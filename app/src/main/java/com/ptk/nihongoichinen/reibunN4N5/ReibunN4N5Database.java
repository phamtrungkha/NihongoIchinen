package com.ptk.nihongoichinen.reibunN4N5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ptk.nihongoichinen.FileIO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PHONGTRAN on 8/27/2016.
 */
public class ReibunN4N5Database {

    private static final String TAG = "SQLite";
    public static final String TABLE_REIBUN = "ReibunN4N5";

    public static final String COLUMN_REIBUN_ID = "ReibunN4N5_Reibun";
    public static final String COLUMN_REIBUN_BAI = "ReibunN4N5_Bai";
    public static final String COLUMN_REIBUN_DAI = "ReibunN4N5_Dai";

    public static void createDefaultReibuns(SQLiteDatabase db, Context context){

        List<ReibunN4N5> reibunsList = FileIO.readReibunN4N5FromFile(context);

        for (int i = 0; i < reibunsList.size(); i++){
            addReibun(db, reibunsList.get(i));
        }
        System.out.print("Pham Trung Kha");
    }

    private static void addReibun(SQLiteDatabase db, ReibunN4N5 reibunN4N5){
        Log.i(TAG, "ReibunN4N5Database.addReibun ... " + reibunN4N5.getId());

        ContentValues values = new ContentValues();
        values.put(COLUMN_REIBUN_ID, reibunN4N5.getId());
        values.put(COLUMN_REIBUN_BAI, reibunN4N5.getBai());
        values.put(COLUMN_REIBUN_DAI, reibunN4N5.getDai());

        db.insert(TABLE_REIBUN, null, values);
        //db.close();
    }

    public List<ReibunN4N5> getReibuns(SQLiteDatabase db, int [] bai) {
        Log.i(TAG, "ReibunN4N5Database.getReibuns ... ");

        List<ReibunN4N5> reibunN4N5List = new ArrayList<ReibunN4N5>();
        for (int i = 0; i < bai.length; i++){
            String selectQuery = "SELECT * FROM " + TABLE_REIBUN + " WHERE " + COLUMN_REIBUN_BAI + " = " + bai[i];
            //SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(selectQuery, null);

            if (cursor.moveToFirst()){
                do {
                    ReibunN4N5 reibunN4N5 = new ReibunN4N5();
                    reibunN4N5.setId(Integer.parseInt(cursor.getString(0)));
                    reibunN4N5.setBai(Integer.parseInt(cursor.getString(1)));
                    reibunN4N5.setDai(Integer.parseInt(cursor.getString(2)));

                    reibunN4N5List.add(reibunN4N5);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        return reibunN4N5List;
    }
}
