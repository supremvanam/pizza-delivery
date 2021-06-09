package com.supremvanam.supremvanam_comp304sec002_lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Hiding the top toolbar -- just to make it look full screen
        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    public void orderButtonClicked(View view) {
        // Moving to the Menu Screen Activity
        Intent intent = new Intent(this, MenuScreenActivity.class);
        startActivity(intent);

    }
}