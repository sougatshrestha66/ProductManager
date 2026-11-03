import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductStorage {

    private final HashMap<Integer, Product> products = new HashMap<>();

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    public List<Product> getProductsByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }

    public void updateProduct(Product updatedProduct) throws ProductNotFoundException {
        if (updatedProduct == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (!products.containsKey(updatedProduct.getId())) {
            throw new ProductNotFoundException("Product not found: " + updatedProduct.getId());
        }
        products.put(updatedProduct.getId(), updatedProduct);
    }

    public void addProduct(Product product) throws DuplicateProductException {

        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (products.containsKey(product.getId())) {
            throw new DuplicateProductException("Product " + product.getId() + " already exists");
        }
        products.put(product.getId(), product);
    }

    public Product getProduct(int id) throws ProductNotFoundException {
        Product product = products.get(id);
        if (product == null) {
            throw new ProductNotFoundException("Product Not found:" + id);
        }
        return product;
    }

    public void deleteProduct(int id) throws ProductNotFoundException {

        if (products.remove(id) == null) {
            throw new ProductNotFoundException("Product not found: " + id);
        }
    }


}
