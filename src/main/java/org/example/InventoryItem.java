package org.example;

/**
 * Created by Mehrdad Ghaderi, S&M
 * Date: 3/12/2025
 * Time: 12:54 PM
 */
public class InventoryItem {
    private Product product;
    private int qytTotal; //currently in stock
    private int qtyReserved; //products that's in the carts, but not yet sold
    private int qtyReorder; //the number to order a product when low
    private int qtyLow; //the trigger or threshold to order more product
    private double salesPrice;

    /*
    * reserveItem, releaseItem, sellItem, placeInventoryOrder */
}
