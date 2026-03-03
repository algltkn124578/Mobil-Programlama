package com.example.basithesapmakinesi;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {

    private TextView tvDisplay;
    private double ilkSayi = 0;
    private double sonIkinciSayi = 0; // Tekrar eden işlemler için
    private String islem = "";
    private boolean yeniSayiBekleniyor = true;
    private boolean esittireBasildi = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDisplay = findViewById(R.id.tvDisplay);
        setNumberListeners();

        // İşlemler
        findViewById(R.id.btnPlus).setOnClickListener(v -> operasyonSec("+"));
        findViewById(R.id.btnMinus).setOnClickListener(v -> operasyonSec("-"));
        findViewById(R.id.btnMultiply).setOnClickListener(v -> operasyonSec("*"));
        findViewById(R.id.btnDivide).setOnClickListener(v -> operasyonSec("/"));
        findViewById(R.id.btnPower).setOnClickListener(v -> operasyonSec("^"));
        findViewById(R.id.btnRoot).setOnClickListener(v -> operasyonSec("root"));
        findViewById(R.id.btnFraction).setOnClickListener(v -> operasyonSec("/"));

        findViewById(R.id.btnInverse).setOnClickListener(v -> {
            double sayi = Double.parseDouble(tvDisplay.getText().toString());
            if (sayi != 0) formatVeGoster(1 / sayi);
            yeniSayiBekleniyor = true;
        });

        findViewById(R.id.btnEquals).setOnClickListener(v -> hesapla());

        findViewById(R.id.btnClear).setOnClickListener(v -> {
            tvDisplay.setText("0");
            ilkSayi = 0;
            sonIkinciSayi = 0;
            islem = "";
            yeniSayiBekleniyor = true;
            esittireBasildi = false;
        });
    }

    private void setNumberListeners() {
        int[] sayiIdleri = {R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9};
        View.OnClickListener listener = v -> {
            MaterialButton btn = (MaterialButton) v;
            if (yeniSayiBekleniyor) {
                tvDisplay.setText(btn.getText());
                yeniSayiBekleniyor = false;
            } else {
                tvDisplay.append(btn.getText());
            }
            esittireBasildi = false;
        };
        for (int id : sayiIdleri) findViewById(id).setOnClickListener(listener);
    }

    private void operasyonSec(String secilenIslem) {
        ilkSayi = Double.parseDouble(tvDisplay.getText().toString());
        islem = secilenIslem;
        yeniSayiBekleniyor = true;
        esittireBasildi = false;
    }

    private void hesapla() {
        double ikinciSayi;

        if (esittireBasildi) {
            // Eğer eşittire üst üste basılıyorsa, ilk sayı sonucu, ikinci sayı eski değeri alır
            ilkSayi = Double.parseDouble(tvDisplay.getText().toString());
            ikinciSayi = sonIkinciSayi;
        } else {
            ikinciSayi = Double.parseDouble(tvDisplay.getText().toString());
            sonIkinciSayi = ikinciSayi; // İlk kez basıldığında ikinci sayıyı hafızaya al
        }

        double sonuc = 0;
        switch (islem) {
            case "+": sonuc = ilkSayi + ikinciSayi; break;
            case "-": sonuc = ilkSayi - ikinciSayi; break;
            case "*": sonuc = ilkSayi * ikinciSayi; break;
            case "/":
                if (ikinciSayi != 0) sonuc = ilkSayi / ikinciSayi;
                else { tvDisplay.setText("Hata"); return; }
                break;
            case "^": sonuc = Math.pow(ilkSayi, ikinciSayi); break;
            case "root":
                if (ikinciSayi != 0) sonuc = Math.pow(ilkSayi, 1.0 / ikinciSayi);
                else { tvDisplay.setText("Hata"); return; }
                break;
        }

        formatVeGoster(sonuc);
        yeniSayiBekleniyor = true;
        esittireBasildi = true; // Ardışık işlem için kilidi aç
    }

    private void formatVeGoster(double sonuc) {
        if (sonuc == (long) sonuc) tvDisplay.setText(String.format("%d", (long) sonuc));
        else tvDisplay.setText(String.valueOf(sonuc));
    }
}