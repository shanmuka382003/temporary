// StrategyPatternExample.java

// Strategy Interface
interface PaymentStrategy {
    void pay(String amount);
}

// Concrete Strategy for Credit Card Payment
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(String amount) {
        System.out.println("Paid " + amount + " using Credit Card: " + cardNumber);
    }
}

// Concrete Strategy for PayPal Payment
class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(String amount) {
        System.out.println("Paid " + amount + " using PayPal: " + email);
    }
}

// Context Class
class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executePayment(String amount) {
        if (paymentStrategy != null) {
            paymentStrategy.pay(amount);
        } else {
            System.out.println("Payment strategy not set!");
        }
    }
}

// Test the Strategy Implementation
public class StrategyPatternExample {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        // Paying with Credit Card
        System.out.println("Using Credit Card Payment:");
        PaymentStrategy creditCard = new CreditCardPayment("1234-5678-9876-5432");
        context.setPaymentStrategy(creditCard);
        context.executePayment("$100");

        // Paying with PayPal
        System.out.println("\nUsing PayPal Payment:");
        PaymentStrategy paypal = new PayPalPayment("user@example.com");
        context.setPaymentStrategy(paypal);
        context.executePayment("$150");

        // Trying without setting a payment strategy
        System.out.println("\nTrying with no Payment Strategy:");
        context.executePayment("$200");
    }
}
