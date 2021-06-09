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
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class CardDetailsActivity extends AppCompatActivity {

    private TextView cardholderName, cardNumber, expiryDate, cvv;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_details);
        sharedPreferences = getSharedPreferences(MenuScreenActivity.SHARED_PREF_NAME, MODE_PRIVATE);

        // Setting the top toolbar title
        this.setTitle("Card Details");

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Connecting the UI elements to their respective fields in this class
        TextView pizzaTotalPrice = findViewById(R.id.txt_totalPriceCardDetails);
        cardholderName = findViewById(R.id.txt_cardholderName);
        cardNumber = findViewById(R.id.txt_cardNumber);
        expiryDate = findViewById(R.id.txt_expiryDate);
        cvv = findViewById(R.id.txt_cvv);

        // Setting the total price from shared preferences
        pizzaTotalPrice.setText(String.format("%s%s", getString(R.string.str_total_price), sharedPreferences.getString("pizzaPrice", "$10.95")));

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

    public void nextCardDetailsClicked(View view) {
        sharedPreferences = getSharedPreferences(MenuScreenActivity.SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Letting the user know when they try to click next without entering all the details.
        if(cardholderName.getText().toString().isEmpty() || cardNumber.getText().toString().isEmpty() || expiryDate.getText().toString().isEmpty() || cvv.getText().toString().isEmpty()) {
            Snackbar.make(cardholderName, "Please fill all the details", Snackbar.LENGTH_SHORT).show();
        } else {
            // Storing the card details in the shared preferences
            editor.putString("cardHolderName", cardholderName.getText().toString());
            editor.putString("cardNumber", cardNumber.getText().toString());
            editor.putString("expiryDate", expiryDate.getText().toString());
            editor.apply();

            Intent intent = new Intent(this, CustomerDetailsActivity.class);
            startActivity(intent);
        }
    }
}