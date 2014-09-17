package org.coffeeshop;

import javax.ejb.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import java.text.*;

/**
 * A basic collection of coffee orders for simplicity
 * @author Bhakti Mehta
 */
@Singleton
public class CoffeeService {
    private static final HashMap<Integer, Coffee> coffees = new HashMap<Integer, Coffee>();
    private static AtomicInteger orderCounter = new AtomicInteger(0);

    private static String[] COFFEE_NAMES={"Mocha", "Latte", "Cappuchino","Americano", "Expresso"};

    private static String[] COFFEE_SIZES={"Extra Small", "Small", "Medium","Large", "Extra Large"};
    private static String[] COFFEE_TYPE={"Brewed", "Hot"};

    private static int nextPage;
    public static int incrementOrderCounter() {
        return orderCounter.getAndIncrement();
    }


    public static int addCoffee(Coffee coffee) {
        int counter = incrementOrderCounter();
        coffee.setId(counter);
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
        for (int i = 0 ; i <100; i ++) {
            addCoffee(new Coffee(getCoffeeType(), getCoffeeSize(), getName(), getPrice()));
        }

    }

    public static int getCounter() {

        return orderCounter.get();
    }

    public static void setNextPage(int nextPage) {
        CoffeeService.nextPage = nextPage;
    }

    private double getPrice() {
        double price = Math.random() * 5;
            DecimalFormat twoDForm = new DecimalFormat("#.##");
            return Double.valueOf(twoDForm.format(price));

    }

    private String getName() {
        int idx = new Random().nextInt(COFFEE_NAMES.length);
        String name = (COFFEE_NAMES[idx]);
        return name;
    }

    private String getCoffeeSize() {
        int idx = new Random().nextInt(COFFEE_SIZES.length);
        String size = (COFFEE_SIZES[idx]);
        return size;
    }

    private String getCoffeeType() {
        int idx = new Random().nextInt(COFFEE_TYPE.length);
        String type = (COFFEE_TYPE[idx]);
        return type;
    }

    public static List<Coffee>  getCoffeeList(int page, int limit) {
        setNextPage(page);
        ArrayList<Coffee> coffeesList = new ArrayList<Coffee>();

        int offset = page*limit;
        for (int i = offset ; i < offset+limit; i ++) {
            coffeesList.add(coffees.get(i));
        }
        return coffeesList;
    }

    public static int getNextPage() {
        return nextPage +1;
        
    }
}
