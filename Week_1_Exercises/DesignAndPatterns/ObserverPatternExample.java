import java.util.ArrayList;
import java.util.List;

// ObserverPatternExample.java

// Subject Interface
interface Stock {
    void registerObserver(Observer observer);
    void deregisterObserver(Observer observer);
    void notifyObservers();
}

// Concrete Subject
class StockMarket implements Stock {
    private List<Observer> observers;
    private double stockPrice;

    public StockMarket() {
        observers = new ArrayList<>();
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockPrice);
        }
    }
}

// Observer Interface
interface Observer {
    void update(double stockPrice);
}

// Concrete Observers
class MobileApp implements Observer {
    @Override
    public void update(double stockPrice) {
        System.out.println("Mobile App: Stock price updated to $" + stockPrice);
    }
}

class WebApp implements Observer {
    @Override
    public void update(double stockPrice) {
        System.out.println("Web App: Stock price updated to $" + stockPrice);
    }
}

// Test the Observer Implementation
public class ObserverPatternExample {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        MobileApp mobileApp = new MobileApp();
        WebApp webApp = new WebApp();

        // Register observers
        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(webApp);

        // Update stock price
        System.out.println("Updating stock price to $100.00");
        stockMarket.setStockPrice(100.00);

        // Update stock price again
        System.out.println("\nUpdating stock price to $150.00");
        stockMarket.setStockPrice(150.00);
        
        // Deregister mobile app
        stockMarket.deregisterObserver(mobileApp);
        
        // Update stock price again
        System.out.println("\nUpdating stock price to $200.00");
        stockMarket.setStockPrice(200.00);
    }
}
