public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;

    public Product(int id, String name, double price, int quantity) {

        if (id < 0){
            throw new IllegalArgumentException("Please enter a valid product ID");
        }
        this.id = id;
        setName(name);
        setPrice(price);  // Now it checks is it positive or negative and assign the value
        setQuantity(quantity); // Calling a method that checks the condition and assign the value as well, Does 2 things
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public double getPrice() {return price;}
    public int getQuantity() {return quantity;}

    public void setName(String name) {
        if (name == null || name.isEmpty()){
            throw new IllegalArgumentException("Please enter a valid product name");
        }
        this.name = name;
    }


    public void setPrice(double price) {

        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        this.price = price;
    }


    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return id + "|" + name + "|" + price + "|" + quantity;
    }
}
