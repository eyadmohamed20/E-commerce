
public class Main {
    public static void main(String[] args) {
        try {
            Customer customer = Customer.getInstance("Eyad", 1000);
            Cart cart = customer.getCart();
            cart.clear(); 
            Product cheese = new shippableProduct("Cheese", 100, 10, 0.2);     
            Product biscuits = new shippableProduct("Biscuits", 150, 10, 0.7); 
            Product scratchCard = new Product("ScratchCard", 50, 10) {};       
            cart.addItem(cheese, 2);
            cart.addItem(biscuits, 1);      
            cart.addItem(scratchCard, 1);   
            customer.checkout();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
