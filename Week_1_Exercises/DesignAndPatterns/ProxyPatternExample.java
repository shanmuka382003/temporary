// ProxyPatternExample.java
public class ProxyPatternExample {

    // Subject Interface
    public interface Image {
        void display();
    }

    // Real Subject Class
    public static class RealImage implements Image {
        private String filename;

        public RealImage(String filename) {
            loadImageFromServer(); // Simulating loading image from a remote server
            this.filename = filename;
        }

        private void loadImageFromServer() {
            System.out.println("Loading image from server: " + filename);
        }

        @Override
        public void display() {
            System.out.println("Displaying image: " + filename);
        }
    }

    // Proxy Class
    public static class ProxyImage implements Image {
        private RealImage realImage;
        private String filename;

        public ProxyImage(String filename) {
            this.filename = filename;
        }

        @Override
        public void display() {
            if (realImage == null) {
                realImage = new RealImage(filename); // Lazy initialization
            }
            realImage.display(); // Display the image
        }
    }

    // Test the Proxy Implementation
    public static void main(String[] args) {
        Image image1 = new ProxyImage("image1.jpg");
        Image image2 = new ProxyImage("image2.jpg");

        // The image will be loaded from the server when displayed for the first time
        System.out.println("First display of image1:");
        image1.display();

        // The image will be cached, so it won't be loaded from the server again
        System.out.println("\nSecond display of image1:");
        image1.display();

        // The image will be loaded from the server when displayed for the first time
        System.out.println("\nFirst display of image2:");
        image2.display();
    }
}
