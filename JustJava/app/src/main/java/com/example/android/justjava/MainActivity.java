
package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

import static android.R.id.message;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {

    int numberOfCoffes = 1;
    int coffePrice = 5;
    int totalPrice = 0;
    String message;
    final static int HAVE_CREAM = 1;
    final static int HAVE_CHOCOLATE = 2;

    boolean hasCreamOrNot;
    boolean hasChocolateOrNot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox hasCream = (CheckBox) findViewById(R.id.topping);
        hasCreamOrNot = hasCream.isChecked();

        CheckBox hasChocolate = (CheckBox) findViewById(R.id.chocolate);
        hasChocolateOrNot = hasChocolate.isChecked();

        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);

        displayPrice();

        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT,getString(R.string.order));
        intent.putExtra(Intent.EXTRA_TEXT,orderSummaryTextView.getText().toString());
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method is called when the + and - button is clicked.
     */

    public void increment(View view) {

        numberOfCoffes++;
        display(numberOfCoffes);

    }

    public void decrement(View view) {

        if (numberOfCoffes == 1) {

            Toast.makeText(this, getString(R.string.lower_coffee_limit), Toast.LENGTH_SHORT).show();

            return;
        } else {

            numberOfCoffes = numberOfCoffes - 1;

            display(numberOfCoffes);
        }
    }


    /**
     * This method is used to calculate the price of order
     */

    private int calculatePrice(int quantity, int price, boolean hasCream, boolean hasChocolate) {

        int basePrice = price;

        if (hasCream) {
            basePrice = basePrice + HAVE_CREAM;
        }

        if (hasChocolate) {
            basePrice = basePrice + HAVE_CHOCOLATE;
        }

        return totalPrice = quantity * basePrice;

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
        orderSummaryTextView.setText(orderPrint());
    }



    public String orderPrint() {

        EditText name = (EditText) findViewById(R.id.name_view);


        String message = "";
        message = getString(R.string.name) + " " + name.getText().toString();
        message = message + "\n" + getString(R.string.order_summary_whipped_cream) + " " + hasCreamOrNot;
        message = message + "\n" + getString(R.string.order_summary_chocolate) + " " + hasChocolateOrNot;
        message = message + "\n" + getString(R.string.order_summary_quantity) + ": " + numberOfCoffes + "\n";
        message = message + getString(R.string.order_summary_price) + " " + NumberFormat.getCurrencyInstance().format(calculatePrice(numberOfCoffes, coffePrice, hasCreamOrNot, hasChocolateOrNot));
        message = message + "\n" + getString(R.string.thank_you);


        return message;

    }
}