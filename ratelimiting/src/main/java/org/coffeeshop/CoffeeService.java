package org.coffeeshop;

import javax.ejb.Singleton;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * A basic collection of coffee orders for simplicity
 * @author Bhakti Mehta
 */
@Singleton
public class CoffeeService {
    private static final HashMap<Integer, Coffee> coffees = new HashMap<Integer, Coffee>();
    private static AtomicInteger orderCounter = new AtomicInteger(0);

    public static int incrementOrderCounter() {
        return orderCounter.incrementAndGet();
    }


    public static int addCoffee(Coffee coffee) {
        int counter = incrementOrderCounter();
        coffees.put(counter, coffee);
        return counter;
    }

    public static int getSize() {
        return  coffees.size();
    }

    public static Coffee getCoffee(int order) {
        return coffees.get(order);
    }

    public CoffeeService() {
        // initial content
        addCoffee(new Coffee("Brewed", "Small", "Mocha", 3.50));

    }

    public static int getCounter() {

        return orderCounter.get();
    }
}
