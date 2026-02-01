package com.example.lab2_listycity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;

    Button addButton;
    Button deleteButton;

    int cityNumber = -1;


    private class CityAdapter extends ArrayAdapter<String> {

        private final int HIGHLIGHT_COLOR = Color.parseColor("#E0FFFF");

        public CityAdapter(@NonNull Context context, int resource, @NonNull ArrayList<String> objects) {
            super(context, resource, objects);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = super.getView(position, convertView, parent);

            if (position == cityNumber) {
                view.setBackgroundColor(HIGHLIGHT_COLOR);
            } else {
                view.setBackgroundColor(Color.TRANSPARENT);
            }

            return view;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        addButton = findViewById(R.id.add_button);
        deleteButton = findViewById(R.id.delete_button);

        String[] cities = {"Karachi", "Lahore", "Islamabad", "Peshawar", "Quetta", "Multan",
                           "Faisalabad","Hyderabad", "Rawalpindi", "Sargodha", "Bahawalpur",
                           "Gujrat", "Sukkur", "Larkana", "Chiniot", "Sahiwal", "Jhang",
                           "Gujranwala", "Sialkot"};
        dataList = new ArrayList<String>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new CityAdapter(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        cityList.setOnItemLongClickListener((parent, view, position, id) -> {

            if (cityNumber == position) {
                cityNumber = -1;
                Toast.makeText(MainActivity.this, "Selection cleared.", Toast.LENGTH_SHORT).show();
            } else {
                cityNumber = position;
                Toast.makeText(MainActivity.this, dataList.get(position) + " selected.", Toast.LENGTH_SHORT).show();
            }

            cityAdapter.notifyDataSetChanged();

            return true;
        });

        cityList.setOnItemClickListener((parent, view, position, id) -> {
            if (cityNumber == position) {
                cityNumber = -1;
                Toast.makeText(MainActivity.this, "Selection cleared.", Toast.LENGTH_SHORT).show();

                cityAdapter.notifyDataSetChanged();
            }
        });

        addButton.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Add New City");

            final EditText input = new EditText(this);
            input.setHint("Enter city name");
            builder.setView(input);

            builder.setPositiveButton("Add", (dialog, which) -> {
                String cityName = input.getText().toString().trim();
                if (!cityName.isEmpty()) {
                    dataList.add(cityName);
                    cityAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, cityName + " added.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "City name cannot be empty.", Toast.LENGTH_SHORT).show();
                }
            });

            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());

            builder.show();
        });

        deleteButton.setOnClickListener(v -> {
            if (cityNumber != -1) {
                if (cityNumber >= 0 && cityNumber < dataList.size()) {
                    String deletedCity = dataList.get(cityNumber);

                    dataList.remove(cityNumber);
                    cityAdapter.notifyDataSetChanged();

                    Toast.makeText(MainActivity.this, deletedCity + " deleted.", Toast.LENGTH_SHORT).show();

                    cityNumber = -1;
                } else {
                    cityNumber = -1;
                    cityAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Selection index out of bounds. Please re-select.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(MainActivity.this, "Please long press an item's text to select it for deletion.", Toast.LENGTH_SHORT).show();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}