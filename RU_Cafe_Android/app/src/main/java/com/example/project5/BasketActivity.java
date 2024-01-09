package com.example.project5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller for the Basket GUI.
 * Handles user inputs to manage the basket orders.
 * Handles onClick functionality for GUI Nodes.
 * @author Ritu Patel, Dorie Rappaport
 */
public class BasketActivity extends AppCompatActivity {
    private OrderManager om;
    private Order currOrder;
    private TextView salesTaxTF;
    private TextView subtotalTF;
    private TextView totalCostTF;
    private ListView orderItemsListView;


    /**
     * Removes the selected item from the basket.
     */
    protected void RemoveItemClick() {
        if (orderItemsListView.getSelectionModel().getSelectedItem() == null) return;
        currOrder.removeItem((MenuItem) orderItemsListView.getSelectionModel().getSelectedItem());


        refresh();
    }

    /**
     * Places the order.
     */
    @FXML
    protected void PlaceOrderClick() {
        om.addOrder();
        Stage stage = (Stage) subtotalTF.getScene().getWindow();
        stage.close();
    }

    /**
     * Clears the list and sets the cost text fields to the appropriate values.
     */
    private void refresh() {
        orderItemsListView.getAdapter().;
        orderItemsListView.getAdapter().getItems().addAll(currOrder.getItems());
        subtotalTF.setText(String.format("$%.2f", currOrder.getSubtotal()));
        salesTaxTF.setText(String.format("$%.2f", currOrder.getTax()));
        totalCostTF.setText(String.format("$%.2f", currOrder.getTotal()));
    }



}