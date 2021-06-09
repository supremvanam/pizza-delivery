package com.supremvanam.supremvanam_comp304sec002_lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MenuScreenActivity extends AppCompatActivity {

    LoadingDialog loadingDialog;
    SharedPreferences sharedPreferences;
    public static final String SHARED_PREF_NAME = "myPref";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

        // Initializing the custom loading progress bar that I created in LoadingDialog.java file
        loadingDialog = new LoadingDialog(this);
    }

    // Overriding onCreateOptionsMenu to set-up the menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pizza_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, PizzaDetailsActivity.class);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Storing data to SharedPreferences object based on users' selection
        switch (item.getItemId()) {
            case R.id.canadianPizza:
                editor.putInt("pizzaImage",  R.drawable.canadian_pizza_min);
                editor.putString("pizzaTitle", "Canadian Pizza");
                editor.putString("pizzaToppings", "Mozzarella Cheese, Bacon, Pepperoni, and Mushrooms");
                editor.apply();

                intent.putExtra("pizzaPrice", 5.99);
                startActivity(intent);
                break;

            case R.id.caesarPizza:

                editor.putInt("pizzaImage",  R.drawable.caesar_001);
                editor.putString("pizzaTitle", "Chicken Caesar Pizza");
                editor.putString("pizzaToppings", "Chicken Breast, Romaine Lettuce, Croutons, and Parmesan");
                editor.apply();

                intent.putExtra("pizzaPrice", 7.79);
                startActivity(intent);
                break;

            case R.id.hawaiianPizza:
                editor.putInt("pizzaImage",  R.drawable.hawaiian_001);
                editor.putString("pizzaTitle", "Hawaiian Pizza");
                editor.putString("pizzaToppings", "Mozzarella Cheese, Sliced Ham, and Pineapple");
                editor.apply();

                intent.putExtra("pizzaPrice", 6.49);
                startActivity(intent);
                break;

            case R.id.baconPizza:
                editor.putInt("pizzaImage",  R.drawable.bacon_001);
                editor.putString("pizzaTitle", "Smokey Maple Bacon");
                editor.putString("pizzaToppings", "Alfredo Sauce, Maple Bacon Strips, Bacon Crumble, Sliced Mushrooms, and Shredded Cheddar");
                editor.apply();

                intent.putExtra("pizzaPrice", 9.99);
                startActivity(intent);
                break;

            case R.id.veggiePizza:
                editor.putInt("pizzaImage",  R.drawable.veggie_001);
                editor.putString("pizzaTitle", "Veggie Lovers");
                editor.putString("pizzaToppings", "Mushrooms, Green Peppers, Tomatoes, Black Olives, and Onions");
                editor.apply();

                intent.putExtra("pizzaPrice", 5.49);
                startActivity(intent);
                break;
        }
        // Once the user clicks on a menu item, progress bar will start (loading spinner).
        loadingDialog.startLoading();
        return true;
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