package com.u16.PizzaMobileApp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

public class ThankYouActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thanks_layout);
        Log.d("@ThankYouActivity #START", "Started.");

        // Retrieve the JSON string from SharedPreferences
        SharedPreferences preferences = getSharedPreferences("com.u16.PizzaMobileApp", MODE_PRIVATE);
        String jsonString = preferences.getString("toppings", "");

        // Convert the JSON string back to a HashMap<String, ArrayList<String>>
        Type type = new TypeToken<HashMap<String, ArrayList<String>>>() {}.getType();
        HashMap<String, ArrayList<String>> toppings = new Gson().fromJson(jsonString, type);

        // get customer info
        String nameVal = preferences.getString("name", "");
        String numberVal = preferences.getString("number", "");
        String addressVal = preferences.getString("address", "");

        Button backBtn = findViewById(R.id.back_btn);
        TextView name = findViewById(R.id.name);
        TextView number = findViewById(R.id.number);
        TextView address = findViewById(R.id.address);
        TextView order = findViewById(R.id.order);

        name.setText(name.getText() + nameVal);
        number.setText(number.getText() + numberVal);
        address.setText(address.getText() + addressVal);

        StringBuilder toppingsStringBuilder = new StringBuilder();

        for (String key : toppings.keySet()) {
            ArrayList<String> toppingList = toppings.get(key);
            String toppingString = TextUtils.join(", ", toppingList); // Convert ArrayList to comma-separated string
            String line = key + ": " + toppingString; // Combine key and topping string
            toppingsStringBuilder.append(line).append("\n");
        }

        int chipsCount = preferences.getInt("chips", 0);
        int cornCount = preferences.getInt("corn", 0);
        int beansCount = preferences.getInt("beans", 0);
        int saladCount = preferences.getInt("salad", 0);

        if (chipsCount > 0) {
            toppingsStringBuilder.append("Chips: ").append(chipsCount).append("\n");
        }

        if (cornCount > 0) {
            toppingsStringBuilder.append("Corn: ").append(cornCount).append("\n");
        }

        if (beansCount > 0) {
            toppingsStringBuilder.append("Beans: ").append(beansCount).append("\n");
        }

        if (saladCount > 0) {
            toppingsStringBuilder.append("Salad: ").append(saladCount).append("\n");
        }

        order.setText(toppingsStringBuilder.toString());

        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, InputInformationActivity.class);
            startActivity(intent);
        });
    }
}
