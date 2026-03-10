package com.example.hafta33rnek;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String[] citiesArray = {
            "Adana", "Adıyaman", "Afyonkarahisar", "Ağrı", "Amasya", "Ankara", "Antalya", "Artvin",
            "Aydın", "Balıkesir", "Bilecik", "Bingöl", "Bitlis", "Bolu", "Burdur", "Bursa",
            "Çanakkale", "Çankırı", "Çorum", "Denizli", "Diyarbakır", "Edirne", "Elazığ", "Erzincan",
            "Erzurum", "Eskişehir", "Gaziantep", "Giresun", "Gümüşhane", "Hakkari", "Hatay", "Isparta",
            "Mersin", "İstanbul", "İzmir", "Kars", "Kastamonu", "Kayseri", "Kırklareli", "Kırşehir",
            "Kocaeli", "Konya", "Kütahya", "Malatya", "Manisa", "Kahramanmaraş", "Mardin", "Muğla",
            "Muş", "Nevşehir", "Niğde", "Ordu", "Rize", "Sakarya", "Samsun", "Siirt",
            "Sinop", "Sivas", "Tekirdağ", "Tokat", "Trabzon", "Tunceli", "Şanlıurfa", "Uşak",
            "Van", "Yozgat", "Zonguldak", "Aksaray", "Bayburt", "Karaman", "Kırıkkale", "Batman",
            "Şırnak", "Bartın", "Ardahan", "Iğdır", "Yalova", "Karabük", "Kilis", "Osmaniye", "Düzce"
    };

    private List<Integer> shuffledNumbers;
    private List<String> orderedCities;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonShuffle = findViewById(R.id.buttonShuffle);
        ListView listViewMain = findViewById(R.id.listViewMain);

        shuffledNumbers = new ArrayList<>();
        for (int i = 1; i <= 81; i++) {
            shuffledNumbers.add(i);
        }
        Collections.shuffle(shuffledNumbers);

        // Şehirler plaka sırasına göre sabit kalacak
        orderedCities = new ArrayList<>(Arrays.asList(citiesArray));

        adapter = new CustomAdapter();
        listViewMain.setAdapter(adapter);

        buttonShuffle.setOnClickListener(v -> {
            Collections.shuffle(shuffledNumbers);
            adapter.notifyDataSetChanged();
        });

        listViewMain.setOnItemClickListener((parent, view, position, id) -> {
            String selectedCity = orderedCities.get(position);
            int displayedNumber = shuffledNumbers.get(position);

            // Şehirler sıralı olduğu için doğru plaka kodu: position + 1
            int correctPlateCode = position + 1;

            boolean isMatch = (displayedNumber == correctPlateCode);

            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("isMatch", isMatch);
            intent.putExtra("cityName", selectedCity);
            intent.putExtra("plateCode", displayedNumber);
            startActivity(intent);
        });
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return citiesArray.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.list_item, parent, false);
            }

            TextView textViewNumber = convertView.findViewById(R.id.textViewNumber);
            TextView textViewCity = convertView.findViewById(R.id.textViewCity);

            textViewNumber.setText(String.valueOf(shuffledNumbers.get(position)));
            textViewCity.setText(orderedCities.get(position));

            return convertView;
        }
    }
}