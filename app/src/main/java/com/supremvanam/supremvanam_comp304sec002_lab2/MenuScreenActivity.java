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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_screen);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.pizza_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(this, PizzaDetailsActivity.class);
        switch (item.getItemId()) {
            case R.id.canadianPizza:
                intent.putExtra("pizzaImage", R.drawable.canadian_pizza2);
                intent.putExtra("pizzaTitle", "Canadian Pizza");
                intent.putExtra("pizzaToppings", "Mozzarella Cheese, Bacon, Pepperoni, and Mushrooms");
                intent.putExtra("pizzaPrice", 5.99);
                startActivity(intent);
                break;
            case R.id.caesarPizza:
                intent.putExtra("pizzaImage", R.drawable.chicken_caesar_2);
                intent.putExtra("pizzaTitle", "Chicken Caesar Pizza");
                intent.putExtra("pizzaToppings", "Chicken Breast, Romaine Lettuce, Croutons, and Parmesan");
                intent.putExtra("pizzaPrice", 7.79);
                startActivity(intent);
                break;
            case R.id.hawaiianPizza:
                intent.putExtra("pizzaImage", R.drawable.hawaiian_pizza2);
                intent.putExtra("pizzaTitle", "Hawaiian Pizza");
                intent.putExtra("pizzaToppings", "Mozzarella Cheese, Sliced Ham, and Pineapple");
                intent.putExtra("pizzaPrice", 6.49);
                startActivity(intent);
                break;
            case R.id.baconPizza:
                intent.putExtra("pizzaImage", R.drawable.bacon_pizza_2);
                intent.putExtra("pizzaTitle", "Smokey Maple Bacon");
                intent.putExtra("pizzaToppings", "Alfredo Sauce, Maple Bacon Strips, Bacon Crumble, Sliced Mushrooms, and Shredded Cheddar");
                intent.putExtra("pizzaPrice", 9.99);
                startActivity(intent);
                break;
            case R.id.veggiePizza:
                intent.putExtra("pizzaImage", R.drawable.veggie_pizza_2);
                intent.putExtra("pizzaTitle", "Veggie Lovers");
                intent.putExtra("pizzaToppings", "Mushrooms, Green Peppers, Tomatoes, Black olives, and Onions.");
                intent.putExtra("pizzaPrice", 5.49);
                startActivity(intent);
                break;
        }
        return true;
    }
}