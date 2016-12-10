
package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int numberOfCoffes = 2;
    int quantity = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        displayPrice(numberOfCoffes * quantity);
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
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        String message = "Total: ";
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message +  NumberFormat.getCurrencyInstance().format(number) + "\n Thank you!");
    }
}