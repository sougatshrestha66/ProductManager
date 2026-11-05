import java.io.*;
import java.util.HashMap;
import java.util.List;

public class FileProductStorage implements ProductStorage {
    private final String filePath;
    private final HashMapStorage storage = new HashMapStorage();

    public FileProductStorage(String filePath) {
        this.filePath = filePath;
        loadProducts();

    }


    private void loadProducts() {
        File file = new File(filePath);
        if (!file.exists()) {
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file)))
        {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                try {
                    String[] parts = line.split(",");
                    Product p = new Product(Integer.parseInt(parts[0].trim()), parts[1].trim(), Double.parseDouble(parts[2].trim()), Integer.parseInt(parts[3].trim()));
                    storage.addProduct(p);

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException | DuplicateProductException e) {
                    System.out.println("Invalid product id: " + line);
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading file" + e.getMessage());
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return storage.getAllProducts();
    }

    @Override
    public List<Product> getProductsByName(String name) {
        return storage.getProductsByName(name);
    }

    @Override
    public Product getProduct(int id) throws ProductNotFoundException {
        return storage.getProduct(id);
    }

    @Override
    public void addProduct(Product product) throws DuplicateProductException {
        storage.addProduct(product);
        saveProduct();
    }

    @Override
    public void updateProduct(Product updatedProduct) throws ProductNotFoundException {
        storage.updateProduct(updatedProduct);
        saveProduct();
    }

    @Override
    public void deleteProduct(int id) throws ProductNotFoundException {
        storage.deleteProduct(id);
        saveProduct();

    }

    private void saveProduct() {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))) {
            for (Product product : getAllProducts()) {
                bw.write(product.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }


}
