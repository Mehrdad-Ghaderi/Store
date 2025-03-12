package org.example;

import java.time.LocalDate;
import java.util.Map;

/**
 * Created by Mehrdad Ghaderi, S&M
 * Date: 3/12/2025
 * Time: 12:53 PM
 */
public class Cart {

    enum Type{PHYSICAL, VIRTUAL}

    private String id;
    private Map<Product, Integer> products;
    private LocalDate orderDate;
    private Type type;

    /* addItem, removeItem, printSalesSlip */

}
