import java.util.Scanner;

public class Client {
    Scanner scanner = new Scanner(System.in);

    public Order askOrder() {
        System.out.println("Enter order type (BUY, SEL, CAN):");
        String orderType = scanner.next();
        System.out.println("Enter order quantity:");
        int orderQuantity = scanner.nextInt();
        System.out.println("Enter order price:");
        float orderPrice = scanner.nextFloat();
        Order order = null;
        try {
            order = new Order(orderType, orderQuantity, orderPrice);
        } catch (RuntimeException e) {
            System.out.println("Invalid order!");
            return null;
        }
        return order;
    }
}
