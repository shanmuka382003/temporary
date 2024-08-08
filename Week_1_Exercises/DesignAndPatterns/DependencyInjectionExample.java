// DependencyInjectionExample.java

// Repository Interface
interface CustomerRepository {
    String findCustomerById(int id);
}

// Concrete Repository Implementation
class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public String findCustomerById(int id) {
        // In a real application, this might query a database
        // For this example, we will just return a dummy customer
        return "Customer" + id; // Dummy customer data
    }
}

// Service Class
class CustomerService {
    private final CustomerRepository customerRepository;

    // Constructor Injection
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public String getCustomer(int id) {
        return customerRepository.findCustomerById(id);
    }
}

// Main Class to Test the Dependency Injection Implementation
public class DependencyInjectionExample {
    public static void main(String[] args) {
        // Create the concrete repository
        CustomerRepository customerRepository = new CustomerRepositoryImpl();

        // Inject the repository into the service using constructor injection
        CustomerService customerService = new CustomerService(customerRepository);

        // Use the service to find a customer
        String customer = customerService.getCustomer(1);
        System.out.println("Found Customer: " + customer);
    }
}
