package com.example.androiduitesting;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ShowActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        TextView textView = findViewById(R.id.textView_city);
        textView.setText(getIntent().getStringExtra("CITY_NAME"));
        Button backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(v -> finish());
    }
}