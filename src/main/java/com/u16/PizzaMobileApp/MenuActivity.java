package com.u16.PizzaMobileApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_layout);
        Log.d("@MenuActivity #START", "Started.");

        Button back_btn = findViewById(R.id.back_btn);
        Button start_btn = findViewById(R.id.start_btn);

        back_btn.setOnClickListener(v -> { // give a small message & close app
            Toast.makeText(getApplicationContext(), "Closed app", Toast.LENGTH_SHORT).show();
            finish();
        });
        start_btn.setOnClickListener(v -> { // proceed to the next area
            Log.d("@MenuActivity #EXIT", "Exited.");
            Intent intent = new Intent(this, PizzaActivity.class);
            startActivity(intent);
        });
    }
}