import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

class CustomerFeedback {
    String comment;
    int rating;

    public CustomerFeedback(String comment, int rating) {
        this.comment = comment;
        this.rating = rating;
    }
}

interface FeedbackManagement {
    void collectFeedback(Order order, CustomerFeedback feedback);
    double getAverageRating(Restaurant restaurant);
}

class FeedbackManagementHashSet implements FeedbackManagement {
    private Map<Order, CustomerFeedback> feedbackMap;

    public FeedbackManagementHashSet() {
        this.feedbackMap = new HashMap<>();
    }

    @Override
    public void collectFeedback(Order order, CustomerFeedback feedback) {
        feedbackMap.put(order, feedback);
    }

    @Override
    public double getAverageRating(Restaurant restaurant) {
        int totalRating = 0;
        int count = 0;

        for (Map.Entry<Order, CustomerFeedback> entry : feedbackMap.entrySet()) {
            if (entry.getKey().getRestaurant().equals(restaurant)) {
                totalRating += entry.getValue().rating;
                count++;
            }
        }

        return (count > 0) ? ((double) totalRating / count) : 0.0;
    }

    public static void main(String[] args) {
        FeedbackManagement feedbackManagement = new FeedbackManagementHashSet();

        // Sample data
        Order order1 = new Order(1, "John Doe");
        Order order2 = new Order(2, "Jane Smith");
        Restaurant restaurant1 = new Restaurant(1, "Burger King");
        Restaurant restaurant2 = new Restaurant(2, "Pizza Hut");
        CustomerFeedback feedback1 = new CustomerFeedback("Good service", 4);
        CustomerFeedback feedback2 = new CustomerFeedback("Excellent food", 5);

        // Collecting feedback
        feedbackManagement.collectFeedback(order1, feedback1);
        feedbackManagement.collectFeedback(order2, feedback2);

        // Displaying average rating for a restaurant
        System.out.println("Average rating for " + restaurant1.getName() + ": " +
                feedbackManagement.getAverageRating(restaurant1));
        System.out.println("Average rating for " + restaurant2.getName() + ": " +
                feedbackManagement.getAverageRating(restaurant2));
    }
}
