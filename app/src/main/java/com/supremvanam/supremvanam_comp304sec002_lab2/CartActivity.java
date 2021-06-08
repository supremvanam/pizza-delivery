package com.supremvanam.supremvanam_comp304sec002_lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class CartActivity extends AppCompatActivity {
    private ImageView pizzaImage;
    private TextView pizzaTitle;
    private TextView pizzaSize;
    private TextView pizzaCrust;
    private TextView pizzaToppings;
    private TextView pizzaTotalPrice;
    private int pizzaImageResource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        this.setTitle("Order Summary");

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        pizzaImage = findViewById(R.id.cart_pizzaImage);
        pizzaTitle = findViewById(R.id.cart_pizzaTitle);
        pizzaSize = findViewById(R.id.cart_pizzaSize);
        pizzaCrust = findViewById(R.id.cart_pizzaCrust);
        pizzaToppings = findViewById(R.id.cart_pizzaToppings);
        pizzaTotalPrice = findViewById(R.id.cart_pizzaPrice);


        SharedPreferences sharedPreferences = getSharedPreferences(MenuScreenActivity.SHARED_PREF_NAME, MODE_PRIVATE);
        pizzaImageResource = sharedPreferences.getInt("pizzaImage", R.drawable.canadian_pizza2);
        pizzaImage.setImageResource(pizzaImageResource);
        pizzaTitle.setText(sharedPreferences.getString("pizzaTitle", null));
        pizzaToppings.setText(sharedPreferences.getString("pizzaToppings", null));
        pizzaSize.setText(sharedPreferences.getString("pizzaSize", "Small"));
        pizzaCrust.setText(sharedPreferences.getString("pizzaCrust", "Thin Crust"));
        pizzaTotalPrice.setText(String.format("%s%s", getString(R.string.str_total_price), sharedPreferences.getString("pizzaPrice", "$10.95")));
//        pizzaTotalPrice.setText(String.format("%s%s", getString(R.string.str_dollar), calculatedPrice));

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}