package com.example.deneme2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //view lerimizi oluşturuyoruz
    Button btn_kisi_ekle;
    ListView lv_kisiler;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //view lerimizi id üzerinden eşleştiriyoruz
        btn_kisi_ekle = findViewById(R.id.btn_kisi_ekle);
        lv_kisiler = findViewById(R.id.lv_kisiler);

        //dbhelper nesnesimi oluşturuyoruz
        DBHelper db = new DBHelper(this);
        //listemize veri tabanından çektiğimiz verileri aktarıyoruz
        List<kisi> kisiler = db.kisileriGetir();

        //üstünde daha rahat işlem yapabilmek için listeleri oluşturduk
        ArrayList<Integer> kisilerId = new ArrayList<>();
        ArrayList<String> kisilerAd = new ArrayList<>();
        ArrayList<String> kisilerNumara = new ArrayList<>();

        for(kisi kisi :kisiler){
            //listelere verileri atadık
            kisilerId.add(kisi.getId());
            kisilerAd.add(kisi.getAd());
            kisilerNumara.add(kisi.getNumara());
        }

        //adapter a ad listesindeki verileri atadık
        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,kisilerAd );
        //listview a adapter daki verileri yazdırdık
        lv_kisiler.setAdapter(adapter);

        //kişi ekleme butonuna tıklanabilirlik ekledik
        btn_kisi_ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //tıklandığında kişi ekleme aktivitesine yönlendirmesi için intente bilgileri girdik
                Intent i = new Intent(MainActivity.this,kisiEkle.class);
                //intenti başlattık
                startActivity(i);
            }
        });

        //listview deki elemanlara tıklanabilirlik ekliyoruz
        lv_kisiler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //tıklandığında intente diğer aktivitenin ismini girdik
                Intent intent = new Intent(MainActivity.this,kisiEkle.class);

                //aktiviteler arası veri iletebilmek için put extra kullanıyoruz
                intent.putExtra("ID",kisilerId.get(i));
                intent.putExtra("AD",kisilerAd.get(i));
                intent.putExtra("NUMARA",kisilerNumara.get(i));

                //intenti başlatıyoruz
                startActivity(intent);
            }
        });

        //context menüyü hangi view elemanına eklediğimizi belirliyoruz
        //context menü uzun basılı tutulduğunda çıkan menü
        registerForContextMenu(btn_kisi_ekle);
    }

    //options menüyü oluşturuyoruz
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //res/menu klasöründe oluşturduğumuz menü xml dosyasını menümüze bağlıyoruz
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    //options menü ye seçilebilirlik ekliyoruz
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.mi_ekle:
                //id si mi_ekle seçildiğinde ne olucağı
                break;
            case R.id.mi_sil:
                //id si mi_sil seçildiğinde ne olucağı
                break;
        }
        return true;
    }

    //context menüyü oluşturuyoruz
    @Override
    public void onCreateContextMenu(ContextMenu menu,View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        //res/menu klasöründe oluşturduğumuz context menü xml dosyasını menümüze bağlıyoruz
        getMenuInflater().inflate(R.menu.context_menu,menu);

    }

    //context menü ye seçilebilirlik ekliyoruz
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.cmi_ac:
                //id si cmi_ac seçildiğinde ne olucağı
                break;
            case R.id.cmi_sil:
                //id si cmi_sil seçildiğinde ne olucağı
                break;
        }
        return true;
    }

}