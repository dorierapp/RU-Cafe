package com.example.project5;
import java.util.ArrayList;
import java.io.*;

/**
 * Manages all orders, including past orders.
 * Add and remove orders, and get orders at a given index of the ObservableArrayList.
 * @author Ritu Patel, Dorie Rappaport
 */
public class OrderManager {
    private ArrayList<Order> orders;
    private final String FILEPATH = "ExportedOrders.txt";
    private Order currOrder;

    private ArrayList<Integer> orderNums;
    private int INITIAL_NUM = 1;
    private int newID = INITIAL_NUM;
    /**
     * Default constructor
     */
    public OrderManager() {
        this.orders = new ArrayList<>();
        orderNums = new ArrayList<>();
        currOrder = new Order(INITIAL_NUM);
    }
    /**
     * Given an index, returns the order at that index.
     * The index corresponds to the order number.
     * @param orderNum the order number for which we are searching
     * @return the order at that index
     */
    public Order getOrder(int orderNum) {

        for (Order o : orders) {
            if (o.getOrderNumber() == orderNum) return o;
        }
        return null;
    }

    /**
     * Adds an order to the list. Called when creating a new order.
     */
    public void addOrder() {
        orders.add(currOrder);
        currOrder = new Order(++newID);
        orderNums.add(newID);
    }
    /**
     * Removes an order from the list.
     * @param order the order to remove
     */
    public void removeOrder(Order order) {
        orders.remove(order);
        orderNums.remove(order.getOrderNumber());
    }

    public ArrayList<Integer> getOrderNums(){
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i< orders.size(); i++){
            arr.add(orders.get(i).getOrderNumber());
        }

        return arr;
    }

    /**
     * Exports the orders to a specified file.
     * @throws IOException throws error
     */
    public void export() throws IOException {
        FileWriter f = new FileWriter(FILEPATH);
        for ( Order o : orders ) {
            f.write("Order " + o.getOrderNumber() + ":\n" );
            for ( MenuItem m : o.getItems() ) {
                f.write(m + "\n");
            }
            f.write(String.format("Subtotal: $%.2f\nSales Tax: $%.2f\nOrder Total: $%.2f\n\n",
                    o.getSubtotal(), o.getTax(), o.getTotal()));
        }
        f.close();
    }



    /**
     * returns the current order item
     * @return currorder
     */
    public Order getCurrOrder() {
        return currOrder;
    }
}
