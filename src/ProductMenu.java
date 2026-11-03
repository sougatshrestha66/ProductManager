import java.util.*;

public class ProductMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final ProductStorage productStorage;

    public ProductMenu(ProductStorage productStorage) {
        this.productStorage = productStorage;
    }

    public void displayMenu() {
        System.out.println("-------Menu--------");
        System.out.println("1.Show All Products");
        System.out.println("2. Search Products");
        System.out.println("3. Add Product");
        System.out.println("4. Update Product");
        System.out.println("5. Delete Product");
        System.out.println("6.Exit");
    }

    public void runMenu() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = readInt("Enter your choice");
            switch (choice) {
                case 1:
                    productList();
                    break;
                case 2:
                    System.out.println("Enter product details");
                    System.out.println("1. Search By Name");
                    System.out.println("2. Search by Id");
                    int option = readInt("Enter option");
                    if (option == 1) {
                        findProductByName();
                    } else if (option == 2) {
                        findProductById();
                    } else {
                        System.out.println("Invalid option");
                    }
                    break;
                case 3:
                    addProduct();
                    break;
                case 4:
                    updateProduct();
                    break;
                case 5:
                    deleteProduct();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;

            }
        }
    }

    private double readDouble(String prompt) {

        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again.");
            }
        }
    }

    private String readString(String prompt) {
        while (true) {
            System.out.println(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("It cannot be empty. Please try again.");
        }
    }


    private int readInt(String message) {
        while (true) {
            System.out.println(message);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println(input + " is not a number");
            }
        }
    }

    private void productList() {
        List<Product> allProducts = productStorage.getAllProducts();
        if (allProducts.isEmpty()) {
            System.out.println("There are no products in the system");
        } else {
            for (Product product : allProducts) {
                System.out.println(product);
            }
            System.out.println("Total: " + allProducts.size());
        }
    }

    private void findProductById() {
        int id = readInt("Enter product id:");
        try {
            System.out.println(productStorage.getProduct(id));
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    private void findProductByName() {

        String name = readString("Enter product name:");
        List<Product> products = productStorage.getProductsByName(name);
        if (products.isEmpty()) {
            System.out.println("There are no products in the system");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    public void addProduct() {
        int id = readInt("Enter product id:");
        String name = readString("Enter product name:");
        double price = readDouble("Enter product price:");
        int quantity = readInt("Enter product quantity:");
        try {
            Product product = new Product(id, name, price, quantity);
            productStorage.addProduct(product);
            System.out.println("Product added successfully");
        } catch (DuplicateProductException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Could not add product: " + e.getMessage());
        }

    }

    public void updateProduct() {
        int id = readInt("Enter product id:");
        try {
            Product product = productStorage.getProduct(id);
            String name = readString("Enter product name:");
            double price = readDouble("Enter product price:");
            int quantity = readInt("Enter product quantity:");
            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            productStorage.updateProduct(new Product(id, name, price, quantity));
            System.out.println("Product updated successfully");
        } catch (IllegalArgumentException | ProductNotFoundException e) {

            System.out.println(e.getMessage());
        }
    }

    public void deleteProduct() {
        try {
            int id = readInt("Enter product id you want to delete:");
            productStorage.deleteProduct(id);
            System.out.println("Product deleted successfully: " + id);
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }



    }

}
