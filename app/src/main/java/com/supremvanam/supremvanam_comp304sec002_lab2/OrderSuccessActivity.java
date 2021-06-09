package com.supremvanam.supremvanam_comp304sec002_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class OrderSuccessActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success);

        // Setting the Top Toolbar Title
        this.setTitle("Order Successful");
        sharedPreferences = getSharedPreferences(MenuScreenActivity.SHARED_PREF_NAME, MODE_PRIVATE);

        // Connecting all the UI Elements to their respective fields in the class
        TextView pizzaTitle = findViewById(R.id.success_pizzaTitle);
        TextView pizzaSize = findViewById(R.id.success_pizzaSize);
        TextView pizzaCrust = findViewById(R.id.success_pizzaCrust);
        TextView pizzaToppings = findViewById(R.id.success_pizzaToppings);
        TextView customerName = findViewById(R.id.success_name);
        TextView customerStreet = findViewById(R.id.success_street);
        TextView customerCity = findViewById(R.id.success_city);
        TextView customerPostalCode = findViewById(R.id.success_postalCode);
        TextView customerPhone = findViewById(R.id.success_Phone);
        TextView customerEmail = findViewById(R.id.newSuccessEmail);
        TextView cardholderName = findViewById(R.id.success_cardholderName);
        TextView cardNumber = findViewById(R.id.success_cardNumber);
        TextView cardExpiry = findViewById(R.id.success_Expiry);
        TextView totalPrice = findViewById(R.id.success_totalPrice);
        TextView favPizza = findViewById(R.id.success_favPizza);
        TextView favTopping = findViewById(R.id.success_favTopping);
        String checkingIfCashPayment;
        totalPrice.setText(String.format("%s%s", getString(R.string.str_total_price), sharedPreferences.getString("pizzaPrice", "$10.95")));

        // Retrieving all the data from shared preferences
        pizzaTitle.setText(sharedPreferences.getString("pizzaTitle", "Canadian Pizza"));
        pizzaSize.setText(sharedPreferences.getString("pizzaSize", "Small"));
        pizzaCrust.setText(sharedPreferences.getString("pizzaCrust", "Thin Crust"));
        pizzaToppings.setText(sharedPreferences.getString("pizzaToppings", "Chilli Flakes, Oregano"));
        customerName.setText(sharedPreferences.getString("customerName", "Centennial"));
        customerStreet.setText(sharedPreferences.getString("customerStreet", "Progress Ave."));
        customerCity.setText(sharedPreferences.getString("customerCity", "Toronto"));
        customerPostalCode.setText(sharedPreferences.getString("customerPostalCode", "5Y1 1A3"));

        // Using String.format instead of String Concatenation because it is recommended by Android Developers.
        customerPhone.setText(String.format("Phone: %s", sharedPreferences.getString("customerPhone", "4381223122")));
        customerEmail.setText(String.format("Email: %s", sharedPreferences.getString("customerEmail", "centenpizza@gmail.com")));
        favPizza.setText(String.format("Favourite Pizza: %s", sharedPreferences.getString("customerFavPizza", "Chicken Barbecue Pizza")));
        favTopping.setText(String.format("Favourite Topping: %s", sharedPreferences.getString("customerFavTopping", "Jalapenos")));

        checkingIfCashPayment = sharedPreferences.getString("cardHolderName", null);
        if(checkingIfCashPayment == null) {
            cardholderName.setText(R.string.str_paid_by_cash);
        } else {
            // Using String.format instead of String Concatenation because it is recommended by Android Developers.
            cardholderName.setText(String.format("Cardholder Name: %s", sharedPreferences.getString("cardHolderName", null)));
            cardNumber.setText(String.format("Card Number: %s", sharedPreferences.getString("cardNumber", null)));
            cardExpiry.setText(String.format("Expiry: %s", sharedPreferences.getString("expiryDate", null)));
        }


        totalPrice.setText(String.format("%s%s", getString(R.string.str_total_price), sharedPreferences.getString("pizzaPrice", "$10.95")));
    }

    public void successHomeClicked(View view) {
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Clearing all the data from SharedPreferences so that we can start fresh when we want to order again
        editor.clear();
        editor.apply();

        // Finishing the activity
        this.finish();

        // Redirecting the user to the home screen
        startActivity(new Intent(this, MainActivity.class));
    }
}