package com.ptk.nihongoichinen.kanji;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ptk.nihongoichinen.FileIO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PHONGTRAN on 8/13/2016.
 */
public class KanjiDatabase{

    private static final String TAG = "SQLite";
    public static final String TABLE_KANJI = "Kanji";

    public static final String COLUMN_KANJI_ID = "Kanji_Id";
    public static final String COLUMN_KANJI_KANJI = "Kanji_Kanji";
    public static final String COLUMN_KANJI_HIRAGANA = "Kanji_Hiragana";
    public static final String COLUMN_KANJI_VIETNAMESE = "Kanji_Vietnamese";
    public static final String COLUMN_KANJI_BAI = "Kanji_Bai";
    public static final String COLUMN_KANJI_LEVEL = "Kanji_Level";

    public static void createDefaultKanjis(SQLiteDatabase db, Context context){

        List<Kanji> kanjisList = FileIO.readKanjisFromFile(context);

        for (int i = 0; i < kanjisList.size(); i++){
            addKanji(db, kanjisList.get(i));
        }
        System.out.print("Pham Trung Kha");
    }

    private static void addKanji(SQLiteDatabase db, Kanji kanji){
        Log.i(TAG, "KanjiDatabase.addKanji ... " + kanji.getKanji());

        ContentValues values = new ContentValues();
        values.put(COLUMN_KANJI_KANJI, kanji.getKanji());
        values.put(COLUMN_KANJI_HIRAGANA, kanji.getHiragana());
        values.put(COLUMN_KANJI_VIETNAMESE, kanji.getVietnamese());
        values.put(COLUMN_KANJI_BAI, kanji.getBai());
        values.put(COLUMN_KANJI_LEVEL, kanji.getLevel());

        db.insert(TABLE_KANJI, null, values);
        //db.close();
    }

    public List<Kanji> getKanjis(SQLiteDatabase db, int[] scope) {
        Log.i(TAG, "KanjiDatabase.getKanjis ... ");

        //SQLiteDatabase db = this.getWritableDatabase();
        List<Kanji> kanjiList = new ArrayList<Kanji>();
        for (int k = 0; k < 5; k++) {
            for (int i = scope[3*k+1]; i <= scope[3*k+2]; i++) {
                String selectQuery;
                if (i == -1)
                    selectQuery = "SELECT * FROM " + TABLE_KANJI + " WHERE " + COLUMN_KANJI_LEVEL + " = " + k;
                else
                    selectQuery = "SELECT * FROM " + TABLE_KANJI + " WHERE " + COLUMN_KANJI_BAI + " = " + i
                        + " AND " + COLUMN_KANJI_LEVEL + " = " + k;
                Cursor cursor = db.rawQuery(selectQuery, null);

                if (cursor.moveToFirst()) {
                    do {
                        Kanji kanji = new Kanji();
                        kanji.setId(cursor.getInt(0));
                        kanji.setKanji(cursor.getString(1));
                        kanji.setHiragana(cursor.getString(2));
                        kanji.setVietnamese(cursor.getString(3));
                        kanji.setBai(Integer.parseInt(cursor.getString(4)));
                        kanji.setLevel(Integer.parseInt(cursor.getString(5)));

                        kanjiList.add(kanji);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        }
        return kanjiList;
    }

    /*public Kanji getKanji(int id){
        Log.i(TAG, "KanjiDatabase.getKanji ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_KANJI, new String[] {COLUMN_KANJI_ID,
                COLUMN_KANJI_KANJI, COLUMN_KANJI_HIRAGANA, COLUMN_KANJI_VIETNAMESE, COLUMN_KANJI_BAI}, COLUMN_KANJI_ID + "=?",
                new String[] {String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Kanji kanji = new Kanji(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                cursor.getString(3), Integer.parseInt(cursor.getString(4)));
        return kanji;
    }*/

    /*public List<Kanji> getAllKanjis(SQLiteDatabase db) {
        Log.i(TAG, "KanjiDatabase.getAllKanjis ... ");

        List<Kanji> kanjiList = new ArrayList<Kanji>();
        String selectQuery = "SELECT * FROM " + TABLE_KANJI;
        //SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                Kanji kanji = new Kanji();
                kanji.setId(cursor.getInt(0));
                kanji.setKanji(cursor.getString(1));
                kanji.setHiragana(cursor.getString(2));
                kanji.setVietnamese(cursor.getString(3));
                kanji.setBai(Integer.parseInt(cursor.getString(4)));

                kanjiList.add(kanji);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return  kanjiList;
    }*/

    /*public int getKanjisCount(SQLiteDatabase db){
        Log.i(TAG, "KanjiDatabase.getKanjisCount ... ");

        String countQuery = "SELECT * FROM " + TABLE_KANJI;
        //SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();
        return count;
    }*/
}
