import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HashMapStorage implements ProductStorage {

    private HashMap<Integer, Product> products = new HashMap<>();

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    @Override
    public List<Product> getProductsByName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        List<Product> productList = new ArrayList<>();
        for (Product product : products.values()) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                productList.add(product);
            }
        }
        return productList;
    }

    @Override
    public void addProduct(Product product) throws DuplicateProductException {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (products.containsKey(product.getId())) {
            throw new DuplicateProductException("Product already exists");
        }
        products.put(product.getId(), product);
    }

    @Override
    public Product getProduct(int id) throws ProductNotFoundException {
      Product product = products.get(id);
      if (product == null) {
          throw new ProductNotFoundException("Product not found");
      }
      return product;
    }

    @Override
    public void updateProduct(Product updatedProduct) throws ProductNotFoundException {
        if (updatedProduct == null) {
            throw new IllegalArgumentException("Product cannot be null");
        }
        if (!products.containsKey(updatedProduct.getId())) {
            throw new ProductNotFoundException("Product not found");
        }
        products.put(updatedProduct.getId(), updatedProduct);
    }

    @Override
    public void deleteProduct(int id) throws ProductNotFoundException {
        if (products.remove(id) == null) {
            throw new ProductNotFoundException("Product not found: " + id);
        }
    }
}
