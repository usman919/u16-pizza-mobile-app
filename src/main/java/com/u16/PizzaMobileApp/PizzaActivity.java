package com.u16.PizzaMobileApp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PizzaActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pizza_layout);
        Log.d("@PizzaActivity #START", "Started.");

        Button back = findViewById(R.id.back_btn);
        Button next = findViewById(R.id.next_btn);
        // small
        Button smallIncrement = findViewById(R.id.small_increment);
        final int[] smallCount = {0};
        TextView smallCountDisplay = findViewById(R.id.small_count);
        Button smallDecrement = findViewById(R.id.small_decrement);
        // medium
        Button mediumIncrement = findViewById(R.id.medium_increment);
        final int[] mediumCount = {0};
        TextView mediumCountDisplay = findViewById(R.id.medium_count);
        Button mediumDecrement = findViewById(R.id.medium_decrement);
        // large
        Button largeIncrement = findViewById(R.id.large_increment);
        final int[] largeCount = {0};
        TextView largeCountDisplay = findViewById(R.id.large_count);
        Button largeDecrement = findViewById(R.id.large_decrement);

        back.setOnClickListener(v -> { // back button event listener
            Log.d("@PizzaActivity #EXIT", "Exited.");
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        });

        next.setOnClickListener(v -> { // forward button event listener
            int totalCount = smallCount[0] + mediumCount[0] + largeCount[0];
            if (totalCount <= 0) { // validate to check if they're greater than 0
                Toast.makeText(getApplicationContext(), "Must select a pizza to continue!", Toast.LENGTH_SHORT).show();
            } else {
                // Update the string resource values using SharedPreferences
                SharedPreferences sharedPref = getSharedPreferences("com.u16.PizzaMobileApp", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("pizza_total_count", String.valueOf(totalCount));
                editor.putString("small_pizza_count", String.valueOf(smallCount[0]));
                editor.putString("medium_pizza_count", String.valueOf(mediumCount[0]));
                editor.putString("large_pizza_count", String.valueOf(largeCount[0]));
                editor.apply();

                Log.d("@PizzaActivity #EXIT", "Exited.");
                Intent intent = new Intent(this, ToppingActivity.class);
                startActivity(intent);
            }
        });

        // small event listeners
        smallIncrement.setOnClickListener(v -> {
            smallCount[0]++;
            smallCountDisplay.setText(String.valueOf(smallCount[0]));
        });

        smallDecrement.setOnClickListener(v -> {
            if (smallCount[0] > 0) {
                smallCount[0]--;
                smallCountDisplay.setText(String.valueOf(smallCount[0]));
            }
        });

        // medium event listeners
        mediumIncrement.setOnClickListener(v -> {
            mediumCount[0]++;
            mediumCountDisplay.setText(String.valueOf(mediumCount[0]));
        });

        mediumDecrement.setOnClickListener(v -> {
            if (mediumCount[0] > 0) {
                mediumCount[0]--;
                mediumCountDisplay.setText(String.valueOf(mediumCount[0]));
            }
        });

        // large event listeners
        largeIncrement.setOnClickListener(v -> {
            largeCount[0]++;
            largeCountDisplay.setText(String.valueOf(largeCount[0]));
        });

        largeDecrement.setOnClickListener(v -> {
            if (largeCount[0] > 0) {
                largeCount[0]--;
                largeCountDisplay.setText(String.valueOf(largeCount[0]));
            }
        });

    }
}
