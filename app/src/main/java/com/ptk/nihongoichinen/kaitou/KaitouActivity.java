package com.ptk.nihongoichinen.kaitou;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v4.media.MediaMetadataCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.ptk.nihongoichinen.Alert;
import com.ptk.nihongoichinen.Constant;
import com.ptk.nihongoichinen.R;

public class KaitouActivity extends AppCompatActivity {

    EditText etKhoangCach;
    CheckBox cbN5;
    CheckBox cbN4;
    CheckBox cbN3;
    CheckBox cbN2;
    CheckBox cbN1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaitou);
        System.out.println("---------------------------------------------------------------------Kaitou Activity creating...");

        Button btnNext = (Button) KaitouActivity.this.findViewById(R.id.kaitou_btn_next);
        btnNext.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                etKhoangCach = (EditText) KaitouActivity.this.findViewById(R.id.kaitou_et_khoangcach);
                String tmp = etKhoangCach.getText().toString();
                System.out.print(tmp);
                if (tmp.equals(""))
                    Alert.alertNotification(Constant.TRUONGTRONG, Constant.TRUONGTRONG_YEUCAU, KaitouActivity.this);
                else {
                    float khoangCach = Float.parseFloat(tmp);
                    if (khoangCach < Constant.MINKC){
                        Alert.alertNotification(Constant.SOQUANHO, Constant.SOQUANHO_YEUCAU, KaitouActivity.this);
                    } else {
                        cbN5 = (CheckBox) KaitouActivity.this.findViewById(R.id.kaitou_cb_level0);
                        cbN4 = (CheckBox) KaitouActivity.this.findViewById(R.id.kaitou_cb_level1);
                        cbN3 = (CheckBox) KaitouActivity.this.findViewById(R.id.kaitou_cb_level2);
                        cbN2 = (CheckBox) KaitouActivity.this.findViewById(R.id.kaitou_cb_level3);
                        cbN1 = (CheckBox) KaitouActivity.this.findViewById(R.id.kaitou_cb_level4);

                        int[] levels = trans(cbN5, cbN4, cbN3, cbN2, cbN1);
                        if ((levels[0] + levels[1] + levels[2] + levels[3] + levels[4]) == 0)
                            Alert.alertNotification(Constant.CHUACHONLEVEL, Constant.CHUACHONLEVEL_YEUCAU, KaitouActivity.this);
                        else {
                            Intent intent = new Intent(KaitouActivity.this, KaitouLearnActivity.class);
                            intent.putExtra("khoangcach", khoangCach);
                            intent.putExtra("levels", levels);
                            KaitouActivity.this.startActivity(intent);
                        }
                    }
                }
            }
        });

        Button btnInfo = (Button) findViewById(R.id.kaitou_btn_info);
        btnInfo.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                Alert.alertNotification(Constant.THONGTINKAITOU_TITLE, Constant.THONGTINKAITOU, KaitouActivity.this);
            }
        });
    }

    private int[] trans(CheckBox cbN5, CheckBox cbN4, CheckBox cbN3, CheckBox cbN2, CheckBox cbN1) {
        int[] result = new int[5];
        result[0] = (cbN5.isChecked()) ? 1 : 0;
        result[1] = (cbN4.isChecked()) ? 1 : 0;
        result[2] = (cbN3.isChecked()) ? 1 : 0;
        result[3] = (cbN2.isChecked()) ? 1 : 0;
        result[4] = (cbN1.isChecked()) ? 1 : 0;
        return result;
    }

    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("---------------------------------------------------------------------Kaitou Activity starting...");
    }
    @Override
    protected void onStop(){
        super.onStop();
        System.out.println("---------------------------------------------------------------------Kaitou Activity stoping...");
    }
    @Override
    protected void onPause(){
        super.onPause();
        System.out.println("---------------------------------------------------------------------Kaitou Activity pausing...");
    }
    @Override
    protected void onResume(){
        super.onResume();
        System.out.println("---------------------------------------------------------------------Kaitou Activity resuming...");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        System.out.println("---------------------------------------------------------------------Kaitou Activity destroying...");
    }
}
