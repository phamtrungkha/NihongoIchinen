package com.ptk.nihongoichinen.reibun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.ptk.nihongoichinen.R;

import java.util.ArrayList;
import java.util.List;

public class ReibunActivity extends AppCompatActivity {

    private ListView listView;
    private EditText tocDo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reibun);

        listView = (ListView) findViewById(R.id.reibun_lv1);
        tocDo = (EditText) ReibunActivity.this.findViewById(R.id.reibun_tocdo);
        tocDo.setText("1");
        List<String> list = new ArrayList<String>();
        for (int i = 1; i < 51; i++)
            list.add(Integer.toString(i));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, list);
        listView.setAdapter(arrayAdapter);

        Button button = (Button) findViewById(R.id.reibun_next);
        button.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v){
                SparseBooleanArray sparseBooleanArray = listView.getCheckedItemPositions();
                int[] bai = toIntArray(sparseBooleanArray);
                Intent intent = new Intent(ReibunActivity.this, ReibunLearnActivity.class);
                intent.putExtra("tocDo", tocDo.getText().toString());
                intent.putExtra("bai", bai);
                ReibunActivity.this.startActivity(intent);
            }
        });
    }

    private int[] toIntArray(SparseBooleanArray sparseBooleanArray) {

        int[] result = new int[sparseBooleanArray.size()];
        for (int i = 0; i < sparseBooleanArray.size(); i++){
            result[i] = sparseBooleanArray.keyAt(i)+1;
        }

        return result;
    }
}
