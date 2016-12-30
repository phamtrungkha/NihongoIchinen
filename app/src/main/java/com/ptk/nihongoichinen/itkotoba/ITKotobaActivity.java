package com.ptk.nihongoichinen.itkotoba;

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

import java.util.ArrayList;
import java.util.List;

public class ITKotobaActivity extends AppCompatActivity{

    private EditText eFrom;
    private EditText eTo;
    private CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itkotoba);

        eFrom = (EditText) ITKotobaActivity.this.findViewById(R.id.itkotoba_et_from);
        eTo = (EditText) ITKotobaActivity.this.findViewById(R.id.itkotoba_et_to);
        checkBox = (CheckBox) ITKotobaActivity.this.findViewById(R.id.itkotoba_cb_all);
        checkBox.setOnClickListener(new CheckBox.OnClickListener(){
            @Override
            public void onClick(View v){
                if (((CheckBox) v).isChecked()){
                    eFrom.setText("-1");
                    eTo.setText("-1");
                    eFrom.setVisibility(View.INVISIBLE);
                    eTo.setVisibility(View.INVISIBLE);
                } else {
                    eFrom.setText("");
                    eTo.setText("");
                    eFrom.setVisibility(View.VISIBLE);
                    eTo.setVisibility(View.VISIBLE);
                }
            }
        });
        Button button = (Button) this.findViewById(R.id.itkotoba_btn_next);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ITKotobaActivity.this, ITKotobaListActivity.class);
                int from = (eFrom.getText().toString().equals(""))?0:Integer.parseInt(eFrom.getText().toString());
                int to = (eTo.getText().toString().equals(""))?0:Integer.parseInt(eTo.getText().toString());
                if (to == 0)
                    Alert.alertNotification(Constant.TUHOCTRONG, Constant.TUHOCTRONG_YEUCAU, ITKotobaActivity.this);
                else if (from <= to){
                    intent.putExtra("from", from);
                    intent.putExtra("to", to);
                    ITKotobaActivity.this.startActivity(intent);
                } else {
                    Alert.alertNotification(Constant.PHAMVISAI, Constant.PHAMVISAI_YEUCAU, ITKotobaActivity.this);
                }
            }
        });
    }
}
