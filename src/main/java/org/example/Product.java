package org.example;

/**
 * Created by Mehrdad Ghaderi, S&M
 * Date: 3/12/2025
 * Time: 12:54 PM
 */
public record Product(String stockKeepingUnit, String name, String manufacturer, Category category) {

    @Override
    public String toString() {
        return name;
    }
}
