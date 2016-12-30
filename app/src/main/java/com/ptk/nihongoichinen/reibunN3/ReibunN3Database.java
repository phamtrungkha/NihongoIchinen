package com.ptk.nihongoichinen.reibunN3;

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
public class ReibunN3Database {
    private static final String TAG = "SQLite";
    public static final String TABLE_REIBUN = "ReibunN3";

    public static final String COLUMN_REIBUN_REIBUN = "ReibunN3_Reibun";
    public static final String COLUMN_REIBUN_BAI = "ReibunN3_Bai";
    public static final String COLUMN_REIBUN_PHAN = "ReibunN3_Phan";
    public static final String COLUMN_REIBUN_DAI = "ReibunN3_Dai";

    public static void createDefaultReibuns(SQLiteDatabase db, Context context){

        List<ReibunN3> reibunsList = FileIO.readReibunN3FromFile(context);

        for (int i = 0; i < reibunsList.size(); i++){
            addReibun(db, reibunsList.get(i));
        }
        System.out.print("Pham Trung Kha");
    }

    private static void addReibun(SQLiteDatabase db, ReibunN3 reibun){
        Log.i(TAG, "ReibunN3Database.addReibun ... " + reibun.getReibun());

        ContentValues values = new ContentValues();
        values.put(COLUMN_REIBUN_REIBUN, reibun.getReibun());
        values.put(COLUMN_REIBUN_BAI, reibun.getBai());
        values.put(COLUMN_REIBUN_PHAN, reibun.getPhan());
        values.put(COLUMN_REIBUN_DAI, reibun.getDai());

        db.insert(TABLE_REIBUN, null, values);
        //db.close();
    }

    public List<ReibunN3> getReibuns(SQLiteDatabase db, int [] bai) {
        Log.i(TAG, "ReibunN4N5Database.getReibuns ... ");

        List<ReibunN3> reibunList = new ArrayList<ReibunN3>();
        for (int i = 0; i < bai.length; i++){
            String selectQuery = "SELECT * FROM " + TABLE_REIBUN + " WHERE " + COLUMN_REIBUN_BAI + " = " + bai[i];
            //SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()){
                do {
                    ReibunN3 reibun = new ReibunN3();
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
