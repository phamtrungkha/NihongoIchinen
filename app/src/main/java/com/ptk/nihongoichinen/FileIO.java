package com.ptk.nihongoichinen;

import android.content.Context;
import android.content.res.AssetManager;

import com.ptk.nihongoichinen.itkotoba.ITKotoba;
import com.ptk.nihongoichinen.kanji.Kanji;
//import com.ptk.nihongoichinen.kanjiN3.KanjiN3;
import com.ptk.nihongoichinen.reibun.Reibun;
import com.ptk.nihongoichinen.reibunN4N5.ReibunN4N5;
import com.ptk.nihongoichinen.reibunN3.ReibunN3;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

/**
 * Created by PHONGTRAN on 8/17/2016.
 */
public class FileIO {

    public static List<Kanji> readKanjisFromFile(Context context){

        List<Kanji> kanjiList = new ArrayList<Kanji>();

        try {
            AssetManager am = context.getAssets();
            InputStream is = am.open("kanji.xls");
            Workbook wb = Workbook.getWorkbook(is);
            for (int i =0; i < 4; i++) {
                Sheet s = wb.getSheet(i);
                int row = s.getRows();

                for (int j = 0; j < row; j++) {
                    Kanji kanji = new Kanji(s.getCell(0, j).getContents(), s.getCell(1, j).getContents(),
                            s.getCell(2, j).getContents(), Integer.parseInt(s.getCell(3, j).getContents()), i);
                    kanjiList.add(kanji);
                }
            }
        }
        catch (Exception e){

        }
        return kanjiList;
    }

    public static List<ITKotoba> readITKotobasFromFile(Context context){

        List<ITKotoba> itKotobaList = new ArrayList<ITKotoba>();

        try {
            AssetManager am = context.getAssets();
            InputStream is = am.open("itkotoba.xls");
            Workbook wb = Workbook.getWorkbook(is);
            Sheet s = wb.getSheet(0);
            int row = s.getRows();
            //int col = s.getColumns();

            for (int i = 0; i < row; i++){
                ITKotoba itKotoba = new ITKotoba(s.getCell(0,i).getContents(), s.getCell(1,i).getContents(),
                        s.getCell(2,i).getContents(), s.getCell(3,i).getContents());
                itKotobaList.add(itKotoba);
            }
        }
        catch (Exception e){

        }
        return itKotobaList;
    }

    /*public static List<KanjiN3> readKanjisN3FromFile(Context context){

        List<KanjiN3> kanjiList = new ArrayList<KanjiN3>();

        try {
            AssetManager am = context.getAssets();
            InputStream is = am.open("kanjiN3.xls");
            Workbook wb = Workbook.getWorkbook(is);
            Sheet s = wb.getSheet(0);
            int row = s.getRows();
            //int col = s.getColumns();

            for (int i = 0; i < row; i++){
                KanjiN3 kanji = new KanjiN3(s.getCell(0,i).getContents(), s.getCell(1,i).getContents(),
                        s.getCell(2,i).getContents(), Integer.parseInt(s.getCell(3,i).getContents()));
                kanjiList.add(kanji);
            }
        }
        catch (Exception e){

        }
        return kanjiList;
    }*/

    public static List<ReibunN4N5> readReibunN4N5FromFile(Context context) {

        List<ReibunN4N5> reibunN4N5List = new ArrayList<ReibunN4N5>();

        try {
            AssetManager am = context.getAssets();
            InputStream is = am.open("reibunN4N5.xls");
            Workbook wb = Workbook.getWorkbook(is);
            int aaa = wb.getNumberOfSheets();
            for (int j = 0; j < wb.getNumberOfSheets(); j++){
                Sheet s = wb.getSheet(j);
                int row = s.getRows();
                int col = s.getColumns();

                String ana = s.getName().toString();
                for (int i = 0; i < row; i++){
                    ReibunN4N5 reibunN4N5 = new ReibunN4N5(Integer.parseInt(s.getCell(0,i).getContents()), Integer.parseInt(s.getName().toString()), Integer.parseInt(s.getCell(1,i).getContents()));
                    reibunN4N5List.add(reibunN4N5);
                }
            }
        }
        catch (Exception e){

        }
        return reibunN4N5List;
    }

    public static List<ReibunN3> readReibunN3FromFile(Context context) {

        List<ReibunN3> reibunList = new ArrayList<ReibunN3>();

        try {
            AssetManager am = context.getAssets();
            InputStream is = am.open("reibunN3.xls");
            Workbook wb = Workbook.getWorkbook(is);
            int aaa = wb.getNumberOfSheets();
            for (int j = 0; j < wb.getNumberOfSheets(); j++){
                Sheet s = wb.getSheet(j);
                int row = s.getRows();
                int col = s.getColumns();

                for (int i = 0; i < row; i++){
                    ReibunN3 reibun = new ReibunN3(s.getCell(0,i).getContents(), Integer.parseInt(s.getName().toString()),
                            Integer.parseInt(s.getCell(1,i).getContents()), Integer.parseInt(s.getCell(2,i).getContents()));
                    reibunList.add(reibun);
                }
            }
        }
        catch (Exception e){

        }
        return reibunList;
    }

    public static List<Reibun> readReibunFromFile(Context context) {

        List<Reibun> reibunList = new ArrayList<Reibun>();

        try {
            AssetManager am = context.getAssets();
            InputStream is = am.open("reibun.xls");
            Workbook wb = Workbook.getWorkbook(is);
            int aaa = wb.getNumberOfSheets();
            for (int j = 0; j < wb.getNumberOfSheets(); j++){
                Sheet s = wb.getSheet(j);
                int row = s.getRows();
                int col = s.getColumns();

                for (int i = 0; i < row; i++){
                    Reibun reibun = new Reibun(s.getCell(0,i).getContents(), Integer.parseInt(s.getName().toString()),
                            Integer.parseInt(s.getCell(1,i).getContents()), Integer.parseInt(s.getCell(2,i).getContents()));
                    reibunList.add(reibun);
                }
            }
        }
        catch (Exception e){

        }
        return reibunList;
    }
}
