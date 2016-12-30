package com.ptk.nihongoichinen.kanji;

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

public class KanjiLearnActivity extends AppCompatActivity {

    private static final int LODI = 1;
    private static final int KOLODI = 0;
    private static final int HIEN = 0;
    private static final int AN = 1;
    private static final int AN_VN = 2;
    private List<Kanji> kanjiList = new ArrayList<Kanji>();
    private List<Kanji> kanjiListChuaThuoc = new ArrayList<Kanji>();
    private List<Kanji> kanjiListDaThuoc = new ArrayList<Kanji>();
    private int sotuconlai = 0;
    private Kanji kanji = null;
    private Random random = new Random();
    private boolean hira2kanji = true;
    private TextView hien = null;
    private TextView an = null;
    private TextView an_vi = null;
    private TextView conlai = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanji_learn);

        Intent intent = getIntent();
        hira2kanji = intent.getBooleanExtra("chieuHoc",true);

        Bundle bundle = intent.getBundleExtra("kanji");
        for (int i = 0; i < bundle.size(); i++){
            kanjiList.add((Kanji) bundle.getSerializable("kl"+i));
        }

        hien = (TextView) findViewById(R.id.kanjilearn_tv_hien);
        an = (TextView) findViewById(R.id.kanjilearn_tv_an);
        an_vi = (TextView) findViewById((R.id.kanjilearn_tv_an_vi));
        conlai = (TextView) findViewById(R.id.kanjilearn_tv_conlai);

        kanji = kanjiList.get(0);
        sotuconlai = kanjiList.size();
        conlai.setText(Integer.toString(sotuconlai));
        hien.setText(getJi(kanji, hira2kanji)[0]);

        Button button_lodi = (Button) findViewById(R.id.kanjilearn_btn_lodi);
        button_lodi.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                sotuconlai--;
                kanjiListDaThuoc.add(kanji);
                kanjiList.remove(kanji);
                kanji = getRandomKanji();
                hien.setText(getJi(kanji, hira2kanji)[HIEN]);
                conlai.setText(Integer.toString(sotuconlai));
                an.setText("");
                an_vi.setText("");
            }
        });

        Button button_dapan = (Button) findViewById(R.id.kanjilearn_btn_dapan);
        button_dapan.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                an.setText(getJi(kanji, hira2kanji)[AN]);
                an_vi.setText(getJi(kanji, hira2kanji)[AN_VN]);
            }
        });

        Button button_next = (Button) findViewById(R.id.kanjilearn_btn_next);
        button_next.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                kanjiListChuaThuoc.add(kanji);
                kanjiList.remove(kanji);
                kanji = getRandomKanji();
                hien.setText(getJi(kanji, hira2kanji)[HIEN]);
                an.setText("");
                an_vi.setText("");
            }


        });
    }

    private String[] getJi(Kanji kanji, boolean hira2kanji) {
        String result[] = new String[3];
        if (hira2kanji){
            result[HIEN] = kanji.getHiragana();
            result[AN] = kanji.getKanji();
        }
        else {
            result[HIEN] = kanji.getKanji();
            result[AN] = kanji.getHiragana();
        }
        result[AN_VN] = kanji.getVietnamese();
        return result;
    }

    public Kanji getRandomKanji() {

        if (kanjiList.size() == 0){
            if (kanjiListChuaThuoc.size() != 0) {
                kanjiList.addAll(kanjiListChuaThuoc);
                kanjiListChuaThuoc.removeAll(kanjiListChuaThuoc);
            }
            else {
                kanjiList.addAll(kanjiListDaThuoc);
                kanjiListDaThuoc.removeAll(kanjiListDaThuoc);
                sotuconlai = kanjiList.size();
            }
        }
        return kanjiList.get(random.nextInt(kanjiList.size()));
    }
}
