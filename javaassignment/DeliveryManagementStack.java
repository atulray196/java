import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class DeliveryPerson {
    String deliveryPersonId;
    String name;

    public DeliveryPerson(String deliveryPersonId, String name) {
        this.deliveryPersonId = deliveryPersonId;
        this.name = name;
    }
}

enum DeliveryStatus {
    PENDING, IN_PROGRESS, DELIVERED
}

interface DeliveryManagement {
    void assignDeliveryPerson(Order order, DeliveryPerson deliveryPerson);
    void updateDeliveryStatus(Order order, DeliveryStatus status);
}

class DeliveryManagementStack implements DeliveryManagement {
    private Map<Order, DeliveryPerson> deliveryMap;
    private Stack<DeliveryStatus> deliveryStatusStack;

    public DeliveryManagementStack() {
        this.deliveryMap = new HashMap<>();
        this.deliveryStatusStack = new Stack<>();
    }

    @Override
    public void assignDeliveryPerson(Order order, DeliveryPerson deliveryPerson) {
        deliveryMap.put(order, deliveryPerson);
    }

    @Override
    public void updateDeliveryStatus(Order order, DeliveryStatus status) {
        deliveryStatusStack.push(status);
    }

    public static void main(String[] args) {
        DeliveryManagement deliveryManagement = new DeliveryManagementStack();

        // Sample data
        Order order1 = new Order(1, "John Doe");
        Order order2 = new Order(2, "Jane Smith");
        DeliveryPerson deliveryPerson1 = new DeliveryPerson("DP1", "DeliveryPerson1");
        DeliveryPerson deliveryPerson2 = new DeliveryPerson("DP2", "DeliveryPerson2");

        // Assigning delivery persons
        deliveryManagement.assignDeliveryPerson(order1, deliveryPerson1);
        deliveryManagement.assignDeliveryPerson(order2, deliveryPerson2);

        // Updating delivery status
        deliveryManagement.updateDeliveryStatus(order1, DeliveryStatus.IN_PROGRESS);
        deliveryManagement.updateDeliveryStatus(order2, DeliveryStatus.DELIVERED);

        // Displaying delivery information
        System.out.println("Delivery Person for Order 1: " + deliveryManagement.getDeliveryPerson(order1).name);
        System.out.println("Delivery Status for Order 1: " + deliveryManagement.getDeliveryStatus());
        System.out.println("Delivery Person for Order 2: " + deliveryManagement.getDeliveryPerson(order2).name);
        System.out.println("Delivery Status for Order 2: " + deliveryManagement.getDeliveryStatus());
    }

    private DeliveryPerson getDeliveryPerson(Order order) {
        return deliveryMap.get(order);
    }

    private DeliveryStatus getDeliveryStatus() {
        return deliveryStatusStack.isEmpty() ? null : deliveryStatusStack.pop();
    }
}
