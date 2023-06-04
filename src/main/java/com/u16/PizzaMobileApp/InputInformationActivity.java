package com.u16.PizzaMobileApp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class InputInformationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.input_layout);
        Log.d("@InputInformationActivity #START", "Started.");

        Button backBtn = findViewById(R.id.back_btn);
        Button nextBtn = findViewById(R.id.next_btn);
        TextView totalPrice = findViewById(R.id.total_price);
        EditText name = findViewById(R.id.input_name);
        EditText number = findViewById(R.id.input_number);
        EditText address = findViewById(R.id.input_address);

        SharedPreferences pref = getSharedPreferences("com.u16.PizzaMobileApp", MODE_PRIVATE);
        // get total price
        int totalPriceVal = Integer.parseInt(pref.getString("total_price", String.valueOf(0)));
        totalPrice.setText("Total price: £" + totalPriceVal);

        backBtn.setOnClickListener(v -> {
            Intent intent = new Intent(this, SidesActivity.class);
            Log.d("@InputInformationActivity #EXIT", "Exited.");
            startActivity(intent);
        });

        nextBtn.setOnClickListener(v -> {
            String nameText = name.getText().toString().trim();
            String numberText = number.getText().toString().trim();
            String addressText = address.getText().toString().trim();

            if (nameText.length() < 3) {
                Toast.makeText(this, "Name must be greater than 3 letters", Toast.LENGTH_SHORT).show();
                return;
            }

            if (numberText.length() < 10) {
                Toast.makeText(this, "Number must be greater than 10 digits", Toast.LENGTH_SHORT).show();
                return;
            }

            if (addressText.length() < 5) {
                Toast.makeText(this, "Address must be bigger than 5 letters", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validation passed, save the values to SharedPreferences
            SharedPreferences preferences = getSharedPreferences("com.u16.PizzaMobileApp", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("name", nameText);
            editor.putString("number", numberText);
            editor.putString("address", addressText);
            editor.apply();

            Intent intent = new Intent(this, ThankYouActivity.class);
            Log.d("@InputInformationActivity #EXIT", "Exited.");
            startActivity(intent);
        });

    }
}
