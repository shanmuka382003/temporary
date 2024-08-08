import java.util.HashMap;

// Product class definition
class Product {
    private String productId;
    private String productName;
    private int quantity;
    private double price;

    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}

// InventoryManagementSystem class definition
public class InventoryManagementSystem {
    private HashMap<String, Product> inventory;

    public InventoryManagementSystem() {
        this.inventory = new HashMap<>();
    }

    // Method to add a product
    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
    }

    // Method to update a product
    public void updateProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            inventory.put(product.getProductId(), product);
        } else {
            System.out.println("Product not found in inventory.");
        }
    }

    // Method to delete a product
    public void deleteProduct(String productId) {
        if (inventory.containsKey(productId)) {
            inventory.remove(productId);
        } else {
            System.out.println("Product not found in inventory.");
        }
    }

    // Method to get a product
    public Product getProduct(String productId) {
        return inventory.get(productId);
    }

    // Method to print inventory
    public void printInventory() {
        for (Product product : inventory.values()) {
            System.out.println("ID: " + product.getProductId() +
                               ", Name: " + product.getProductName() +
                               ", Quantity: " + product.getQuantity() +
                               ", Price: " + product.getPrice());
        }
    }

    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();

        // Add products
        ims.addProduct(new Product("P001", "Product 1", 100, 29.99));
        ims.addProduct(new Product("P002", "Product 2", 200, 19.99));

        // Print inventory
        System.out.println("Initial inventory:");
        ims.printInventory();

        // Update product
        ims.updateProduct(new Product("P001", "Product 1", 150, 29.99));

        // Print inventory after update
        System.out.println("\nInventory after updating quantity of Product 1:");
        ims.printInventory();

        // Delete product
        ims.deleteProduct("P002");

        // Print inventory after deletion
        System.out.println("\nInventory after deletion:");
        ims.printInventory();
    }
}
