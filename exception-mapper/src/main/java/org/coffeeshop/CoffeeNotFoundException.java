package org.coffeeshop;

/**
 * This is a custom exception thrown
 * in case the resource method does not find
 * any coffee orders
 * @author Bhakti Mehta
 */
public class CoffeeNotFoundException extends RuntimeException {

    public CoffeeNotFoundException(String message) {
        super(message);
    }
}
