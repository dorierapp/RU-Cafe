package com.example.project5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.EditText;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;

import com.example.project4.R;

import java.util.ArrayList;

/**
 * Controller for the RU Cafe GUI.
 * Handles onClick functionality for GUI Nodes.
 * @author Ritu Patel, Dorie Rappaport
 */
public class DonutOrderActivity extends AppCompatActivity { //implements AdapterView.OnItemSelectedListener {
    private Order order;
    private ArrayList<Donut> tempOrder;

    private EditText donutQuant;
    private OrderManager orderManager;
    private Global info;

    private TextView subTotalText;

    private RecyclerView recyclerView;

    private RecyclerView orderedDonutsListView;

    private Button addDonutButton;
    private Button removeDonutButton;
    private Button addToOrderDonutButton;
    private Button backButton;
    private ArrayList<Donut> donutList = new ArrayList<Donut>();
    private ImageView image;
    private double tempTotal = 0.00;
    private final double START = 0;
    private final int DEFAULT = 0;
    private final int TYPEQTY = 3;
    private final int YEAST = 0;
    private final int CAKE = 1;
    private final int HOLE = 2;
    private final String IMAGES_PATH = "src/main/resources/img/";
    private final String HOLE_IMAGE = "hole.png";
    private final String CAKE_IMAGE = "cake.png";
    private final String YEAST_IMAGE = "yeast.png";
    private final String[] type = {"Yeast", "Cake", "Hole"};
    private final String[] numDonuts = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
            "11", "12"};


    private Donut newDonut;

    /**
     * Overrides initialize. Initializes the donut ordering view.
     * @param savedInstanceState default parameter
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);

        info = (Global) getApplicationContext();
        orderManager = info.getOrderManager();

        tempOrder = new ArrayList<>();
        newDonut = new Donut();
        //order = (Order) getIntent().getSerializableExtra("STORE");
        //order = storeOrder.getCurrOrder();
        //image = (ImageView) findViewById(R.id.donutImg);
        //donutComboBox = (RecyclerView) findViewByID(R.id.donutSpinner);

        recyclerView = findViewById(R.id.recyclerView);
//        donutQuant = (Spinner) findViewByID(R.id.donutQuant);
        subTotalText = findViewById(R.id.subTotalText);
//        addDonutButton = (Button) findViewByID(R.id.addDonutButton);
//        removeDonutButton = (Button) findViewByID(R.id.removeDonutButton);
//        addToOrderDonutButton = (Button) findViewByID(R.id.addToOrderDonutButton);
//        backButton = (Button) findViewByID(R.id.backButton);

        //image.setImageResource(R.drawable.Donut);

        // populate tempOrder here

        ItemsAdapter adapter = new ItemsAdapter(this,donutList,null);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter.setOnUpdateTotal(new ItemsAdapter.UpdateTotal() {
            @Override
            public void onUpdate() {
                ///int archiveIndex = orderManager.getCurrent();
                String updatedTotal = "Subtotal: $" + String.format("%.2f", tempTotal);
                subTotalText.setText(updatedTotal);
            }
        });
    }
    private int[] donutTypes = {0,1,2};
    private String[] yeastFlavors = {"jelly", "boston creme", "strawberry", "lemon", "vanilla", "almond"};
    private String[] cakeFlavors = {"blueberry", "cruller", "chocolate"};
    private String[] holeFlavors = {"glazed", "jelly", "powdered"};
    private int yeastImage = R.drawable.yeast;
    private int cakeImage = R.drawable.cake;
    private int donutHoleImage = R.drawable.hole;
    private void setUpMenuItems(){
        int image;
        for(int i = 0; i < TYPEQTY; i++) {
            String[] array;
            if (i == YEAST){ array = yeastFlavors; image = yeastImage; }
            else if (i == CAKE){ array = cakeFlavors; image = cakeImage; }
            else { array = holeFlavors; image = donutHoleImage; }
            for(int j = 0; j < array.length; j++) {
                Donut d = new Donut(i, array[j], image);
                donutList.add(d);
            }
        }
    }

    /* @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newDonut = new Donut();
        donutComboBox.getItems().addAll(type);
        donutComboBox.getSelectionModel().selectFirst();
        donutQuant.getItems().addAll(numDonuts);
        try {
            onTypeClick();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        donutQuant.getSelectionModel().selectFirst();
        subTotalText.setText(String.format("$%.2f",START));
        tempOrder = new ArrayList<>();
        refresh();
    }*/

    /**
     * Handles the event of a click for the components in the donut ordering view
     * @throws FileNotFoundException
     */

    /*
    protected void onTypeClick() throws FileNotFoundException {
        donutListView.getItems().clear();
        newDonut.setType(donutComboBox.getSelectionModel().getSelectedIndex());
        donutListView.getItems().addAll(newDonut.getFlavors()[donutComboBox.getSelectionModel().getSelectedIndex()]);
        donutListView.getSelectionModel().selectFirst();
        Image img = new Image(new FileInputStream(IMAGES_PATH + YEAST_IMAGE));
        switch (donutComboBox.getSelectionModel().getSelectedIndex()) {
            case (YEAST):
                img = new Image(new FileInputStream(IMAGES_PATH + YEAST_IMAGE));
                break;
            case (HOLE):
                img = new Image(new FileInputStream(IMAGES_PATH + HOLE_IMAGE));
                break;
            case (CAKE):
                img = new Image(new FileInputStream(IMAGES_PATH + CAKE_IMAGE));
                break;
        }
        image.setImage(img);

    }
*/
//    /**
//     * Gets the current order from the order manager
//     * @param orMan the order manager containing the order of interest
//     */
//    @Override
//    public void initData(OrderManager orMan) {
//        order = orMan.getCurrOrder();
//    }

    /**
     * Adds a donut to the order.
     */
    /*
    @FXML
    protected void AddDButtonClick(){
        newDonut.setFlavor(donutListView.getSelectionModel().getSelectedItem().toString());
        newDonut.setQuantity(Integer.parseInt(donutQuant.getSelectionModel().getSelectedItem().toString()));
        tempOrder.add(newDonut);
        tempTotal += newDonut.itemPrice();
        newDonut = new Donut();
        refresh();
    }
*/
    /**
     * Removes a donut from the order.
     */
    /*
    @FXML
    protected void RemoveDButtonClick(){
        if(orderedDonutsListView.getSelectionModel().getSelectedItem() == null){
            return;
        }
        tempTotal -= ((Donut) orderedDonutsListView.getSelectionModel().getSelectedItem()).itemPrice();
        tempOrder.remove(((Donut) orderedDonutsListView.getSelectionModel().getSelectedItem()));
        refresh();
    }
*/
    /**
     * Adds the selected donuts to the order.
     */
    /*
    @FXML
    protected void AddToOrderDClick() {
        order.addDonutBasket(tempOrder);
        donutListView.getItems().clear();
        orderedDonutsListView.getItems().clear();
        subTotalText.setText(String.format("$%.2f",START));
        donutQuant.getSelectionModel().select(DEFAULT);
        donutComboBox.getSelectionModel().select(DEFAULT);
        tempOrder = new ArrayList<>();
        Stage stage = (Stage) subTotalText.getScene().getWindow();
        stage.close();
    }
*/
    /**
     * Clears the list view and resets the ordering view.
     */
    private void refresh(){
        //orderedDonutsListView.getItems().clear();
        //orderedDonutsListView.getItems().addAll(tempOrder);
        subTotalText.setText(String.format("$%.2f",tempTotal));
        if(tempTotal == START) subTotalText.setText("$0.00");
    }
    public void previous(View view){
        Intent intent = new Intent(view.getContext(), MainController.class);
        startActivity(intent);
    }


//    @Override
//    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//
//    }
//
//    @Override
//    public void onNothingSelected(AdapterView<?> parent) {
//
//    }
}