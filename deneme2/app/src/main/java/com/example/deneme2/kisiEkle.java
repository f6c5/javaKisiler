package com.example.deneme2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class kisiEkle extends AppCompatActivity {

    //kullanacığımız view leri oluşturuyoruz
    EditText et_ad;
    EditText et_numara;
    Button btn_kaydet;
    Button btn_sil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kisi_ekle);

        //view leri id üzerinden eşleştiriyoruz

        et_ad = findViewById(R.id.et_ad);
        et_numara = findViewById(R.id.et_numara);

        btn_kaydet = findViewById(R.id.btn_kaydet);
        btn_sil = findViewById(R.id.btn_sil);

        //butonumuza tıklanabilirlik ekliyoruz
        btn_kaydet.setOnClickListener(v ->{

            //veri tabanı işlemleri için oluşturduğumuz sınıfı çağırıyoruz ve bu aktiviteyi parametre olarak veriyoruz
            DBHelper dbhelper = new DBHelper(this);

            // kişi nesnesini oluşturuyoruz ve edittext teki text değerlerini stringe çevirip nesnemize ekliyoruz
            kisi kisi = new kisi(et_ad.getText().toString(),et_numara.getText().toString());

            //oluştruduğumuz nesneyi veri tabnına ekliyoruz
            dbhelper.kisiEkle(kisi);
            //eklendiğini bildirmek için bir toast mesajı gönderiyoruz
            Toast.makeText(this,"Kişi eklendi",Toast.LENGTH_SHORT).show();

            //veri eklendikten sonra intent yardımı ile mainactivity e gidiyoruz
            Intent i = new Intent(this,MainActivity.class);
            //intenti çalıştırıyoruz
            startActivity(i);
        });

        Bundle extras = getIntent().getExtras();
        if (extras == null){
            int idKisi = extras.getInt("ID");
            String adKisi = extras.getString("AD");
            String numaraKisi = extras.getString("NUMARA");

            et_ad.setText(adKisi);
            et_numara.setText(numaraKisi);
        }


    }
}
