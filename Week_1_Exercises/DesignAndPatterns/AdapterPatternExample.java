// AdapterPatternExample.java
public class AdapterPatternExample {

    // Target Interface
    public interface PaymentProcessor {
        void processPayment(double amount);
    }

    // Adaptee Classes
    public static class PayPal {
        public void makePayment(double amount) {
            System.out.println("Payment of $" + amount + " processed through PayPal.");
        }
    }

    public static class Stripe {
        public void pay(double amount) {
            System.out.println("Payment of $" + amount + " processed through Stripe.");
        }
    }

    // Adapter Classes
    public static class PayPalAdapter implements PaymentProcessor {
        private PayPal payPal;

        public PayPalAdapter(PayPal payPal) {
            this.payPal = payPal;
        }

        @Override
        public void processPayment(double amount) {
            payPal.makePayment(amount);
        }
    }

    public static class StripeAdapter implements PaymentProcessor {
        private Stripe stripe;

        public StripeAdapter(Stripe stripe) {
            this.stripe = stripe;
        }

        @Override
        public void processPayment(double amount) {
            stripe.pay(amount);
        }
    }

    // Test the Adapter Implementation
    public static void main(String[] args) {
        // Create instances of the payment gateways
        PayPal payPal = new PayPal();
        Stripe stripe = new Stripe();

        // Create adapter instances
        PaymentProcessor payPalProcessor = new PayPalAdapter(payPal);
        PaymentProcessor stripeProcessor = new StripeAdapter(stripe);

        // Use the adapters to process payments
        payPalProcessor.processPayment(100.00);
        stripeProcessor.processPayment(150.50);
    }
}
