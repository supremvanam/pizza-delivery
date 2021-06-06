package com.supremvanam.supremvanam_comp304sec002_lab2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
        switch (item.getItemId()) {
            case R.id.canadianPizza:
                Toast.makeText(MenuScreenActivity.this, "Canadian clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.caesarPizza:
                Toast.makeText(MenuScreenActivity.this, "Caesar clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.hawaiianPizza:
                Toast.makeText(MenuScreenActivity.this, "Hawaii clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.baconPizza:
                Toast.makeText(MenuScreenActivity.this, "Bacon clicked", Toast.LENGTH_SHORT).show();
                break;
            case R.id.veggiePizza:
                Toast.makeText(MenuScreenActivity.this, "Veggie clicked", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}