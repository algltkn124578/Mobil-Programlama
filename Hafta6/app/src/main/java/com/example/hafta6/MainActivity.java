package com.example.hafta6;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Şehir görsellerinin listesi
    private int[] cityImages = {
            R.drawable.istanbul1,
            R.drawable.istanbul3,
            R.drawable.galatakulesi,
            R.drawable.izmir1,
            R.drawable.izmir2,
            R.drawable.izmir3,
            R.drawable.ankara1,
            R.drawable.ankara2,
            R.drawable.ankara3
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button btnBasla = findViewById(R.id.btnBasla);
        btnBasla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCitySelectionDialog();
            }
        });
    }

    private void showCitySelectionDialog() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.activity_selection, null);
        
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView);
        
        final AlertDialog dialog = builder.create();
        
        // Rastgele resim seçme işlemi
        ImageView imgRandom = dialogView.findViewById(R.id.imgRandom);
        Random random = new Random();
        int randomIndex = random.nextInt(cityImages.length);
        imgRandom.setImageResource(cityImages[randomIndex]);

        Button btnIstanbul = dialogView.findViewById(R.id.btnIstanbul);
        Button btnIzmir = dialogView.findViewById(R.id.btnIzmir);
        Button btnAnkara = dialogView.findViewById(R.id.btnAnkara);

        btnIstanbul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IstanbulActivity.class));
                dialog.dismiss();
            }
        });

        btnIzmir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, IzmirActivity.class));
                dialog.dismiss();
            }
        });

        btnAnkara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, AnkaraActivity.class));
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}