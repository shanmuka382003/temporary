// DecoratorPatternExample.java
public class DecoratorPatternExample {

    // Component Interface
    public interface Notifier {
        void send(String message);
    }

    // Concrete Component
    public static class EmailNotifier implements Notifier {
        @Override
        public void send(String message) {
            System.out.println("Sending Email: " + message);
        }
    }

    // Abstract Decorator Class
    public static abstract class NotifierDecorator implements Notifier {
        protected Notifier notifier;

        public NotifierDecorator(Notifier notifier) {
            this.notifier = notifier;
        }

        @Override
        public void send(String message) {
            notifier.send(message);
        }
    }

    // Concrete Decorator Classes
    public static class SMSNotifierDecorator extends NotifierDecorator {
        public SMSNotifierDecorator(Notifier notifier) {
            super(notifier);
        }

        @Override
        public void send(String message) {
            super.send(message); // Call the original notifier
            System.out.println("Sending SMS: " + message);
        }
    }

    public static class SlackNotifierDecorator extends NotifierDecorator {
        public SlackNotifierDecorator(Notifier notifier) {
            super(notifier);
        }

        @Override
        public void send(String message) {
            super.send(message); // Call the original notifier
            System.out.println("Sending Slack Message: " + message);
        }
    }

    // Test the Decorator Implementation
    public static void main(String[] args) {
        // Create a basic email notifier
        Notifier emailNotifier = new EmailNotifier();

        // Decorate it with SMS notification capability
        Notifier smsNotifier = new SMSNotifierDecorator(emailNotifier);

        // Decorate it further with Slack notification capability
        Notifier slackNotifier = new SlackNotifierDecorator(smsNotifier);

        // Send a message
        System.out.println("Sending notifications:");
        slackNotifier.send("Hello, World!");
    }
}
