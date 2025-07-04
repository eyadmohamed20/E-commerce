import java.util.List;

public class shippingService {
    public static void shipItems(List<shippable> items) {
        System.out.println("Shipping items:");
        for (shippable item : items) {
            System.out.println("- " + item.getName() + " weighing " + item.getWeight() + "kg");
        }
    }
}
