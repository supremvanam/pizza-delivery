package com.supremvanam.supremvanam_comp304sec002_lab2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Objects;

public class PizzaDetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private ImageView pizzaImage;
    private TextView pizzaTitle, pizzaToppings, pizzaPrice;
    private CheckBox addToCardCheckBox;
    private Button orderSummaryButton;
    private int pizzaImageResource;
    private double originalPrice;
    private double calculatedPrice;
    private String pizzaSize = "Small";
    private String pizzaCrust = "Thin Crust";
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_details);
        Objects.requireNonNull(getSupportActionBar()).hide();


        // Connecting the UI element objects to the UI elements
        pizzaImage = findViewById(R.id.img_pizzaImage);
        pizzaTitle = findViewById(R.id.txt_pizzaName);
        pizzaToppings = findViewById(R.id.txt_pizzaToppings);
        pizzaPrice = findViewById(R.id.txt_pizzaPrice);
        spinner = findViewById(R.id.sp_pizzaSizesSpinner);
        radioGroup = findViewById(R.id.rg_pizzaCrust);
        addToCardCheckBox = findViewById(R.id.check_addToCart);
        orderSummaryButton = findViewById(R.id.btn_orderSummary);

        if (!addToCardCheckBox.isChecked()) {
            orderSummaryButton.setEnabled(false);
        }

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.pizzaSizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        // Receiving details from the previous Activity
        Intent menuScreenIntent = getIntent();
        sharedPreferences = getSharedPreferences(MenuScreenActivity.SHARED_PREF_NAME, MODE_PRIVATE);
        pizzaTitle.setText(sharedPreferences.getString("pizzaTitle", null));
        pizzaToppings.setText(sharedPreferences.getString("pizzaToppings", null));
        pizzaImageResource = sharedPreferences.getInt("pizzaImage", R.drawable.canadian_pizza2);
        originalPrice = menuScreenIntent.getDoubleExtra("pizzaPrice", 5.00);
        pizzaImage.setImageResource(pizzaImageResource);

        // String concatenation is not a good practice when using setText - according to the Android Developers website.
        pizzaPrice.setText(String.format("%s%s", getString(R.string.str_dollar), originalPrice));



    }

    public void backButtonClicked(View view) {
        PizzaDetailsActivity.this.finish();
    }

    public void addToCartChecked(View view) {

        if (!addToCardCheckBox.isChecked()) {
            orderSummaryButton.setEnabled(false);
        } else {
            orderSummaryButton.setEnabled(true);
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();

        // I used String.format because String concatenation is not a good practice when using setText - according to the Android Developers website.

        if (choice.equals("Medium")) {
            pizzaSize = "Medium";
            calculatedPrice = originalPrice * 1.2;
            // Assuming the cost of a medium pizza is 1.2 times higher than the small pizza
            pizzaPrice.setText(String.format("%s%s", getString(R.string.str_dollar), new DecimalFormat("0.00"). format(calculatedPrice)));

        } else if (choice.equals("Large")) {
            pizzaSize = "Large";
            calculatedPrice = originalPrice * 1.5;
            // Assuming the cost of a large pizza is 1.5 times higher than the small pizza
            pizzaPrice.setText(String.format("%s%s", getString(R.string.str_dollar), new DecimalFormat("0.00"). format(calculatedPrice)));

        } else if (choice.equals("Extra Large")) {
            pizzaSize = "Extra Large";
            calculatedPrice = originalPrice * 2;

            // Assuming the cost of an extra large pizza is 2 times higher than the small pizza
            pizzaPrice.setText(String.format("%s%s", getString(R.string.str_dollar), new DecimalFormat("0.00"). format(calculatedPrice)));
        } else {
            calculatedPrice = originalPrice;
            pizzaPrice.setText(String.format("%s%s", getString(R.string.str_dollar), calculatedPrice));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public void radioButtonClicked(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);

        if (radioButton == findViewById(R.id.radioThinCrust)) {
            pizzaCrust = "Thin Crust";
        } else {
            pizzaCrust = "Thick Crust";
        }
    }

    public void addAnotherPizzaClicked(View view) {
        PizzaDetailsActivity.this.finish();
    }

    public void orderSummaryClicked(View view) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("pizzaTitle", pizzaTitle.getText().toString());
        editor.putInt("pizzaImage", pizzaImageResource);
        editor.putString("pizzaToppings", pizzaToppings.getText().toString());
        editor.putString("pizzaPrice", ""+calculatedPrice);
        editor.putString("pizzaSize", pizzaSize);
        editor.putString("pizzaCrust", pizzaCrust);
        editor.apply();


        Intent intent = new Intent(this, CartActivity.class);
        startActivity(intent);
    }
}