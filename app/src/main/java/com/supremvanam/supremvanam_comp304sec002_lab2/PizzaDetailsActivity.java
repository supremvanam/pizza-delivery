package com.supremvanam.supremvanam_comp304sec002_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
    private TextView pizzaTitle;
    private TextView pizzaToppings;
    private TextView pizzaPrice;
    private double calculatedPrice;

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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.pizzaSizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        // Receiving details from the previous Activity
        Intent menuScreenIntent = getIntent();
        calculatedPrice = menuScreenIntent.getDoubleExtra("pizzaPrice", 5.00);
        pizzaImage.setImageResource(menuScreenIntent.getIntExtra("pizzaImage", R.drawable.chicken_caesar));
        pizzaTitle.setText(menuScreenIntent.getStringExtra("pizzaTitle"));
        pizzaToppings.setText(menuScreenIntent.getStringExtra("pizzaToppings"));

        // String concatenation is not a good practice when using setText - according to the Android Developers website.
        pizzaPrice.setText(String.format("%s%s", getString(R.string.str_dollar), calculatedPrice));
    }

    public void backButtonClicked(View view) {
        PizzaDetailsActivity.this.finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();

        // I used String.format because String concatenation is not a good practice when using setText - according to the Android Developers website.

        if (choice.equals("Medium")) {

            // Assuming the cost of a medium pizza is 1.2 times higher than the small pizza
            pizzaPrice.setText(String.format("%s%s", getString(R.string.str_dollar), new DecimalFormat("0.00"). format(calculatedPrice*1.2)));

        } else if (choice.equals("Large")) {

            // Assuming the cost of a medium pizza is 1.5 times higher than the small pizza
            pizzaPrice.setText(String.format("%s%s", getString(R.string.str_dollar), new DecimalFormat("0.00"). format(calculatedPrice*1.5)));

        } else if (choice.equals("Extra Large")) {

            // Assuming the cost of a medium pizza is 2 times higher than the small pizza
            pizzaPrice.setText(String.format("%s%s", getString(R.string.str_dollar), new DecimalFormat("0.00"). format(calculatedPrice*2)));
        } else {
            pizzaPrice.setText(String.format("%s%s", getString(R.string.str_dollar), calculatedPrice));
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }


    public void radioButtonClicked(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        Toast.makeText(this, "Selected Radio Button: "+radioButton.getText(), Toast.LENGTH_SHORT).show();
    }
}