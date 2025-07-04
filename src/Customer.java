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
        double shipping = cart.getShippableItems().size() * 10.0;
        double total = subtotal + shipping;

        for (CartItem item : cart.getItems()) {
            if (item.getProduct().isExpired()) throw new Exception(item.getProduct().getName() + " is expired");
        }

        if (balance < total) throw new Exception("Insufficient balance");

        for (CartItem item : cart.getItems()) {
            item.getProduct().reduceQuantity(item.getQuantity());
        }

        balance -= total;

        System.out.println("Order subtotal: " + subtotal);
        System.out.println("Shipping fees: " + shipping);
        System.out.println("Paid amount: " + total);
        System.out.println("Remaining balance: " + balance);
        List<shippable> toShip = cart.getShippableItems();
        
        if (!toShip.isEmpty()) {
            shippingService.shipItems(toShip);
        }
    }
}
