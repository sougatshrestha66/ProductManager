//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public static void main(String[] args) {
    Product p = new Product(1111,"Laptop",2000000,6);
    System.out.println(p);

    p.setQuantity(3);

    System.out.println(p.getName() + " has left only " + p.getQuantity() + " in stock");
}
