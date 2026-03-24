package com.example.hafta6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SelectionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_selection);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.selection_layout), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnIstanbul = findViewById(R.id.btnIstanbul);
        Button btnIzmir = findViewById(R.id.btnIzmir);
        Button btnAnkara = findViewById(R.id.btnAnkara);

        btnIstanbul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectionActivity.this, IstanbulActivity.class);
                startActivity(intent);
            }
        });

        btnIzmir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectionActivity.this, IzmirActivity.class);
                startActivity(intent);
            }
        });

        btnAnkara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectionActivity.this, AnkaraActivity.class);
                startActivity(intent);
            }
        });
    }
}