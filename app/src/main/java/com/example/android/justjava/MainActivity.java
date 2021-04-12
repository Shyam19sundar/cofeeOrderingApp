/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
int quantity=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText namefield= (EditText) findViewById(R.id.field);
        String value = namefield.getText().toString();
        CheckBox whippedcreambox = (CheckBox) findViewById(R.id.whippped_cream_checkbox);
        boolean haswhippedcreambox= whippedcreambox.isChecked();
        CheckBox chocolate = (CheckBox) findViewById(R.id.chocolate_checkbox);
        boolean haschocolate=chocolate.isChecked();
        int baseprice=5;
        if(haschocolate){
            baseprice+=2;
        }
        if(haswhippedcreambox){
            baseprice+=1;
        }
        int price= baseprice* quantity;
        String priceMessage= "Name : " + value + "\n";
        priceMessage=priceMessage +"Quantity = " + quantity + "\n";
        priceMessage=priceMessage + "Add Whippped Cream ? " + haswhippedcreambox + "\n";
        priceMessage=priceMessage + "Add Chocolate ? " + haschocolate + "\n";
        priceMessage=priceMessage +"Total = $ " + price;
        priceMessage=priceMessage + "\n Thank You!";
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:")); // only email apps should handle this
            intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java Order for " + value);
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }

        displayMessage(priceMessage);

    }

    public void increment(View view) {
        if (quantity <= 99) {
            quantity+=1;
            display(quantity);
        }
        else {
            Context context = getApplicationContext();
            CharSequence text = "Maximum 100 can only be Ordered";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }
    public void decrement(View view) {
        if (quantity > 1) {
            quantity-=1;
            display(quantity);
        }
        else {
            Context context = getApplicationContext();
            CharSequence text = "Atleast 1 Cofee should be Ordered";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
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
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }
}