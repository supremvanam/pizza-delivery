package com.supremvanam.supremvanam_comp304sec002_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class PizzaDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_details);
        Objects.requireNonNull(getSupportActionBar()).hide();

    }

    public void backButtonClicked(View view) {
        PizzaDetailsActivity.this.finish();
    }
}