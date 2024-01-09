package com.example.project5;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project4.R;


/**
 * Controller for the StoreOrders View.
 * Allows for viewing, cancelling, and exporting
 * orders in the store.
 * @author Ritu Patel, Dorie Rappaport
 */
public class StoreOrderActivity extends AppCompatActivity {
    private OrderManager om;
    private Order order;
    private Spinner numOrders;
    private ListView orderItemsListView;
    private Button cancelOrderButton;
    private TextView Total;

    private static final int EMPTY = 0;
    private static final int FIRST = 0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);

        om = (OrderManager) getIntent().getSerializableExtra(getString(R.string.store));
        numOrders = (Spinner) findViewById(R.id.numOrders);
        orderItemsListView = (ListView) findViewById(R.id.orderItemsListView);
        Total = (TextView) findViewById(R.id.Total);
        cancelOrderButton = (Button) findViewById(R.id.cancelOrderButton);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, om.getStringNums());
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        numOrders.setAdapter(arrayAdapter);
        numOrders.setSelection(FIRST);
        numOrders.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        cancelOrderButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Cancels an order
             * Alerts the user.
             * @param view default parameter
             */

            public void onClick(View view) {
                if ( numOrders.getSelectedItem() != null ) {
                    om.removeOrder((Order) (numOrders.getSelectedItem()));
                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                            StoreOrderActivity.this, android.R.layout.simple_spinner_item, om.getStringNums());
                    arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    numOrders.setAdapter(arrayAdapter);
                    makePopUp(getString(R.string.cancelled_title), getString(R.string.cancelled_msg));
                } else {
                    showToast(getString(R.string.no_selection));
                }
            }
        });

        refresh();
    }

    /**
     * A function to initialize the stage AFTER initData is called.
     * This makes the class not extend the Intializable class.
     */
    public void start() {
//        numOrders.getItems().clear();
//        numOrders.getItems().addAll(om.getOrderNums());
//        numOrders.getSelectionModel().selectFirst();
        refresh();
    }

    /**
     * Updates values when selections change.
     * Updates the orders.
     * Updates the totalWithTax value.
     */
    private void refresh() {


        if (numOrders.getAdapter().getCount() > 0) {
            Order selected = om.getOrder(Integer.parseInt(numOrders.getSelectedItem().toString()));
            ArrayAdapter<MenuItem> arrayAdapter = new ArrayAdapter<>(this, R.layout.list_view_item, R.id.orderItemsListView, selected.getItems());
            orderItemsListView.setAdapter(arrayAdapter);
            Total.setText(String.format("$%.2f", selected.getTotal()));
        } else {
            Total.setText("0.00");
        }
    }


    protected OrderManager getOM() {
        return om;
    }


    /**
     * Refreshes the page with the appropriate order.
     * Calls the refresh() method to retrieve data from the orderBox.
     */

    public void onOrderNumClick() {
        refresh();
    }

    /**
     * Cancels the currently selected order and refreshes.
     * Handles edge case where there are no orders.
     */

    public void CancelOrderClick() {
        om.removeOrder(order);
        start();
    }

    @Override
    protected OrderManager getStoreOrder() {
        return null;
    }

    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        refresh();
        numOrders.setSelection(i);
    }

    /**
     * Handles the case when no item is selected.
     * @param adapterView default parameter
     */
    public void onNothingSelected(AdapterView<?> adapterView) {}
}
