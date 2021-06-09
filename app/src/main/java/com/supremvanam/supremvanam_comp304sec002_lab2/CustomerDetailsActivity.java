package com.supremvanam.supremvanam_comp304sec002_lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;

public class CustomerDetailsActivity extends AppCompatActivity {

    private TextView name, street, city, postalCode, phoneNumber, emailAddress, favPizza, favTopping;
    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);

        // Setting the top toolbar title
        this.setTitle("Customer Details");

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);
        sharedPreferences = getSharedPreferences(MenuScreenActivity.SHARED_PREF_NAME, MODE_PRIVATE);

        // Connecting the UI elements to their respective fields in this class
        name = findViewById(R.id.customerName);
        street = findViewById(R.id.customerStreet);
        city = findViewById(R.id.customerCity);
        postalCode = findViewById(R.id.customerPostalCode);
        phoneNumber = findViewById(R.id.customerPhone);
        emailAddress = findViewById(R.id.customer_email);
        favPizza = findViewById(R.id.customerFavPizza);
        favTopping = findViewById(R.id.customerFavTopping);
        TextView totalPrice = findViewById(R.id.customer_totalPrice);

        // Using String.format because string concatenation is not recommended by Android Developers Website.
        totalPrice.setText(String.format("%s%s", getString(R.string.str_total_price), sharedPreferences.getString("pizzaPrice", "$10.95")));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // The user will be taken to the previous activity when they click back button on the top toolbar.
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void customerCheckoutClicked(View view) {

        sharedPreferences = getSharedPreferences(MenuScreenActivity.SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Letting the user know when they try to click next without entering all the details.
        if(name.getText().toString().isEmpty() || street.getText().toString().isEmpty() || city.getText().toString().isEmpty() || postalCode.getText().toString().isEmpty() || phoneNumber.getText().toString().isEmpty() || emailAddress.getText().toString().isEmpty() || favPizza.getText().toString().isEmpty() || favTopping.getText().toString().isEmpty()) {
            Snackbar.make(name, "Please fill all the details", Snackbar.LENGTH_SHORT).show();
        } else {
            // Storing the customer details in the shared preferences
            editor.putString("customerName", name.getText().toString());
            editor.putString("customerStreet", street.getText().toString());
            editor.putString("customerCity", city.getText().toString());
            editor.putString("customerPostalCode", postalCode.getText().toString());
            editor.putString("customerPhone", phoneNumber.getText().toString());
            editor.putString("customerEmail", emailAddress.getText().toString());
            editor.putString("customerFavPizza", favPizza.getText().toString());
            editor.putString("customerFavTopping", favTopping.getText().toString());
            editor.apply();

            startActivity(new Intent(this, OrderSuccessActivity.class));
        }
    }
}