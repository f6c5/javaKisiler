package com.example.deneme2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import androidx.core.database.sqlite.SQLiteDatabaseKt;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    //dbhelper constructor oluşturuyoruz
    public DBHelper (@Nullable Context context){
        //db_kisiler adında veri tabanını oluşturuyoruz
        super(context,"db_kisiler",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //tabloyu oluşturuyoruz
        sqLiteDatabase.execSQL("CREATE TABLE tbl_kisiler(ID INTEGER PRIMARY KEY AUTOINCREMENT, AD TEXT, NUMARA TEXT)");
    }

    //onUpgrade metodu getWritableDatabase() yada getReadableDatabase() metodları çağrıldığında çalışır.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbl_kisiler", new  String[]{"tbl_kisiler"} );
        onCreate(sqLiteDatabase);
    }

    //kişi ekleme fonksiyonu parametre olarak bir kisi alır
    public void kisiEkle(kisi kisi){
        //sqlitedatabase nesnesi oluşturuyoruz ve bunu yazılabilir yapıyoruz
        SQLiteDatabase db = this.getWritableDatabase();
        //contentvalues yardımcı nesnesini oluşturuyyoruz
        ContentValues cv = new ContentValues();
        //content values nesnesine tablodaki sütun ismi ve değeri şeklinde verileri ekliyoruz
        cv.put("AD", kisi.getAd());
        cv.put("NUMARA", kisi.getNumara());
        //sqlitedatabase nesnesinin yardımcı fonksiyonu olan insert(ekleme) fonksiyonuna parametre olarak tablo ismi, null, ve contentvalues nesnemiz verilir.
        db.insert("tbl_kisiler", null, cv);
        db.close();
    }

    //veri tabanındaki kişileri listeye aktarmak için kisi türünde liste döndüren bir fonksiyon
    public List<kisi> kisileriGetir(){
        // kişileri aktarıcağımız kisi tipindeki listemizi oluşturuyoruz
        List<kisi> kisiler = new ArrayList<>();

        //sqlitedatabase nesnemizi okunabilir yapıyoruz
        SQLiteDatabase db = this.getReadableDatabase();

        //rawquery fonksiyonu sql sorguları çalıştırmak için kullanılır
        //rawquery fonksiyouna girdiğimiz sorgunun sonucu listelenen değerleri cursor nesnesi ile saklıyoruz
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_kisiler",null);
        //cursor nesnesi verileri tablo şeklinde saklar

        //öncelikle cursor nesnesideki ilk satıra gidiyoruz yani veritabanından ekilen ilk satır
        //eğer cursor nesnesindeki ilk satır boşsa bu toblonun boş olduğunu gösterir
        if (cursor.moveToFirst()){
            //ilk satırdayken do while dongüsüne alıyoruz
            do {
                //kisi türünde bir kisi nesnesi oluşturuyoruz ve cursordaki değerleri sütun indexlerine göre atıyoruz
                kisi kisi = new kisi(cursor.getInt(0), cursor.getString(1),cursor.getString(2));

                //kisi nesnemizi listemize ekliyoruz
                kisiler.add(kisi);
                //cursor içindeki sonraki satıra geçiyoruz ve bunu kontrol ediyoruz eğer sonraki satır boşsa döngüden çıkar
            }while (cursor.moveToNext());
        }
        db.close();
        return kisiler;
    }

    //kişiyi güncellemek için oluşturduğumuz kişi türünde bir kisi nesnesi parametresi alan fonksiyon
    public void kisiGüncelle(kisi kisi){

        //sqlitedatabase nesnesi oluşturuyoruz ve bunu yazılabilir yapıyoruz
        SQLiteDatabase db = this.getWritableDatabase();
        //contentvalues yardımcı nesnesini oluşturuyyoruz
        ContentValues cv = new ContentValues();
        //değerleri atıyoruz
        cv.put("AD", kisi.getAd());
        cv.put("NUMARA", kisi.getNumara());
        //db.update fonksiyonuna tablomuzu, cv mizi, id sütnunda , güncellemek istdeğimiz kisimizin id sini parametre olarak alır
        db.update("tbl_kisiler", cv,"ID =?",new String[]{String.valueOf(kisi.getId())} );

        db.close();
    }
    //tablodaki satırı silen int türünde id parametresi alan bir fonksiyon
    public void kisiSil(int id){

        SQLiteDatabase db = this.getWritableDatabase();
        //db.table fonksiyonuna tablo ismini, id sütununda , parametre olarak aldığımız id yi siliyoruz
        db.delete("tbl_kisiler","ID =?",new String[]{String.valueOf(id)});
        db.close();
    }


}
