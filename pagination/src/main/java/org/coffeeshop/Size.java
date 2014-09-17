package org.coffeeshop;

/**
 * A basic enum for the size of coffee
 */
public enum Size {
    Small("S"), Medium("M"), Large("L"), ExtraLarge("XL");
    private String value;

    private Size(String v) {
        this.value = v;
    }
    public String getValue() {
        return value;
    }
   /* public String toString() {
        return value;
    }*/


}
