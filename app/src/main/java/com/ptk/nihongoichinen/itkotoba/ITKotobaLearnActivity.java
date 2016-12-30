package com.ptk.nihongoichinen.itkotoba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ptk.nihongoichinen.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ITKotobaLearnActivity extends AppCompatActivity {

    private static final int LODI = 1;
    private static final int KOLODI = 0;
    private static final int HIEN = 0;
    private static final int AN_1 = 1;
    private static final int AN_2 = 2;
    private static final int AN_3 = 3;
    private List<ITKotoba> itKotobaList = new ArrayList<ITKotoba>();
    private List<ITKotoba> itKotobaListChuaThuoc = new ArrayList<ITKotoba>();
    private List<ITKotoba> itKotobaListDaThuoc = new ArrayList<ITKotoba>();
    private int sotuconlai = 0;
    private ITKotoba itKotoba = null;
    private Random random = new Random();
    private String chieuhoc;
    private TextView hien = null;
    private TextView an_1 = null;
    private TextView an_2 = null;
    private TextView an_3 = null;
    private TextView conlai = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itkotoba_learn);

        Intent intent = getIntent();
        chieuhoc = intent.getStringExtra("chieuhoc");

        Bundle bundle = intent.getBundleExtra("itKotoba");
        for (int i = 0; i < bundle.size(); i++){
            itKotobaList.add((ITKotoba) bundle.getSerializable("ikl"+i));
        }

        hien = (TextView) findViewById(R.id.itkotobalearn_tv_hien);
        an_1 = (TextView) findViewById(R.id.itkotobalearn_tv_an_1);
        an_2 = (TextView) findViewById((R.id.itkotobalearn_tv_an_2));
        an_3 = (TextView) findViewById((R.id.itkotobalearn_tv_an_3));
        conlai = (TextView) findViewById(R.id.itkotobalearn_tv_conlai);

        itKotoba = itKotobaList.get(0);
        sotuconlai = itKotobaList.size();
        conlai.setText(Integer.toString(sotuconlai));
        hien.setText(getJi(itKotoba, chieuhoc)[HIEN]);

        Button button_lodi = (Button) findViewById(R.id.itkotobalearn_btn_lodi);
        button_lodi.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                sotuconlai--;
                itKotobaListDaThuoc.add(itKotoba);
                itKotobaList.remove(itKotoba);
                itKotoba = getRandomKanji();
                hien.setText(getJi(itKotoba, chieuhoc)[HIEN]);
                conlai.setText(Integer.toString(sotuconlai));
                an_1.setText("");
                an_2.setText("");
                an_3.setText("");
            }
        });

        Button button_dapan = (Button) findViewById(R.id.itkotobalearn_btn_dapan);
        button_dapan.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                an_1.setText(getJi(itKotoba, chieuhoc)[AN_1]);
                an_2.setText(getJi(itKotoba, chieuhoc)[AN_2]);
                an_3.setText(getJi(itKotoba, chieuhoc)[AN_3]);
            }
        });

        Button button_next = (Button) findViewById(R.id.itkotobalearn_btn_next);
        button_next.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                itKotobaListChuaThuoc.add(itKotoba);
                itKotobaList.remove(itKotoba);
                itKotoba = getRandomKanji();
                hien.setText(getJi(itKotoba, chieuhoc)[HIEN]);
                an_1.setText("");
                an_2.setText("");
                an_3.setText("");
            }


        });
    }

    private String[] getJi(ITKotoba itKotoba, String chieuhoc) {
        String result[] = new String[4];
        if (chieuhoc.equals("Kanji")){
            result[HIEN] = itKotoba.getKanji();
            result[AN_1] = itKotoba.getHiragana();
            result[AN_2] = itKotoba.getKatakana();
            result[AN_3] = itKotoba.getMeaning();
        } else if (chieuhoc.equals("Hiragana")){
            result[HIEN] = itKotoba.getHiragana();
            result[AN_1] = itKotoba.getKanji();
            result[AN_2] = itKotoba.getKatakana();
            result[AN_3] = itKotoba.getMeaning();
        } else if (chieuhoc.equals("Katakana")){
            result[HIEN] = itKotoba.getKatakana();
            result[AN_1] = itKotoba.getKanji();
            result[AN_2] = itKotoba.getHiragana();
            result[AN_3] = itKotoba.getMeaning();
        } else {
            result[HIEN] = itKotoba.getMeaning();
            result[AN_1] = itKotoba.getKanji();
            result[AN_2] = itKotoba.getHiragana();
            result[AN_3] = itKotoba.getKatakana();
        }
        if (result[HIEN].equals("")){
            result[HIEN] = result[AN_2];
            result[AN_2] = "";
        }
        return result;
    }

    public ITKotoba getRandomKanji() {

        if (itKotobaList.size() == 0){
            if (itKotobaListChuaThuoc.size() != 0) {
                itKotobaList.addAll(itKotobaListChuaThuoc);
                itKotobaListChuaThuoc.removeAll(itKotobaListChuaThuoc);
            }
            else {
                itKotobaList.addAll(itKotobaListDaThuoc);
                itKotobaListDaThuoc.removeAll(itKotobaListDaThuoc);
                sotuconlai = itKotobaList.size();
            }
        }
        return itKotobaList.get(random.nextInt(itKotobaList.size()));
    }
}
