package org.example;

/**
 * Created by Mehrdad Ghaderi, S&M
 * Date: 3/12/2025
 * Time: 12:54 PM
 */
public class InventoryItem {
    private Product product;
    private double price;
    private int qtyTotal; //currently in stock
    private int qtyReserved; //products that's in the carts, but not yet sold
    private int qtyReorder; //the number to order a product when low
    private int qtyLow; //the trigger or threshold to order more product

    public InventoryItem(Product product, double price, int qtyTotal, int qtyLow) {
        this.product = product;
        this.price = price;
        this.qtyTotal = qtyTotal;
        this.qtyLow = qtyLow;
        this.qtyReorder = qtyTotal;
    }

    public Product getProduct() {
        return product;
    }

    public double getPrice() {
        return price;
    }

    public boolean reserveItem(int qty) {
        int qtyAvailable = qtyTotal - qtyReserved;
        if ((qtyAvailable) < qty) {
            printUnavailablity(qtyAvailable);
            return false;
        }
        this.qtyReserved += qty;
        return true;
    }

    private void printUnavailablity(int qtyAvailable) {
        System.out.println("There are only " + qtyAvailable + " "
                + (qtyAvailable == 1 ? product.name() : (product.name() + "s"))
                +  " left in stock for this item.");
    }

    public void releaseItem(int qty) {
        qtyReserved -= qty;
    }

    public boolean sellItem(int qty) {

        if (qtyTotal >= qty) {
            qtyTotal -= qty;
            qtyReserved -= qty;
            if (qtyTotal <= qtyLow) {
                placeInventoryOrder();
            }
            return true;
        }
        printUnavailablity(qty);
        return false;
    }

    private void placeInventoryOrder() {
        System.out.printf("Ordering quantity %d : %s%n ", qtyReorder, product);
        qtyTotal += qtyReorder;
    }

    @Override
    public String toString() {
        return "%s, $%.2f : [%04d, % 2d]".formatted(product, price, qtyTotal, qtyReserved);
    }
}
