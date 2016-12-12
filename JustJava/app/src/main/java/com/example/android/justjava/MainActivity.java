
package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {

    int numberOfCoffes = 0;
    int coffePrice = 5;
    int totalPrice = 0;
    boolean cream = false;
    final static int HAVE_CREAM = 5;
    final static int NO_CREAM = 0;
    int creamPrice = NO_CREAM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        displayPrice();

    }

    /**
     * This method is called when the + and - button is clicked.
     */

    public void increment(View view) {

        numberOfCoffes++;
        display(numberOfCoffes);

    }

    public void decrement(View view) {

        if (numberOfCoffes == 0) {
            numberOfCoffes = 0;
        } else {

            numberOfCoffes = numberOfCoffes - 1;

            display(numberOfCoffes);
        }
    }

    /**
     * This method is called to check whether or not put in Cream
     */
    public boolean creamCheck(View v){

        cream = ((CheckBox)v).isChecked();
        if (cream){
            creamPrice = HAVE_CREAM;
        }else{
            creamPrice = NO_CREAM;
        }
        return cream;
    }
    /**
     * This method is used to calculate the price of order
     */

    private int calculatePrice(int quantity, int price, int creamPrice) {

        return totalPrice = totalPrice + quantity * (price+creamPrice);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice() {

        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(toString());
    }

    @Override


    public String toString() {

        String message = "";
        message = "Name: Dang Hoai Son\n";
        message = message + "Add Cream: " + cream;
        message = message + "\nQuantity: " + numberOfCoffes + "\n";
        message = message + "Total: $" + calculatePrice(numberOfCoffes, coffePrice,creamPrice);
        Log.e("MainActivity", "Cream Price is " + creamPrice );
        message = message + "\nThank you!";
        return message;
    }
}