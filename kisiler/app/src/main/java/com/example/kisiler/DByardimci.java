package com.example.kisiler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DByardimci extends SQLiteOpenHelper {

    public DByardimci (@Nullable Context context){
        super(context,"db_kisiler",null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE tbl_kisiler(ID INTEGER PRIMARY KEY AUTOINCREMENT, AD TEXT, NUMARA TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS tbl_kisiler", new  String[]{"tbl_kisiler"} );

        onCreate(sqLiteDatabase);
    }

    public void ekle(kisi kisi){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("AD", kisi.getAd());
        cv.put("NUMARA", kisi.getNumara());

        db.insert("tbl_kisiler", null,cv);
        db.close();
    }

    public List<kisi> listele(){

        List<kisi> kisiler = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM tbl_kisiler",null);
        if (cursor.moveToFirst()){
            do {
                kisi kisi = new kisi(cursor.getInt(0), cursor.getString(1),cursor.getString(2));
                kisiler.add(kisi);
            }while (cursor.moveToNext());
        }
        db.close();

        return kisiler;
    }

    public void kisiGüncelle(kisi kisi){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("AD", kisi.getAd());
        cv.put("NUMARA", kisi.getNumara());

        db.update("tbl_kisiler", cv,"ID =?",new String[]{String.valueOf(kisi.getId())} );

        db.close();
    }

    public void sil(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("tbl_kisiler", "AD=?",new String[]{String.valueOf(id)});
    }
}
