package com.ptk.nihongoichinen.reibun;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ptk.nihongoichinen.FileIO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PHONGTRAN on 10/7/2016.
 */
public class ReibunDatabase {
    private static final String TAG = "SQLite";
    public static final String TABLE_REIBUN = "Reibun";

    public static final String COLUMN_REIBUN_REIBUN = "Reibun_Reibun";
    public static final String COLUMN_REIBUN_BAI = "Reibun_Bai";
    public static final String COLUMN_REIBUN_PHAN = "Reibun_Phan";
    public static final String COLUMN_REIBUN_DAI = "Reibun_Dai";

    public static void createDefaultReibuns(SQLiteDatabase db, Context context){

        List<Reibun> reibunsList = FileIO.readReibunFromFile(context);

        for (int i = 0; i < reibunsList.size(); i++){
            addReibun(db, reibunsList.get(i));
        }
        System.out.print("Pham Trung Kha");
    }

    private static void addReibun(SQLiteDatabase db, Reibun reibun){
        Log.i(TAG, "ReibunDatabase.addReibun ... " + reibun.getReibun());

        ContentValues values = new ContentValues();
        values.put(COLUMN_REIBUN_REIBUN, reibun.getReibun());
        values.put(COLUMN_REIBUN_BAI, reibun.getBai());
        values.put(COLUMN_REIBUN_PHAN, reibun.getPhan());
        values.put(COLUMN_REIBUN_DAI, reibun.getDai());

        db.insert(TABLE_REIBUN, null, values);
        //db.close();
    }

    public List<Reibun> getReibuns(SQLiteDatabase db, int [] bai) {
        Log.i(TAG, "ReibunDatabase.getReibuns ... ");

        List<Reibun> reibunList = new ArrayList<Reibun>();
        for (int i = 0; i < bai.length; i++){
            String selectQuery = "SELECT * FROM " + TABLE_REIBUN + " WHERE " + COLUMN_REIBUN_BAI + " = " + bai[i];
            //SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()){
                do {
                    Reibun reibun = new Reibun();
                    reibun.setReibun(cursor.getString(0));
                    reibun.setBai(bai[i]);
                    reibun.setPhan(Integer.parseInt(cursor.getString(2)));
                    reibun.setDai(Integer.parseInt(cursor.getString(3)));

                    reibunList.add(reibun);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return reibunList;
    }
}
