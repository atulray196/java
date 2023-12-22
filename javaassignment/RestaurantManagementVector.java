import java.util.List;
import java.util.Vector;

class Restaurant {
    int restaurantId;
    String name;

    public Restaurant(int restaurantId, String name) {
        this.restaurantId = restaurantId;
        this.name = name;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public String getName() {
        return name;
    }
}

interface RestaurantManagement {
    void addRestaurant(Restaurant restaurant);
    Restaurant getRestaurantById(int restaurantId);
    List<Restaurant> getAllRestaurants();
}

class RestaurantManagementVector implements RestaurantManagement {
    private List<Restaurant> restaurants;

    public RestaurantManagementVector() {
        this.restaurants = new Vector<>();
    }

    @Override
    public void addRestaurant(Restaurant restaurant) {
        restaurants.add(restaurant);
    }

    @Override
    public Restaurant getRestaurantById(int restaurantId) {
        for (Restaurant restaurant : restaurants) {
            if (restaurant.getRestaurantId() == restaurantId) {
                return restaurant;
            }
        }
        return null;
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurants;
    }

    public static void main(String[] args) {
        RestaurantManagement restaurantManagement = new RestaurantManagementVector();

        // Adding restaurants
        restaurantManagement.addRestaurant(new Restaurant(1, "Burger King"));
        restaurantManagement.addRestaurant(new Restaurant(2, "Pizza Hut"));

        // Retrieving and displaying restaurants
        Restaurant restaurant = restaurantManagement.getRestaurantById(1);
        System.out.println("Restaurant ID: " + restaurant.getRestaurantId() + ", Name: " + restaurant.getName());

        List<Restaurant> allRestaurants = restaurantManagement.getAllRestaurants();
        System.out.println("All Restaurants:");
        for (Restaurant r : allRestaurants) {
            System.out.println("Restaurant ID: " + r.getRestaurantId() + ", Name: " + r.getName());
        }
    }
}
