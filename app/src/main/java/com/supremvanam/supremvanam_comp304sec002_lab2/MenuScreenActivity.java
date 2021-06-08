package com.supremvanam.supremvanam_comp304sec002_lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.Objects;

public class MenuScreenActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    public static final String SHARED_PREF_NAME = "myPref";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pizza_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, PizzaDetailsActivity.class);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        switch (item.getItemId()) {
            case R.id.canadianPizza:

                editor.putInt("pizzaImage",  R.drawable.canadian_pizza2);
                editor.putString("pizzaTitle", "Canadian Pizza");
                editor.putString("pizzaToppings", "Mozzarella Cheese, Bacon, Pepperoni, and Mushrooms");
                editor.apply();

                intent.putExtra("pizzaPrice", 5.99);
                startActivity(intent);
                break;

            case R.id.caesarPizza:

                editor.putInt("pizzaImage",  R.drawable.chicken_caesar_2);
                editor.putString("pizzaTitle", "Chicken Caesar Pizza");
                editor.putString("pizzaToppings", "Chicken Breast, Romaine Lettuce, Croutons, and Parmesan");
                editor.apply();


//                intent.putExtra("pizzaImage", R.drawable.chicken_caesar_2);
//                intent.putExtra("pizzaTitle", "Chicken Caesar Pizza");
//                intent.putExtra("pizzaToppings", "Chicken Breast, Romaine Lettuce, Croutons, and Parmesan");

                intent.putExtra("pizzaPrice", 7.79);
                startActivity(intent);
                break;

            case R.id.hawaiianPizza:
                editor.putInt("pizzaImage",  R.drawable.hawaiian_pizza2);
                editor.putString("pizzaTitle", "Hawaiian Pizza");
                editor.putString("pizzaToppings", "Mozzarella Cheese, Sliced Ham, and Pineapple");
                editor.apply();

                intent.putExtra("pizzaPrice", 6.49);
                startActivity(intent);
                break;

            case R.id.baconPizza:
                editor.putInt("pizzaImage",  R.drawable.bacon_pizza_2);
                editor.putString("pizzaTitle", "Smokey Maple Bacon");
                editor.putString("pizzaToppings", "Alfredo Sauce, Maple Bacon Strips, Bacon Crumble, Sliced Mushrooms, and Shredded Cheddar");
                editor.apply();

                intent.putExtra("pizzaPrice", 9.99);
                startActivity(intent);
                break;

            case R.id.veggiePizza:
                editor.putInt("pizzaImage",  R.drawable.veggie_pizza_2);
                editor.putString("pizzaTitle", "Veggie Lovers");
                editor.putString("pizzaToppings", "Mushrooms, Green Peppers, Tomatoes, Black Olives, and Onions");
                editor.apply();

                intent.putExtra("pizzaPrice", 5.49);
                startActivity(intent);
                break;
        }
        return true;
    }
}