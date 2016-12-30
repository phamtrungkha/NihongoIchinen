package com.ptk.nihongoichinen.itkotoba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ptk.nihongoichinen.FileIO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by Phong Tran on 12/18/2016.
 */
public class ITKotobaDatabase {
    private static final String TAG = "SQLite";
    public static final String TABLE_ITKOTOBA = "ITKotoba";

    public static final String COLUMN_ITKOTOBA_ID = "Kotoba_Id";
    public static final String COLUMN_ITKOTOBA_KANJI = "Kotoba_Kanji";
    public static final String COLUMN_ITKOTOBA_HIRAGANA = "Kotoba_Hiragana";
    public static final String COLUMN_ITKOTOBA_MEANING = "Kotoba_Meaning";
    public static final String COLUMN_ITKOTOBA_KATAKANA = "Kotoba_Katakana";

    public static void createDefaultITKotobas(SQLiteDatabase db, Context context){

        List<ITKotoba> itkotobasList = FileIO.readITKotobasFromFile(context);

        for (int i = 0; i < itkotobasList.size(); i++){
            addITKotoba(db, itkotobasList.get(i));
        }
        System.out.print("Pham Trung Kha");
    }

    private static void addITKotoba(SQLiteDatabase db, ITKotoba itkotoba){
        Log.i(TAG, "KotobaDatabase.addKotoba ... " + itkotoba.getMeaning());

        ContentValues values = new ContentValues();
        values.put(COLUMN_ITKOTOBA_KANJI, itkotoba.getKanji());
        values.put(COLUMN_ITKOTOBA_HIRAGANA, itkotoba.getHiragana());
        values.put(COLUMN_ITKOTOBA_MEANING, itkotoba.getMeaning());
        values.put(COLUMN_ITKOTOBA_KATAKANA, itkotoba.getKatakana());

        db.insert(TABLE_ITKOTOBA, null, values);
        //db.close();
    }

    public List<ITKotoba> getITKotobas(SQLiteDatabase db, int from, int to) {
        Log.i(TAG, "KotobaDatabase.getKotobas ... ");

        //SQLiteDatabase db = this.getWritableDatabase();
        List<ITKotoba> itKotobaList = new ArrayList<ITKotoba>();
        for (int i = from; i <= to; i++){
            String selectQuery;
            if (i == -1)
                selectQuery = "SELECT * FROM " + TABLE_ITKOTOBA;
            else
                selectQuery = "SELECT * FROM " + TABLE_ITKOTOBA + " WHERE " + COLUMN_ITKOTOBA_ID + " = " + i;
            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()){
                do {
                    ITKotoba itKotoba = new ITKotoba();
                    itKotoba.setId(cursor.getInt(0));
                    itKotoba.setKanji(cursor.getString(1));
                    itKotoba.setHiragana(cursor.getString(2));
                    itKotoba.setMeaning(cursor.getString(3));
                    itKotoba.setKatakana(cursor.getString(4));

                    itKotobaList.add(itKotoba);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return itKotobaList;
    }
}
