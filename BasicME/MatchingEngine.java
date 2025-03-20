import java.util.*;

class MatchingEngine {
    PriorityQueue<Order> buyOrders, sellOrders; // order book
    Order lastOrderProcessed;

    public MatchingEngine() {
        buyOrders = new PriorityQueue<>(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                if (o1.price < o2.price) {
                    return Integer.MAX_VALUE;
                }
                else if (o1.price == o2.price) {
                    return Integer.compare(o1.quantity, o2.quantity);
                }
                else {
                    return Integer.MIN_VALUE;
                }
            }
        });
        sellOrders = new PriorityQueue<>(new Comparator<Order>() {
            @Override
            public int compare(Order o1, Order o2) {
                if (o1.price > o2.price) {
                    return Integer.MAX_VALUE;
                }
                else if (o1.price == o2.price) {
                    return Integer.compare(o1.quantity, o2.quantity);
                }
                else {
                    return Integer.MIN_VALUE;
                }
            }
        });
    }

    public void getOrder(Order order) {
        order.sequenceId = order.generateSequenceId(lastOrderProcessed);
        if (order.type.equals("BUY")) {
            buyOrders.add(order);
        }
        else if (order.type.equals("SEL")) {
            sellOrders.add(order);
        }
        lastOrderProcessed = order;
    }

    public String processOrder(Order order) {
        if (order != null && order.type.equals("CAN")) {
            Iterator<Order> iterator = buyOrders.iterator();
            while (iterator.hasNext()) {
                Order currentOrder = iterator.next();
                if (currentOrder.sequenceId == order.quantity) {
                    iterator.remove();
                    return "Cancelled order " + Integer.toString(order.quantity);
                }
            }
            iterator = sellOrders.iterator();
            while (iterator.hasNext()) {
                Order currentOrder = iterator.next();
                if (currentOrder.sequenceId == order.quantity) {
                    iterator.remove();
                    return "Cancelled order " + Integer.toString(order.quantity);
                }
            }
            return "Unable to cancel order " + Integer.toString(order.quantity);
        }
        if (!buyOrders.isEmpty() && !sellOrders.isEmpty()) {
            System.out.println("YES");
            System.out.println(buyOrders.peek().price + ", " + sellOrders.peek().price);
            if (buyOrders.peek().price >= sellOrders.peek().price) {
                Order buyOrder = buyOrders.poll();
                Order sellOrder = sellOrders.poll();
                String returnStatement = "";
                if (buyOrder.quantity > sellOrder.quantity) {
                    buyOrder.quantity -= sellOrder.quantity;
                    buyOrders.add(buyOrder);
                    returnStatement = Integer.toString(sellOrder.quantity) + " shares traded at price " +
                            Float.toString(buyOrder.price) + " , sequence Id's of buyer and seller are: " +
                            Integer.toString(buyOrder.sequenceId) + " " + Integer.toString(sellOrder.sequenceId);
                }
                else if (buyOrder.quantity < sellOrder.quantity) {
                    sellOrder.quantity -= buyOrder.quantity;
                    sellOrders.add(sellOrder);
                    returnStatement = Integer.toString(buyOrder.quantity) + " shares traded at price " +
                            Float.toString(buyOrder.price) + " , sequence Id's of buyer and seller are: " +
                            Integer.toString(buyOrder.sequenceId) + " " + Integer.toString(sellOrder.sequenceId);
                }
                else {
                    returnStatement = Integer.toString(sellOrder.quantity) + " shares traded at price " +
                            Float.toString(buyOrder.price) + " , sequence Id's of buyer and seller are: " +
                            Integer.toString(buyOrder.sequenceId) + " " + Integer.toString(sellOrder.sequenceId);
                }
                return returnStatement;
            }
        }
        return null;
    }
}
