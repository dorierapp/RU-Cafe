package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.CheckBox;
import android.widget.RadioButton;



import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Controller class for the coffee ordering view.
 * @author Ritu Patel, Dorie Rappaport
 */
public class CoffeeOrderActivity extends AppCompatActivity {

    private final String[] QUANT = {"1", "2", "3", "4", "5"};
    private ArrayList<Coffee> tempOrder;
    private Order order;


    private Spinner numCoffee;

    private TextView subTotalText2;

  //  private ToggleGroup cupSizeToggleGroup;

    private ListView orderedCoffeeListView;

    private CheckBox sweetCreamCheckBox;

    private CheckBox mochaCheckBox;

    private CheckBox irishCreamCheckBox;

    private CheckBox frenchVanillaCheckBox;

    private CheckBox caramelCheckBox;

    private RadioButton shortBut;

    private RadioButton tallBut;

    private RadioButton grandeBut;

    private RadioButton ventiBut;


    private double tempTotal = 0.00;
    private final double START = 0;
    private final int DEFAULT = 0;

    private Coffee newCoffee;

    /**
     * Initializes the coffee ordering view.
     * @param url Uniform Resource Locator
     * @param resourceBundle Resource Bundle
     */

    public void initialize(URL url, ResourceBundle resourceBundle) {
        newCoffee = new Coffee();
        numCoffee.getItems().addAll(QUANT);
        numCoffee.getSelectionModel().selectFirst();
        subTotalText2.setText(String.format("$%.2f",START));
        tempOrder = new ArrayList<>();
        refresh();
    }

    /**
     * Adds the specified coffee to the list.
     */
    
    protected void AddButtonClick(){

        if(sweetCreamCheckBox.isSelected()) {
            newCoffee.addIns.add("Sweet Cream");
        }
        if(frenchVanillaCheckBox.isSelected()) {
            newCoffee.addIns.add("French Vanilla");
        }
        if(caramelCheckBox.isSelected()) {
            newCoffee.addIns.add("Caramel");
        }
        if(mochaCheckBox.isSelected()) {
            newCoffee.addIns.add("Mocha");
        }
        if(irishCreamCheckBox.isSelected()) {
            newCoffee.addIns.add("Irish Cream");
        }

        if(!shortBut.isSelected() && !tallBut.isSelected()
                && !grandeBut.isSelected() && !ventiBut.isSelected())
            return;

        newCoffee.setSize(Size.valueOf(((RadioButton) cupSizeToggleGroup.getSelectedToggle()).getText().toUpperCase()));
        newCoffee.setQuantity(Integer.parseInt(numCoffee.getSelectionModel().getSelectedItem().toString()));
        tempOrder.add(newCoffee);
        tempTotal += newCoffee.itemPrice();
        newCoffee = new Coffee();
        refresh();
    }

    /**
     * Removes the selected coffee from the list.
     */
    @FXML
    protected void RemoveButtonClick(){
        if(orderedCoffeeListView.getSelectionModel().getSelectedItem() == null){
            return;
        }
        tempTotal -= ((Coffee) orderedCoffeeListView.getSelectionModel().getSelectedItem()).itemPrice();
        tempOrder.remove(((Coffee) orderedCoffeeListView.getSelectionModel().getSelectedItem()));
        refresh();

    }

    /**
     * Clears the list and resets the subtotal.
     */
    private void refresh(){
        sweetCreamCheckBox.setSelected(false);
        frenchVanillaCheckBox.setSelected(false);
        caramelCheckBox.setSelected(false);
        mochaCheckBox.setSelected(false);
        irishCreamCheckBox.setSelected(false);
        orderedCoffeeListView.getItems().clear();
        orderedCoffeeListView.getItems().addAll(tempOrder);
        subTotalText2.setText(String.format("$%.2f",tempTotal));
        if(tempTotal == START) subTotalText2.setText("$0.00");


    }

    /**
     * Adds the coffees in the list to the order.
     */

    protected void AddToOrderClick() {
        order.addCoffeeBasket(tempOrder);
        orderedCoffeeListView.getItems().clear();
        subTotalText2.setText(String.format("$%.2f",START));
        numCoffee.getSelectionModel().select(DEFAULT);
        tempOrder = new ArrayList<>();
        Stage stage = (Stage) subTotalText2.getScene().getWindow();
        stage.close();
    }

    /**
     * Adds the order to the order manager.
     * @param orMan the order manager containing the order.
     */
    @Override
    public void initData(OrderManager orMan) {
        order = orMan.getCurrOrder();
    }
}
