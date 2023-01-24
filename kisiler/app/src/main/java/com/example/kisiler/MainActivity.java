package com.example.kisiler;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText ad;
    EditText no;
    Button ekle;
    Button listele;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ad = findViewById(R.id.txt_ad);
        no = findViewById(R.id.txt_no);
        ekle = findViewById(R.id.btn_ekle);
        listele = findViewById(R.id.btn_listele);

        ekle.setOnClickListener(view ->{
                DByardimci db = new DByardimci(this);
                kisi kisi = new kisi(ad.getText().toString(), no.getText().toString());
                db.ekle(kisi);
                Toast.makeText(this,"eklendi",Toast.LENGTH_SHORT).show();
            }
        );

        listele.setOnClickListener(view -> {
            //intent yönlendirme için
            Intent i = new Intent(this, kisilerActivity.class);
            startActivity(i);
        });
    }

}