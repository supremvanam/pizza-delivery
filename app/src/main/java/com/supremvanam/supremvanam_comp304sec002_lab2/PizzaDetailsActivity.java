package com.supremvanam.supremvanam_comp304sec002_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Objects;

public class PizzaDetailsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private RadioGroup radioGroup;
    private TextView pizzaTitle, pizzaToppings, pizzaPrice;
    private CheckBox addToCardCheckBox;
    private Button orderSummaryButton;
    private int pizzaImageResource;
    private double originalPrice;
    private double calculatedPrice;
    private String pizzaSize = "Small", pizzaCrust = "Thin Crust";
    SharedPreferences sharedPreferences;
    LoadingDialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_details);

        // Hiding the top toolbar -- just to make it look full screen
        Objects.requireNonNull(getSupportActionBar()).hide();


        // Connecting the UI element objects to the UI elements
        ImageView pizzaImage = findViewById(R.id.img_pizzaImage);
        pizzaTitle = findViewById(R.id.txt_pizzaName);
        pizzaToppings = findViewById(R.id.txt_pizzaToppings);
        pizzaPrice = findViewById(R.id.txt_pizzaPrice);
        Spinner spinner = findViewById(R.id.sp_pizzaSizesSpinner);
        radioGroup = findViewById(R.id.rg_pizzaCrust);
        addToCardCheckBox = findViewById(R.id.check_addToCart);
        orderSummaryButton = findViewById(R.id.btn_orderSummary);
        loadingDialog = new LoadingDialog(this);

        // If the checkbox is not checked then the button to go to the next activity will be disabled.
        if (!addToCardCheckBox.isChecked()) {
            orderSummaryButton.setEnabled(false);
        }

        // Using the Array from the string resource file and setting up the pizza sizes spinner.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.pizzaSizes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        // Receiving data from the previous Activity
        Intent menuScreenIntent = getIntent();
        sharedPreferences = getSharedPreferences(MenuScreenActivity.SHARED_PREF_NAME, MODE_PRIVATE);
        pizzaTitle.setText(sharedPreferences.getString("pizzaTitle", null));
        pizzaToppings.setText(sharedPreferences.getString("pizzaToppings", null));
        pizzaImageResource = sharedPreferences.getInt("pizzaImage", R.drawable.canadian_pizza_min);
        originalPrice = menuScreenIntent.getDoubleExtra("pizzaPrice", 5.00);
        pizzaImage.setImageResource(pizzaImageResource);

        // Using String.format because string concatenation is not a good practice when using setText - according to the Android Developers website.
        pizzaPrice.setText(String.format("%s%s", getString(R.string.str_dollar), originalPrice));
    }

    // When back button on the top toolbar is clicked user will be taken to the previous activity.
    public void backButtonClicked(View view) {
        PizzaDetailsActivity.this.finish();
    }

    public void addToCartChecked(View view) {
        // If checkbox is checked then the button to go to the next activity will be enable, otherwise it will be disabled.
        orderSummaryButton.setEnabled(addToCardCheckBox.isChecked());
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();

        switch (choice) {
            case "Medium":
                pizzaSize = "Medium";

                // Assuming the cost of a medium pizza is 1.2 times higher than the small pizza
                calculatedPrice = originalPrice * 1.2;

                // Limiting the decimal places to 2
                calculatedPrice = calculatedPrice * 100;
                calculatedPrice = Math.round(calculatedPrice);
                calculatedPrice = calculatedPrice / 100;

                // I used String.format because String concatenation is not a good practice when using setText - according to the Android Developers website.
                pizzaPrice.setText(String.format("%s%s", getString(R.string.str_dollar), calculatedPrice));

                break;
            case "Large":
                pizzaSize = "Large";

                // Assuming the cost of a large pizza is 1.5 times higher than the small pizza
                calculatedPrice = originalPrice * 1.5;

                // Limiting the decimal places to 2
                calculatedPrice = calculatedPrice * 100;
                calculatedPrice = Math.round(calculatedPrice);
                calculatedPrice = calculatedPrice / 100;
                pizzaPrice.setText(String.format("%s%s", getString(R.string.str_dollar), calculatedPrice));

                break;
            case "Extra Large":
                pizzaSize = "Extra Large";

                // Assuming the cost of an extra large pizza is 2 times higher than the small pizza
                calculatedPrice = originalPrice * 2;

                // Limiting the decimal places to 2
                calculatedPrice = calculatedPrice * 100;
                calculatedPrice = Math.round(calculatedPrice);
                calculatedPrice = calculatedPrice / 100;

                pizzaPrice.setText(String.format("%s%s", getString(R.string.str_dollar), calculatedPrice));
                break;
            default:
                calculatedPrice = originalPrice;

                // Limiting the decimal places to 2
                calculatedPrice = calculatedPrice * 100;
                calculatedPrice = Math.round(calculatedPrice);
                calculatedPrice = calculatedPrice / 100;
                pizzaPrice.setText(String.format("%s%s", getString(R.string.str_dollar), calculatedPrice));
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // This method needs to be overridden to use Spinner.
    }


    // Selecting the radio button and assigning the correct string value so that we can use it later with the help of SharedPreferences.
    public void radioButtonClicked(View view) {
        int radioId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = findViewById(radioId);

        if (radioButton == findViewById(R.id.radioThinCrust)) {
            pizzaCrust = "Thin Crust";
        } else {
            pizzaCrust = "Thick Crust";
        }
    }

    // If user clicks bottom black button, they will be taken to the previous page to select another pizza.
    public void addAnotherPizzaClicked(View view) {
        PizzaDetailsActivity.this.finish();
    }

    public void orderSummaryClicked(View view) {
    //  Saving the user selected data into SharedPreferences when user clicks on order summary button
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

        // Progress bar (loading spinner) starts when user clicks on order summary button
        loadingDialog.startLoading();
    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            // Progress bar will be dismissed just before the activity is stopped (or moved to a different activity)
            loadingDialog.stopLoading();
        } catch (Exception ex) {
            System.out.println("Alert dialog isn't active");
        }
    }
}