package com.supremvanam.supremvanam_comp304sec002_lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class PaymentActivity extends AppCompatActivity {

    private Button nextButton;
    private RadioGroup radioGroup;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        sharedPreferences = getSharedPreferences(MenuScreenActivity.SHARED_PREF_NAME, MODE_PRIVATE);

        // Setting the title on the top toolbar
        this.setTitle("Select Payment");

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();

        // showing the back button in action bar
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Connecting the UI Elements to their respective fields
        nextButton = findViewById(R.id.success_homeButton);
        TextView pizzaTotalPrice = findViewById(R.id.txt_totalPriceCardDetails);
        radioGroup = findViewById(R.id.paymentMethods);

        // Retrieving the price information from shared preferences
        pizzaTotalPrice.setText(String.format("%s%s", getString(R.string.str_total_price), sharedPreferences.getString("pizzaPrice", "$10.95")));

        // Disabling the next button in the beginning
        nextButton.setEnabled(false);
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

    // Next button will be enabled when user selects a payment option
    public void paymentRadioButtonClicked(View view) {
        nextButton.setEnabled(true);
    }

    public void nextPaymentClicked(View view) {
        // Connecting to the shared preferences
        sharedPreferences = getSharedPreferences(MenuScreenActivity.SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Intent intent = new Intent(this, CardDetailsActivity.class);

        int radioId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioId);
        nextButton.setEnabled(true);

        // Checking the user's selected payment option and based on that taking user to different activities.
        String selectedPayment;
        if (radioButton == findViewById(R.id.radio_credit)) {
            selectedPayment = "Credit Card";
        } else if (radioButton == findViewById(R.id.radio_debit)) {
            selectedPayment = "Debit Card";
        } else {
            selectedPayment = "Cash";
            intent = new Intent(this, CustomerDetailsActivity.class);
        }
        editor.putString("paymentOption", selectedPayment);
        editor.apply();
        startActivity(intent);
    }
}