package com.ptk.nihongoichinen.kanji;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.ptk.nihongoichinen.Alert;
import com.ptk.nihongoichinen.Constant;
import com.ptk.nihongoichinen.R;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;

public class KanjiActivity extends AppCompatActivity {

    private CheckBox level0;
    private CheckBox level1;
    private CheckBox level2;
    private CheckBox level3;
    private CheckBox level4;
    private EditText lv0From;
    private EditText lv1From;
    private EditText lv2From;
    private EditText lv3From;
    private EditText lv4From;
    private EditText lv0To;
    private EditText lv1To;
    private EditText lv2To;
    private EditText lv3To;
    private EditText lv4To;
    private static final int ALL = -1;
    private int[] scope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kanji);

        scope = new int[15];
        lv0From = (EditText) KanjiActivity.this.findViewById(R.id.kanji_et_level0_from);
        lv0To = (EditText) KanjiActivity.this.findViewById(R.id.kanji_et_level0_to);
        lv1From = (EditText) KanjiActivity.this.findViewById(R.id.kanji_et_level1_from);
        lv1To = (EditText) KanjiActivity.this.findViewById(R.id.kanji_et_level1_to);
        lv2From = (EditText) KanjiActivity.this.findViewById(R.id.kanji_et_level2_from);
        lv2To = (EditText) KanjiActivity.this.findViewById(R.id.kanji_et_level2_to);
        lv3From = (EditText) KanjiActivity.this.findViewById(R.id.kanji_et_level3_from);
        lv3To = (EditText) KanjiActivity.this.findViewById(R.id.kanji_et_level3_to);
        lv4From = (EditText) KanjiActivity.this.findViewById(R.id.kanji_et_level4_from);
        lv4To = (EditText) KanjiActivity.this.findViewById(R.id.kanji_et_level4_to);

        level0 = (CheckBox) KanjiActivity.this.findViewById(R.id.kanji_cb_level0_all);
        level0.setOnClickListener(new CheckBox.OnClickListener(){
            @Override
            public void onClick(View v){
                if (((CheckBox) v).isChecked()){
                    lv0From.setText("-1");
                    lv0To.setText("-1");
                    lv0From.setVisibility(View.INVISIBLE);
                    lv0To.setVisibility(View.INVISIBLE);

                } else {
                    lv0From.setText("");
                    lv0To.setText("");
                    lv0From.setVisibility(View.VISIBLE);
                    lv0To.setVisibility(View.VISIBLE);
                }
            }
        });
        level1 = (CheckBox) KanjiActivity.this.findViewById(R.id.kanji_cb_level1_all);
        level1.setOnClickListener(new CheckBox.OnClickListener(){
            @Override
            public void onClick(View v){
                if (((CheckBox) v).isChecked()){
                    lv1From.setText("-1");
                    lv1To.setText("-1");
                    lv1From.setVisibility(View.INVISIBLE);
                    lv1To.setVisibility(View.INVISIBLE);

                } else {
                    lv1From.setText("");
                    lv1To.setText("");
                    lv1From.setVisibility(View.VISIBLE);
                    lv1To.setVisibility(View.VISIBLE);
                }
            }
        });
        level2 = (CheckBox) KanjiActivity.this.findViewById(R.id.kanji_cb_level2_all);
        level2.setOnClickListener(new CheckBox.OnClickListener(){
            @Override
            public void onClick(View v){
                if (((CheckBox) v).isChecked()){
                    lv2From.setText("-1");
                    lv2To.setText("-1");
                    lv2From.setVisibility(View.INVISIBLE);
                    lv2To.setVisibility(View.INVISIBLE);

                } else {
                    lv2From.setText("");
                    lv2To.setText("");
                    lv2From.setVisibility(View.VISIBLE);
                    lv2To.setVisibility(View.VISIBLE);
                }
            }
        });
        level3 = (CheckBox) KanjiActivity.this.findViewById(R.id.kanji_cb_level3_all);
        level3.setOnClickListener(new CheckBox.OnClickListener(){
            @Override
            public void onClick(View v){
                if (((CheckBox) v).isChecked()){
                    lv3From.setText("-1");
                    lv3To.setText("-1");
                    lv3From.setVisibility(View.INVISIBLE);
                    lv3To.setVisibility(View.INVISIBLE);

                } else {
                    lv3From.setText("");
                    lv3To.setText("");
                    lv3From.setVisibility(View.VISIBLE);
                    lv3To.setVisibility(View.VISIBLE);
                }
            }
        });
        level4 = (CheckBox) KanjiActivity.this.findViewById(R.id.kanji_cb_level4_all);
        level4.setOnClickListener(new CheckBox.OnClickListener(){
            @Override
            public void onClick(View v){
                if (((CheckBox) v).isChecked()){
                    lv4From.setText("-1");
                    lv4To.setText("-1");
                    lv4From.setVisibility(View.INVISIBLE);
                    lv4To.setVisibility(View.INVISIBLE);

                } else {
                    lv4From.setText("");
                    lv4To.setText("");
                    lv4From.setVisibility(View.VISIBLE);
                    lv4To.setVisibility(View.VISIBLE);
                }
            }
        });

        Button btnNext = (Button) this.findViewById(R.id.kanji_btn_next);
        btnNext.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                scope[0] = 0;
                scope[1] = (lv0From.getText().toString().equals(""))?0:Integer.parseInt(lv0From.getText().toString());
                scope[2] = (lv0To.getText().toString().equals(""))?0:Integer.parseInt(lv0To.getText().toString());
                scope[3] = 1;
                scope[4] = (lv1From.getText().toString().equals(""))?0:Integer.parseInt(lv1From.getText().toString());
                scope[5] = (lv1To.getText().toString().equals(""))?0:Integer.parseInt(lv1To.getText().toString());
                scope[6] = 2;
                scope[7] = (lv2From.getText().toString().equals(""))?0:Integer.parseInt(lv2From.getText().toString());
                scope[8] = (lv2To.getText().toString().equals(""))?0:Integer.parseInt(lv2To.getText().toString());
                scope[9] = 3;
                scope[10] = (lv3From.getText().toString().equals(""))?0:Integer.parseInt(lv3From.getText().toString());
                scope[11] = (lv3To.getText().toString().equals(""))?0:Integer.parseInt(lv3To.getText().toString());
                scope[12] = 4;
                scope[13] = (lv4From.getText().toString().equals(""))?0:Integer.parseInt(lv4From.getText().toString());
                scope[14] = (lv4To.getText().toString().equals(""))?0:Integer.parseInt(lv4To.getText().toString());

                Intent intent = new Intent(KanjiActivity.this, KanjiListActivity.class);
                if (check(scope)){
                    intent.putExtra("scope", scope);
                    KanjiActivity.this.startActivity(intent);
                } else {

                }
            }
        });

        Button btnInfo = (Button) findViewById(R.id.kanji_btn_info);
        btnInfo.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                Alert.alertNotification(Constant.THONGTINKANJI_TITLE, Constant.THONGTINKANJI, KanjiActivity.this);
            }
        });
    }

    @Override
    protected void onDestroy() {
        SQLiteStudioService.instance().stop();
        super.onDestroy();
    }


    private boolean check(int[] scope) {
        boolean result = true;
        int tong = 0;
        for (int i = 0; i < 5; i++){
            tong += (scope[i*3+1] + scope[i*3+2]);
            if (scope[i*3+1] > scope[i*3+2])
                result = false;
        }
        if (result == false)
            Alert.alertNotification(Constant.PHAMVISAI, Constant.PHAMVISAI_YEUCAU, KanjiActivity.this);
        else if (tong == 0) {
            Alert.alertNotification(Constant.BAIHOCTRONG, Constant.BAIHOCTRONG_YEUCAU, KanjiActivity.this);
            result = false;
        }
        return result;
    }
}
