//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public static void main(String[] args) throws DuplicateProductException {
    Product p = new Product(1111,"Laptop",2000000,6);
    ProductStorage ps = new ProductStorage();
    ProductMenu pm = new ProductMenu(ps);
    ps.addProduct(p);
    pm.runMenu();
}
