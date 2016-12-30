package com.ptk.nihongoichinen.itkotoba;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Spinner;

import com.ptk.nihongoichinen.MainDatabase;
import com.ptk.nihongoichinen.R;

import java.util.ArrayList;
import java.util.List;

public class ITKotobaListActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ListView listView;
    private final List<ITKotoba> itKotobaList = new ArrayList<ITKotoba>();
    private ArrayAdapter<ITKotoba> listViewAdapter;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itkotoba_list);

        Intent intent = getIntent();
        int from = intent.getIntExtra("from",0);
        int to = intent.getIntExtra("to",0);
        String hoctu = intent.getStringExtra("hoctu");

        //get database
        ITKotobaDatabase itkdb = new ITKotobaDatabase();
        MainDatabase mdb = new MainDatabase(this);
        listView = (ListView) findViewById(R.id.itkotobalist_lst_lstV);
        List<ITKotoba> list = itkdb.getITKotobas(mdb.getReadableDatabase(), from, to);
        this.itKotobaList.addAll(list);
        this.listViewAdapter = new ArrayAdapter<ITKotoba>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, this.itKotobaList);
        this.listView.setAdapter(this.listViewAdapter);

        //set Spinner element
        spinner = (Spinner) this.findViewById(R.id.itkotobalist_spn_from);
        spinner.setOnItemSelectedListener(this);
        List<String> categories = new ArrayList<String>();
        categories.add("Kanji");
        categories.add("Katakana");
        categories.add("Hiragana");
        categories.add("Nghĩa Anh-Việt");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        Button button = (Button) this.findViewById(R.id.itkotobalist_btn_hoc);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                String selected = spinner.getSelectedItem().toString();
                Intent intentNext = new Intent(ITKotobaListActivity.this, ITKotobaLearnActivity.class);
                Bundle bundle = new Bundle();
                for (int i = 0; i < itKotobaList.size(); i++){
                    bundle.putSerializable(("ikl"+i), itKotobaList.get(i));
                }
                intentNext.putExtra("chieuhoc", selected);
                intentNext.putExtra("itKotoba", bundle);
                ITKotobaListActivity.this.startActivity(intentNext);
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}