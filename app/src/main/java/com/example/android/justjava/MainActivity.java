/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 1,price = 0;
    boolean Whipping_Cream,Choclate;
    String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder (View view) {
        getName();
        CheckState_whippedcream();
        CheckState_choclate();
        displayQuantity(quantity);
        displayordersummary();
        int price = calculatePrice();
       String summary = createOrderSummary(price, Whipping_Cream);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mail to ...."));
        intent.setType("plain/text");
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.subject));
        intent.putExtra(Intent.EXTRA_TEXT,summary);
        intent.putExtra(Intent.EXTRA_EMAIL,new String[] {"coffeorder@gmail.com"});
        startActivity(Intent.createChooser(intent, "Select an Email client"));



    }

    /**
     * This method decides action for the state of the checkbox
     */
    public void CheckState_whippedcream() {
        final CheckBox checkBox = (CheckBox) findViewById(R.id.topping_checkbox);
        if(checkBox.isChecked()){
            Whipping_Cream = true;
        }
        else
            Whipping_Cream = false;

    }
    public void CheckState_choclate(){
        final CheckBox checkBox = (CheckBox) findViewById(R.id.choclate_checkbox);
        if(checkBox.isChecked()){
            Choclate = true;

        }
        else
            Choclate = false;
    }
    public void getName(){
        EditText edittext = (EditText) findViewById(R.id.name);
        name = edittext.getText().toString();


    }
    /**
     * this mehtod increments the value of quantity acoording to the plus box
     * @param view
     */
    public void increment (View view)   {
        if(quantity < 100) {
            quantity = quantity + 1;
            displayQuantity(quantity);
        }
        else
            Toast.makeText(getApplicationContext(), "Quantity cannot exceed 100",
                    Toast.LENGTH_SHORT).show();
    }
    /**
     * this mehtod decrements the value of quantity acoording to the plus box
     * @param view
     */

    public void decrement (View view)   {
        if(quantity > 1) {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }
            else
            Toast.makeText(getApplicationContext(), "Quantity cannot be less than 1",
                    Toast.LENGTH_SHORT).show();

    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }
    /**
     * This method displays the given price on the screen.
     * @param number
     */
    private void display(String number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(number);
    }
    private void displayordersummary() {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText("Order Summary");
    }

    /**
     *Calculates the total price of the order
     * @return total price
     */
    private int calculatePrice(){
        price = quantity*5;
        if (Whipping_Cream == true && Choclate == true){
            price = price + (3*quantity);
        }

        else if (Choclate == true){
            price = price + (2*quantity);
        }
        else if(Whipping_Cream == true)
        {
            price = price + (1*quantity);
        }
        return price;
    }

    /**
     *
     * @param price
     * @return the order summary
     */
    private String createOrderSummary(int price, boolean whipping_Cream){
        return (getString(R.string.name)+" "+name+ "\n"+getString(R.string.quantity)+" "+quantity+"\n"+getString(R.string.total)+" "+price+"\n Add Whipping Cream ?"+whipping_Cream+"\n Add Choclate ?"+Choclate+"\n"+ getString(R.string.thank_you));
    }

}
