package org.example;

import java.util.Set;

/**
 * Created by Mehrdad Ghaderi, S&M
 * Date: 3/12/2025
 * Time: 12:54 PM
 */
public class Product {
    private final String stockKeepingUnit;
    private final String name;
    private final String manufacturer;
    private final Category category;

    public Product(String stockKeepingUnit, String name, String manufacturer, Category category) {
        this.stockKeepingUnit = stockKeepingUnit;
        this.name = name;
        this.manufacturer = manufacturer;
        this.category = category;
    }
}
