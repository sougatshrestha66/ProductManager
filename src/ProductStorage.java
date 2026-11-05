import java.util.List;

public interface ProductStorage {
    List<Product> getAllProducts();
    List<Product> getProductsByName(String name);
    void addProduct(Product product) throws DuplicateProductException;
    Product getProduct(int id) throws ProductNotFoundException;
    void updateProduct(Product updatedProduct) throws ProductNotFoundException;
    void deleteProduct(int id) throws ProductNotFoundException;
}
