import java.util.LinkedList;
import java.util.Queue;

class Payment {
    double amount;

    public Payment(double amount) {
        this.amount = amount;
    }
}

interface PaymentManagement {
    void processPayment(Order order, Payment payment);
    void refundPayment(Order order);
}

class PaymentManagementQueue implements PaymentManagement {
    private Queue<Payment> paymentQueue;

    public PaymentManagementQueue() {
        this.paymentQueue = new LinkedList<>();
    }

    @Override
    public void processPayment(Order order, Payment payment) {
        paymentQueue.offer(payment);
    }

    @Override
    public void refundPayment(Order order) {
        paymentQueue.poll();
    }

    public static void main(String[] args) {
        PaymentManagement paymentManagement = new PaymentManagementQueue();

        // Sample data
        Order order1 = new Order(1, "John Doe");
        Order order2 = new Order(2, "Jane Smith");
        Payment payment1 = new Payment(20.0);
        Payment payment2 = new Payment(15.0);

        // Processing payments
        paymentManagement.processPayment(order1, payment1);
        paymentManagement.processPayment(order2, payment2);

        // Displaying payment information
        System.out.println("Payment amount for Order 1: $" + paymentManagement.getPaymentAmount(order1));
        System.out.println("Payment amount for Order 2: $" + paymentManagement.getPaymentAmount(order2));

        // Refunding payment for Order 1
        paymentManagement.refundPayment(order1);
        System.out.println("Payment amount for Order 1 after refund: $" + paymentManagement.getPaymentAmount(order1));
    }

    private double getPaymentAmount(Order order) {
        return paymentQueue.peek().amount;
    }
}
