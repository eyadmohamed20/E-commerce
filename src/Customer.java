import java.util.*;
public class Customer {
    private static Customer instance;
    private String name;
    private double balance;
    private Cart cart;

    private Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
        this.cart = Cart.getInstance();  
    }

    public static Customer getInstance(String name, double balance) {
        if (instance == null) {
            instance = new Customer(name, balance);
        }
        return instance;
    }

    public Cart getCart() {
        return cart;
    }
    public void checkout() throws Exception {
    if (cart.isEmpty()) throw new Exception("Cart is empty");

    double subtotal = cart.calculateSubtotal();
    List<shippable> shippables = cart.getShippableItems();
    double shipping = shippables.size() * 10.0;
    double total = subtotal + shipping;

    for (CartItem item : cart.getItems()) {
        if (item.getProduct().isExpired()) throw new Exception(item.getProduct().getName() + " is expired");
    }

    if (balance < total) throw new Exception("Insufficient balance");

    for (CartItem item : cart.getItems()) {
        item.getProduct().reduceQuantity(item.getQuantity());
    }

    balance -= total;

    if (!shippables.isEmpty()) {
        shippingService.printShipmentNotice(shippables);
    }

    // 🧾 Checkout receipt
    System.out.println("** Checkout receipt **");
    for (CartItem item : cart.getItems()) {
        String name = item.getProduct().getName();
        int qty = item.getQuantity();
        double price = item.getTotalPrice();
        System.out.printf("%dx %-10s %.0f%n", qty, name, price);
    }
    System.out.println("--------------------");
    System.out.printf("Subtotal    %.0f%n", subtotal);
    System.out.printf("Shipping    %.0f%n", shipping);
    System.out.printf("Amount      %.0f%n", total);
    System.out.println();

}

}
