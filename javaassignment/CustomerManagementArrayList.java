import java.util.ArrayList;
import java.util.List;

class Customer {
    int customerId;
    String name;

    public Customer(int customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }
}

interface CustomerManagement {
    void addCustomer(Customer customer);
    Customer getCustomerById(int customerId);
    List<Customer> getAllCustomers();
}

class CustomerManagementArrayList implements CustomerManagement {
    private List<Customer> customers;

    public CustomerManagementArrayList() {
        this.customers = new ArrayList<>();
    }

    @Override
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    @Override
    public Customer getCustomerById(int customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId() == customerId) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customers;
    }

    public static void main(String[] args) {
        CustomerManagement customerManagement = new CustomerManagementArrayList();

        // Adding customers
        customerManagement.addCustomer(new Customer(1, "John Doe"));
        customerManagement.addCustomer(new Customer(2, "Jane Smith"));

        // Retrieving and displaying customers
        Customer customer = customerManagement.getCustomerById(1);
        System.out.println("Customer ID: " + customer.getCustomerId() + ", Name: " + customer.getName());

        List<Customer> allCustomers = customerManagement.getAllCustomers();
        System.out.println("All Customers:");
        for (Customer c : allCustomers) {
            System.out.println("Customer ID: " + c.getCustomerId() + ", Name: " + c.getName());
        }
    }
}
