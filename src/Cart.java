import java.util.*;

public class Cart {
    private static Cart instance;
    private List<CartItem> items;

    private Cart() {
        items=new ArrayList<>();
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public void addItem(Product product, int quantity) throws Exception {
        if (product.getQuantity() < quantity) {
            throw new Exception("Not enough stock for " + product.getName());
        }
        items.add(new CartItem(product, quantity));
    }

    public double calculateSubtotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public List<CartItem> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public List<shippable> getShippableItems() {
        List<shippable> result = new ArrayList<>();
        for (CartItem item : items) {
            if (item.getProduct() instanceof shippable) {
                for (int i = 0; i < item.getQuantity(); i++) {
                    result.add((shippable) item.getProduct());
                }
            }
        }
        return result;
    }
     public void clear() {
        items.clear();
    }
}
