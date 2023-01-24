package com.example.kisiler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class kisilerActivity extends AppCompatActivity {

    ListView lv_liste;
    Button don;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kisiler);

        lv_liste = findViewById(R.id.lv_kisiler);
        don = findViewById(R.id.btn_anasayfa);


        don.setOnClickListener(view -> {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
        });

        listele();
    }

    public void listele(){
        DByardimci db = new DByardimci(this);
        List<kisi> kisiList = db.listele();

        List<String> isimler = new ArrayList<>();

        for (kisi kisi: kisiList) {
            isimler.add(kisi.getAd());
        }

        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, isimler);
        lv_liste.setAdapter(adapter);
    }
}