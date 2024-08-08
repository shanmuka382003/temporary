// BuilderPatternExample.java
public class BuilderPatternExample {

    // Product Class
    public static class Computer {
        private String CPU;
        private String RAM;
        private String storage;
        private String graphicsCard;
        private String powerSupply;

        // Private constructor to enforce object creation through Builder
        private Computer(Builder builder) {
            this.CPU = builder.CPU;
            this.RAM = builder.RAM;
            this.storage = builder.storage;
            this.graphicsCard = builder.graphicsCard;
            this.powerSupply = builder.powerSupply;
        }

        @Override
        public String toString() {
            return "Computer{" +
                    "CPU='" + CPU + '\'' +
                    ", RAM='" + RAM + '\'' +
                    ", Storage='" + storage + '\'' +
                    ", Graphics Card='" + graphicsCard + '\'' +
                    ", Power Supply='" + powerSupply + '\'' +
                    '}';
        }

        // Static nested Builder class
        public static class Builder {
            private String CPU;
            private String RAM;
            private String storage;
            private String graphicsCard;
            private String powerSupply;

            public Builder setCPU(String CPU) {
                this.CPU = CPU;
                return this;
            }

            public Builder setRAM(String RAM) {
                this.RAM = RAM;
                return this;
            }

            public Builder setStorage(String storage) {
                this.storage = storage;
                return this;
            }

            public Builder setGraphicsCard(String graphicsCard) {
                this.graphicsCard = graphicsCard;
                return this;
            }

            public Builder setPowerSupply(String powerSupply) {
                this.powerSupply = powerSupply;
                return this;
            }

            public Computer build() {
                return new Computer(this);
            }
        }
    }

    // Test the Builder Implementation
    public static void main(String[] args) {
        // Create a Computer with specific configurations
        Computer gamingPC = new Computer.Builder()
                .setCPU("Intel i9")
                .setRAM("32GB")
                .setStorage("1TB SSD")
                .setGraphicsCard("NVIDIA RTX 3080")
                .setPowerSupply("750W")
                .build();

        Computer officePC = new Computer.Builder()
                .setCPU("Intel i5")
                .setRAM("16GB")
                .setStorage("512GB SSD")
                .build();

        // Display the configurations
        System.out.println("Gaming PC: " + gamingPC);
        System.out.println("Office PC: " + officePC);
    }
}
