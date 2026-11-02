import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ProductStorage {

    private final HashMap<Integer, Product> products = new HashMap<>();

    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
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
        if (!products.containsKey(id)) {
            throw new ProductNotFoundException("Product Not found:" + id);
        }
        products.remove(id);
    }
    public boolean exists(int id) {
        return products.containsKey(id);
    }
    public int getProductCount() {
        return products.size();
    }

}
