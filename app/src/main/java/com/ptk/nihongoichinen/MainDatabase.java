package com.ptk.nihongoichinen;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ptk.nihongoichinen.itkotoba.ITKotobaDatabase;
import com.ptk.nihongoichinen.kanji.KanjiDatabase;
import com.ptk.nihongoichinen.reibun.ReibunDatabase;
import com.ptk.nihongoichinen.reibunN4N5.ReibunN4N5Database;
import com.ptk.nihongoichinen.reibunN3.ReibunN3Database;

/**
 * Created by PHONGTRAN on 8/27/2016.
 */
public class MainDatabase  extends SQLiteOpenHelper {

    private static final String TAG = "SQLite";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Japanese_Manager";
    private Context contextRoot;

    public MainDatabase(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        contextRoot = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "japaneseDatabase.onCreate ... ");

        //Kanji
        String kanji = "CREATE TABLE " + KanjiDatabase.TABLE_KANJI + "("
                + KanjiDatabase.COLUMN_KANJI_ID + " INTEGER PRIMARY KEY," + KanjiDatabase.COLUMN_KANJI_KANJI + " TEXT, "
                + KanjiDatabase.COLUMN_KANJI_HIRAGANA + " TEXT, " + KanjiDatabase.COLUMN_KANJI_VIETNAMESE + " TEXT, "
                + KanjiDatabase.COLUMN_KANJI_BAI + " TEXT, " + KanjiDatabase.COLUMN_KANJI_LEVEL + " TEXT" + ")";
        db.execSQL(kanji);
        KanjiDatabase.createDefaultKanjis(db, contextRoot);

        //ITKotoba
        String itKotoba = "CREATE TABLE " + ITKotobaDatabase.TABLE_ITKOTOBA + "("
                + ITKotobaDatabase.COLUMN_ITKOTOBA_ID + " INTEGER PRIMARY KEY, " + ITKotobaDatabase.COLUMN_ITKOTOBA_KANJI + " TEXT,"
                + ITKotobaDatabase.COLUMN_ITKOTOBA_HIRAGANA + " TEXT, " + ITKotobaDatabase.COLUMN_ITKOTOBA_MEANING + " TEXT, "
                + ITKotobaDatabase.COLUMN_ITKOTOBA_KATAKANA + " TEXT" + ")";
        db.execSQL(itKotoba);
        ITKotobaDatabase.createDefaultITKotobas(db, contextRoot);

        //ReibunN4N5
        String reibunN4N5 = "CREATE TABLE " + ReibunN4N5Database.TABLE_REIBUN + "("
                + ReibunN4N5Database.COLUMN_REIBUN_ID+ " INTEGER PRIMARY KEY," + ReibunN4N5Database.COLUMN_REIBUN_BAI + " INTEGER, "
                + ReibunN4N5Database.COLUMN_REIBUN_DAI + " INTEGER" + ")";
        db.execSQL(reibunN4N5);
        ReibunN4N5Database.createDefaultReibuns(db, contextRoot);

        //ReibunN3
        String reibunN3 = "CREATE TABLE " + ReibunN3Database.TABLE_REIBUN + "("
                + ReibunN3Database.COLUMN_REIBUN_REIBUN+ " TEXT," + ReibunN3Database.COLUMN_REIBUN_BAI + " INTEGER,"
                + ReibunN3Database.COLUMN_REIBUN_PHAN + " INTEGER PRIMARY KEY," + ReibunN3Database.COLUMN_REIBUN_DAI + " INTEGER" + ")";
        db.execSQL(reibunN3);
        ReibunN3Database.createDefaultReibuns(db, contextRoot);


        //Reibun
        String reibun = "CREATE TABLE " + ReibunDatabase.TABLE_REIBUN + "("
                + ReibunDatabase.COLUMN_REIBUN_REIBUN+ " TEXT," + ReibunDatabase.COLUMN_REIBUN_BAI + " INTEGER,"
                + ReibunDatabase.COLUMN_REIBUN_PHAN + " INTEGER PRIMARY KEY," + ReibunDatabase.COLUMN_REIBUN_DAI + " INTEGER" + ")";
        db.execSQL(reibun);
        ReibunDatabase.createDefaultReibuns(db, contextRoot);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        Log.i(TAG, "KanjiDatabase.onUpgrade ... ");
        db.execSQL("DROP TABLE IF EXISTS " + KanjiDatabase.TABLE_KANJI);
        onCreate(db);
    }
}
