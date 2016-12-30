package com.ptk.nihongoichinen.kanji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import com.ptk.nihongoichinen.MainDatabase;
import com.ptk.nihongoichinen.R;

import java.util.ArrayList;
import java.util.List;

public class KanjiListActivity extends AppCompatActivity {

    private ListView listView;
    private final List<Kanji> kanjiList = new ArrayList<Kanji>();
    private ArrayAdapter<Kanji> listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanji_list);

        Intent intent = getIntent();
        int[] scope = intent.getIntArrayExtra("scope");

        listView = (ListView) findViewById(R.id.kanjilist_lv_list);

        KanjiDatabase kdb = new KanjiDatabase();
        MainDatabase mdb = new MainDatabase(this);

        List<Kanji> list = kdb.getKanjis(mdb.getReadableDatabase(), scope);
        this.kanjiList.addAll(list);

        this.listViewAdapter = new ArrayAdapter<Kanji>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, this.kanjiList);

        this.listView.setAdapter(this.listViewAdapter);

        Button button = (Button) this.findViewById(R.id.kanjilist_btn_batdauhoc);
        button.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v){
                CheckBox hira2kanji = (CheckBox) KanjiListActivity.this.findViewById(R.id.kanjilist_cb_hira2kanji);
                Intent intentNext = new Intent(KanjiListActivity.this, KanjiLearnActivity.class);
                Bundle bundle = new Bundle();
                for (int i = 0; i < kanjiList.size(); i++){
                    bundle.putSerializable(("kl"+i), kanjiList.get(i));
                }

                intentNext.putExtra("chieuHoc", hira2kanji.isChecked());
                intentNext.putExtra("kanji", bundle);
                KanjiListActivity.this.startActivity(intentNext);
            }
        });
    }
}