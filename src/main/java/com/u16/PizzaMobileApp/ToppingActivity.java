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

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class ToppingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.topping_layout);
        Log.d("@ToppingActivity #START", "Started.");

        Button backBtn = findViewById(R.id.back_btn);
        Button nextBtn = findViewById(R.id.next_btn);
        TextView pizzaCount = findViewById(R.id.pizza_count);
        Button nextPizzaBtn = findViewById(R.id.next_pizza_btn);
        // Mushroom topping
        Button mushroomIncrement = findViewById(R.id.mushroom_increment);
        final int[] mushroomCount = {0};
        TextView mushroomCountDisplay = findViewById(R.id.mushroom_count);
        Button mushroomDecrement = findViewById(R.id.mushroom_decrement);
        // Sweetcorn topping
        Button sweetcornIncrement = findViewById(R.id.sweetcorn_increment);
        final int[] sweetcornCount = {0};
        TextView sweetcornCountDisplay = findViewById(R.id.sweetcorn_count);
        Button sweetcornDecrement = findViewById(R.id.sweetcorn_decrement);
        // Extra Cheese topping
        Button extraCheeseIncrement = findViewById(R.id.extra_cheese_increment);
        final int[] extraCheeseCount = {0};
        TextView extraCheeseCountDisplay = findViewById(R.id.extra_cheese_count);
        Button extraCheeseDecrement = findViewById(R.id.extra_cheese_decrement);
        // Chicken topping
        Button chickenIncrement = findViewById(R.id.chicken_increment);
        final int[] chickenCount = {0};
        TextView chickenCountDisplay = findViewById(R.id.chicken_count);
        Button chickenDecrement = findViewById(R.id.chicken_decrement);

        // Retrieve the count values from SharedPreferences
        SharedPreferences sharedPref = getSharedPreferences("com.u16.PizzaMobileApp", Context.MODE_PRIVATE);
        int totalCount = Integer.parseInt(sharedPref.getString("pizza_total_count", "0"));
        int smallCount = Integer.parseInt(sharedPref.getString("small_pizza_count", "0"));
        int mediumCount = Integer.parseInt(sharedPref.getString("medium_pizza_count", "0"));
        int largeCount = Integer.parseInt(sharedPref.getString("large_pizza_count", "0"));

        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(ToppingActivity.this, PizzaActivity.class);
            startActivity(intent);
        });

        // Mushroom increment button listener
        mushroomIncrement.setOnClickListener(v -> {
            mushroomCount[0]++;
            mushroomCountDisplay.setText(String.valueOf(mushroomCount[0]));
        });

        // Mushroom decrement button listener
        mushroomDecrement.setOnClickListener(v -> {
            if (mushroomCount[0] > 0) {
                mushroomCount[0]--;
                mushroomCountDisplay.setText(String.valueOf(mushroomCount[0]));
            }
        });

        // Sweetcorn increment button listener
        sweetcornIncrement.setOnClickListener(v -> {
            sweetcornCount[0]++;
            sweetcornCountDisplay.setText(String.valueOf(sweetcornCount[0]));
        });

        // Sweetcorn decrement button listener
        sweetcornDecrement.setOnClickListener(v -> {
            if (sweetcornCount[0] > 0) {
                sweetcornCount[0]--;
                sweetcornCountDisplay.setText(String.valueOf(sweetcornCount[0]));
            }
        });

        // Extra Cheese increment button listener
        extraCheeseIncrement.setOnClickListener(v -> {
            extraCheeseCount[0]++;
            extraCheeseCountDisplay.setText(String.valueOf(extraCheeseCount[0]));
        });

        // Extra Cheese decrement button listener
        extraCheeseDecrement.setOnClickListener(v -> {
            if (extraCheeseCount[0] > 0) {
                extraCheeseCount[0]--;
                extraCheeseCountDisplay.setText(String.valueOf(extraCheeseCount[0]));
            }
        });

        // Chicken increment button listener
        chickenIncrement.setOnClickListener(v -> {
            chickenCount[0]++;
            chickenCountDisplay.setText(String.valueOf(chickenCount[0]));
        });

        // Chicken decrement button listener
        chickenDecrement.setOnClickListener(v -> {
            if (chickenCount[0] > 0) {
                chickenCount[0]--;
                chickenCountDisplay.setText(String.valueOf(chickenCount[0]));
            }
        });

        int totalPrice = 0;
        // calc total price
        if (smallCount > 0) {
            for (int i = 0; i < smallCount; i++) {
                totalPrice += 8;
            }
        }
        if (mediumCount > 0) {
            for (int i = 0; i < mediumCount; i++) {
                totalPrice += 10;
            }
        }
        if (largeCount > 0) {
            for (int i = 0; i < largeCount; i++) {
                totalPrice += 14;
            }
        }
        // loop
        final int[] count = {0};
        final String[] currentSize = {""};
        currentSize[0] = getCurrentPizzaSize(count[0], totalCount, smallCount, mediumCount, largeCount);
        HashMap<String, ArrayList<String>> toppings = new HashMap<>(); // to store all toppings & sizes
        final String[] pizzaCountText = {getString(R.string.topping_pizzas_remaining) + (totalCount - count[0])};
        pizzaCount.setText(pizzaCountText[0]); // display pizza count

        nextPizzaBtn.setOnClickListener(v -> {
            ArrayList<String> toppingList = new ArrayList<>();
            if (mushroomCount[0] > 0) {
                toppingList.add("mushroom");
                mushroomCount[0] = 0;
            }
            if (sweetcornCount[0] > 0) {
                toppingList.add("sweet corn");
                sweetcornCount[0] = 0;
            }
            if (extraCheeseCount[0] > 0) {
                toppingList.add("extra cheese");
                extraCheeseCount[0] = 0;
            }
            if (chickenCount[0] > 0) {
                toppingList.add("chicken");
                chickenCount[0] = 0;
            }

            // updating some variables
            count[0]++;
            currentSize[0] = getCurrentPizzaSize(count[0], totalCount, smallCount, mediumCount, largeCount);

            if (totalCount - count[0] > 0) { // this calculates remaining count
                Toast.makeText(getApplicationContext(), "Please press the > arrow to continue", Toast.LENGTH_SHORT).show();
            } else {
                pizzaCountText[0] = getString(R.string.topping_pizzas_remaining) + (totalCount - count[0]);
                pizzaCount.setText(pizzaCountText[0]);
            }

            toppings.put(currentSize[0], toppingList);
        });


        int finalTotalPrice = totalPrice;
        nextBtn.setOnClickListener(v -> {
            if (count[0] == totalCount) {
                // Convert HashMap to JSON string
                String jsonString = new Gson().toJson(toppings);

                // Store the JSON string in SharedPreferences
                SharedPreferences preferences = getSharedPreferences("com.u16.PizzaMobileApp", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("toppings", jsonString);

                // storing total price
                editor.putString("total_price", String.valueOf(finalTotalPrice));
                editor.apply();

                Intent intent = new Intent(this, SidesActivity.class);
                Log.d("@ToppingActivity #EXIT", "Exited.");
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Please press the > arrow to continue", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // function to do the calculation for us
    private String getCurrentPizzaSize(int count, int totalCount, int smallCount, int mediumCount, int largeCount) {
        int remainingCount = totalCount - smallCount - mediumCount - largeCount;

        if (count <= smallCount) {
            return "Small";
        } else if (count <= smallCount + mediumCount) {
            return "Medium";
        } else {
            return "Large";
        }
    }

}
