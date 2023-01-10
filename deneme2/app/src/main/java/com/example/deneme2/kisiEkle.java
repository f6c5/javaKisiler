package com.example.deneme2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class kisiEkle extends AppCompatActivity {

    EditText et_ad;
    EditText et_numara;
    Button btn_kaydet;
    Button btn_sil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kisi_ekle);

        et_ad = findViewById(R.id.et_ad);
        et_numara = findViewById(R.id.et_numara);

        btn_kaydet = findViewById(R.id.btn_kaydet);
        btn_sil = findViewById(R.id.btn_sil);

        btn_kaydet.setOnClickListener(v ->{
            DBHelper db = new DBHelper(this);
            kisi kisi = new kisi(et_ad.getText().toString(),et_numara.getText().toString());
            db.kisiEkle(kisi);
            Toast.makeText(this,"Kişi eklendi",Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this,MainActivity.class);
            startActivity(i);
        });

        Bundle extras = getIntent().getExtras();
        int idKisi = extras.getInt("ID");
        String adKisi = extras.getString("AD");
        String numaraKisi = extras.getString("NUMARA");

        et_ad.setText(adKisi);
        et_numara.setText(numaraKisi);


    }
}
