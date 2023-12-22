import java.util.LinkedList;
import java.util.List;

class Order {
    int orderId;
    String customerName;

    public Order(int orderId, String customerName) {
        this.orderId = orderId;
        this.customerName = customerName;
    }

    public int getOrderId() {
        return orderId;
    }

    public String getCustomerName() {
        return customerName;
    }
}

interface OrderManagement {
    void placeOrder(Order order);
    Order getOrderById(int orderId);
    List<Order> getAllOrders();
}

class OrderManagementLinkedList implements OrderManagement {
    private List<Order> orders;

    public OrderManagementLinkedList() {
        this.orders = new LinkedList<>();
    }

    @Override
    public void placeOrder(Order order) {
        orders.add(order);
    }

    @Override
    public Order getOrderById(int orderId) {
        for (Order order : orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return orders;
    }

    public static void main(String[] args) {
        OrderManagement orderManagement = new OrderManagementLinkedList();

        // Placing orders
        orderManagement.placeOrder(new Order(1, "John Doe"));
        orderManagement.placeOrder(new Order(2, "Jane Smith"));

        // Retrieving and displaying orders
        Order retrievedOrder = orderManagement.getOrderById(1);
        System.out.println("Order ID: " + retrievedOrder.getOrderId() + ", Customer: " + retrievedOrder.getCustomerName());

        List<Order> allOrders = orderManagement.getAllOrders();
        System.out.println("All Orders:");
        for (Order order : allOrders) {
            System.out.println("Order ID: " + order.getOrderId() + ", Customer: " + order.getCustomerName());
        }
    }
}
