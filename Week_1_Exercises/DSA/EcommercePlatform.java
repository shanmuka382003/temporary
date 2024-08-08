import java.util.Arrays;
import java.util.Comparator;

// Product class definition
class Product {
    private String productId;
    private String productName;
    private String category;

    public Product(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    // Getters
    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }
}

// EcommercePlatform class definition
public class EcommercePlatform {
    private Product[] products;

    public EcommercePlatform(Product[] products) {
        this.products = products;
    }

    // Linear Search
    public Product linearSearch(String productName) {
        for (Product product : products) {
            if (product.getProductName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }

    // Binary Search
    public Product binarySearch(String productName) {
        Arrays.sort(products, Comparator.comparing(Product::getProductName));
        int left = 0;
        int right = products.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = products[mid].getProductName().compareToIgnoreCase(productName);

            if (comparison == 0) {
                return products[mid];
            }
            if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Product[] products = {
            new Product("P001", "Laptop", "Electronics"),
            new Product("P002", "Smartphone", "Electronics"),
            new Product("P003", "Tablet", "Electronics"),
            new Product("P004", "Headphones", "Accessories"),
            new Product("P005", "Charger", "Accessories")
        };

        EcommercePlatform search = new EcommercePlatform(products);

        // Linear Search
        System.out.println("Linear Search:");
        Product foundProduct = search.linearSearch("Tablet");
        if (foundProduct != null) {
            System.out.println("Found Product: " + foundProduct.getProductName());
        } else {
            System.out.println("Product not found.");
        }

        // Binary Search
        System.out.println("\nBinary Search:");
        foundProduct = search.binarySearch("Tablet");
        if (foundProduct != null) {
            System.out.println("Found Product: " + foundProduct.getProductName());
        } else {
            System.out.println("Product not found.");
        }
    }
}
