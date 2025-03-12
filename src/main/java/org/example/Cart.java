package org.example;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Mehrdad Ghaderi, S&M
 * Date: 3/12/2025
 * Time: 12:53 PM
 */
public class Cart {

    enum CartType {PHYSICAL, VIRTUAL}

    private static int lastId = 1;
    private int id;
    private LocalDate cartDate;
    private CartType type;
    private Map<String, Integer> products;

    public Cart(CartType type, int days) {
        this.type = type;
        id = lastId++;
        cartDate = LocalDate.now().minusDays(days);
        products = new HashMap<>();
    }

    public Cart(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public LocalDate getCartDate() {
        return cartDate;
    }

    public void addItem(InventoryItem inventoryItem, int qty) {
        products.merge(inventoryItem.getProduct().stockKeepingUnit(), qty, Integer::sum);
        if (!inventoryItem.reserveItem(qty)) {
            System.out.println("Something went wrong, " + inventoryItem.getProduct().name() + " could not be added.");
        }
    }

    public void removeItem(InventoryItem inventoryItem, int qty) {
        Integer currentQty = products.get(inventoryItem.getProduct().stockKeepingUnit());
        if (currentQty <= qty) {
            products.remove(inventoryItem.getProduct().stockKeepingUnit());
            System.out.print("Item [%s] removed from cart".formatted(inventoryItem.getProduct().name()));
        } else {
            products.merge(inventoryItem.getProduct().stockKeepingUnit(), qty,
                    (oldValue, newValue) -> oldValue - newValue);
            System.out.printf("%d [%s]s removed from cart".formatted(qty, inventoryItem.getProduct().name()));
        }
        inventoryItem.releaseItem(qty);
    }

    public void printSaleSlip(Map<String, InventoryItem> inventory) {
        double totalPrice = 0;
        System.out.println("-".repeat(40));

        for (var cartItem : products.entrySet()) {
            InventoryItem inventoryItem = inventory.get(cartItem.getKey());
            Integer qty = cartItem.getValue();
            double price = inventoryItem.getPrice() * qty;
            totalPrice += price;
            System.out.printf("\t%s %-10s (%d)@ $%2f = $%.2f%n"
                    , cartItem.getKey(), inventoryItem.getProduct().name(), qty, inventoryItem.getPrice(), price);
        }

        System.out.printf("Total price: %.2f%n", totalPrice);
        System.out.println("-".repeat(40));
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", cartDate=" + cartDate +
                ", products=" + products +
                '}';
    }
}
