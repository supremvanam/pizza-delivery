package com.supremvanam.supremvanam_comp304sec002_lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        // Setting the top toolbar title
        this.setTitle("Order Summary");

        // Calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // Showing the back button in action bar
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Connecting the UI elements to their respective fields
        ImageView pizzaImage = findViewById(R.id.cart_pizzaImage);
        TextView pizzaTitle = findViewById(R.id.cart_pizzaTitle);
        TextView pizzaSize = findViewById(R.id.cart_pizzaSize);
        TextView pizzaCrust = findViewById(R.id.cart_pizzaCrust);
        TextView pizzaToppings = findViewById(R.id.cart_pizzaToppings);
        TextView pizzaTotalPrice = findViewById(R.id.txt_totalPriceCardDetails);


        // Retrieving data using shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences(MenuScreenActivity.SHARED_PREF_NAME, MODE_PRIVATE);
        int pizzaImageResource = sharedPreferences.getInt("pizzaImage", R.drawable.canadian_pizza_min);
        pizzaImage.setImageResource(pizzaImageResource);
        pizzaTitle.setText(sharedPreferences.getString("pizzaTitle", null));
        pizzaToppings.setText(sharedPreferences.getString("pizzaToppings", null));
        pizzaSize.setText(sharedPreferences.getString("pizzaSize", "Small"));
        pizzaCrust.setText(sharedPreferences.getString("pizzaCrust", "Thin Crust"));
        pizzaTotalPrice.setText(String.format("%s%s", getString(R.string.str_total_price), sharedPreferences.getString("pizzaPrice", "$10.95")));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // Taking the user back to the previous activity when they click on back button on the top toolbar
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // Starting a new activity when user clicks on next
    public void selectPaymentClicked(View view) {
        Intent intent = new Intent(this, PaymentActivity.class);
        startActivity(intent);
    }
}