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

    Button btn_kisi_ekle;
    ListView lv_kisiler;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btn_kisi_ekle = findViewById(R.id.btn_kisi_ekle);
        lv_kisiler = findViewById(R.id.lv_kisiler);
        DBHelper db = new DBHelper(this);
        List<kisi> kisiler = db.kisileriGetir();
        ArrayList<Integer> kisilerId = new ArrayList<>();
        ArrayList<String> kisilerAd = new ArrayList<>();
        ArrayList<String> kisilerNumara = new ArrayList<>();

        for(kisi kisi :kisiler){
            kisilerId.add(kisi.getId());
            kisilerAd.add(kisi.getAd());
            kisilerNumara.add(kisi.getNumara());
        }

        adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,kisilerAd );
        lv_kisiler.setAdapter(adapter);

        btn_kisi_ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this,kisiEkle.class);
                startActivity(i);
            }
        });

        lv_kisiler.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,kisiEkle.class);
                intent.putExtra("ID",kisilerId.get(i));
                intent.putExtra("AD",kisilerAd.get(i));
                intent.putExtra("NUMARA",kisilerNumara.get(i));
                startActivity(intent);
            }
        });

        registerForContextMenu(btn_kisi_ekle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

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

    @Override
    public void onCreateContextMenu(ContextMenu menu,View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        getMenuInflater().inflate(R.menu.context_menu,menu);

    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.cmi_ac:
                Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show();
                break;
            case R.id.cmi_sil:
                Toast.makeText(this, "aaa", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

}