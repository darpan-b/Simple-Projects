public class Main {
    public static void main(String[] args) {
        Client client = new Client();
        MatchingEngine matchingEngine = new MatchingEngine();
        while (true) {
            Order curOrder = client.askOrder();
            matchingEngine.getOrder(curOrder);
            String orderMsg = "";
            if (curOrder.type.equals("CAN")) {
                orderMsg = matchingEngine.processOrder(curOrder); // threading issue b/w client and ME
            }
            if (orderMsg != null) {
                System.out.println(orderMsg);
            }
            for (int i = 0; i < 5; i++) { // this doesn't make sense but whatever
                orderMsg = matchingEngine.processOrder(null);
                if (orderMsg != null) {
                    System.out.println(orderMsg);
                }
            }
        }
    }
}