import java.util.*;
public class shippingService {
    public static void printShipmentNotice(List<shippable> items) {
        System.out.println("** Shipment notice **");

        Map<String, Double> weightsByName = new LinkedHashMap<>();
        Map<String, Integer> countByName = new LinkedHashMap<>();
        double totalWeight = 0;

        for (shippable item : items) {
            String name = item.getName();
            double weight = item.getWeight();
            totalWeight += weight;

            weightsByName.put(name, weightsByName.getOrDefault(name, 0.0) + weight);
            countByName.put(name, countByName.getOrDefault(name, 0) + 1);
        }

        for (String name : weightsByName.keySet()) {
            double grams = weightsByName.get(name) * 1000;
            int qty = countByName.get(name);
            System.out.printf("%dx %-10s %4.0fg%n", qty, name, grams);
        }

        System.out.printf("Total package weight %.1fkg%n", totalWeight);
        System.out.println();
    }
}
