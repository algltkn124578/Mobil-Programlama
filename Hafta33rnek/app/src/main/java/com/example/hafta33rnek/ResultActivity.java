package com.example.hafta33rnek;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class ResultActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        ConstraintLayout layout = findViewById(R.id.resultLayout);
        TextView textView = findViewById(R.id.textViewResult);
        Button buttonBack = findViewById(R.id.buttonBack);

        boolean isMatch = getIntent().getBooleanExtra("isMatch", false);
        String cityName = getIntent().getStringExtra("cityName");
        int plateCode = getIntent().getIntExtra("plateCode", 0);

        if (isMatch) {
            layout.setBackgroundColor(Color.GREEN);
            textView.setText("Eşleşme Başarılı!\n" + plateCode + " - " + cityName);
        } else {
            layout.setBackgroundColor(Color.RED);
            textView.setText("Plaka kodu ve şehir eşleşmedi!\n" + plateCode + " - " + cityName);
        }

        buttonBack.setOnClickListener(v -> finish());
    }
}