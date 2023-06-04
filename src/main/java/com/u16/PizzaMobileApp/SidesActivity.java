package com.u16.PizzaMobileApp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SidesActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sides_layout);
        Log.d("@SidesActivity #START", "Started.");

        Button backBtn = findViewById(R.id.back_btn);
        Button nextBtn = findViewById(R.id.next_btn);
        // chips
        Button chipsIncrement = findViewById(R.id.chips_increment);
        final int[] chipsCount = {0};
        TextView chipsCountDisplay = findViewById(R.id.chips_count);
        Button chipsDecrement = findViewById(R.id.chips_decrement);
        // corn
        Button cornIncrement = findViewById(R.id.corn_increment);
        final int[] cornCount = {0};
        TextView cornCountDisplay = findViewById(R.id.corn_count);
        Button cornDecrement = findViewById(R.id.corn_decrement);
        // beans
        Button beansIncrement = findViewById(R.id.beans_increment);
        final int[] beansCount = {0};
        TextView beansCountDisplay = findViewById(R.id.beans_count);
        Button beansDecrement = findViewById(R.id.beans_decrement);
        // salad
        Button saladIncrement = findViewById(R.id.salad_increment);
        final int[] saladCount = {0};
        TextView saladCountDisplay = findViewById(R.id.salad_count);
        Button saladDecrement = findViewById(R.id.salad_decrement);

        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, ToppingActivity.class);
            Log.d("@SidesActivity #EXIT", "Exited.");
            startActivity(intent);
        });

        nextBtn.setOnClickListener(v -> {
            // updating total price & putting values in sharedpref for later use
            SharedPreferences preferences = getSharedPreferences("com.u16.PizzaMobileApp", MODE_PRIVATE);
            String totalPrice = preferences.getString("total_price", "0");
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("total_price", String.valueOf( Integer.parseInt(totalPrice) + (chipsCount[0] * 2) + (cornCount[0] * 2) + (beansCount[0] * 2) + (saladCount[0] * 2)));
            editor.putInt("chips", chipsCount[0]);
            editor.putInt("corn", cornCount[0]);
            editor.putInt("beans", beansCount[0]);
            editor.putInt("salad", saladCount[0]);
            editor.apply();

            Intent intent = new Intent(this, InputInformationActivity.class);
            Log.d("@SidesActivity #EXIT", "Exited.");
            startActivity(intent);
        });

        // event listeners for sides
        chipsIncrement.setOnClickListener(v -> {
            chipsCount[0]++;
            chipsCountDisplay.setText(String.valueOf(chipsCount[0]));
        });

        chipsDecrement.setOnClickListener(v -> {
            if (chipsCount[0] > 0) {
                chipsCount[0]--;
                chipsCountDisplay.setText(String.valueOf(chipsCount[0]));
            }
        });

        cornIncrement.setOnClickListener(v -> {
            cornCount[0]++;
            cornCountDisplay.setText(String.valueOf(cornCount[0]));
        });

        cornDecrement.setOnClickListener(v -> {
            if (cornCount[0] > 0) {
                cornCount[0]--;
                cornCountDisplay.setText(String.valueOf(cornCount[0]));
            }
        });

        beansIncrement.setOnClickListener(v -> {
            beansCount[0]++;
            beansCountDisplay.setText(String.valueOf(beansCount[0]));
        });

        beansDecrement.setOnClickListener(v -> {
            if (beansCount[0] > 0) {
                beansCount[0]--;
                beansCountDisplay.setText(String.valueOf(beansCount[0]));
            }
        });

        saladIncrement.setOnClickListener(v -> {
            saladCount[0]++;
            saladCountDisplay.setText(String.valueOf(saladCount[0]));
        });

        saladDecrement.setOnClickListener(v -> {
            if (saladCount[0] > 0) {
                saladCount[0]--;
                saladCountDisplay.setText(String.valueOf(saladCount[0]));
            }
        });
    }
}
