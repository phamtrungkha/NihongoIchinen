package com.ptk.nihongoichinen.kaitou;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ToggleButton;

import com.ptk.nihongoichinen.Alert;
import com.ptk.nihongoichinen.MainActivity;
import com.ptk.nihongoichinen.R;
import com.ptk.nihongoichinen.Constant;

public class KaitouLearnActivity extends AppCompatActivity {

    private float khoangCach;
    private int[] levels;
    private ToggleButton toggleButton;
    private EditText etKhoangCach;
    public boolean binded = false;
    private KaitouService kaitouService;
    private boolean first = true;

    ServiceConnection kaitouServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            KaitouService.LocalKaitouBinder binder = (KaitouService.LocalKaitouBinder) service;
            kaitouService = binder.getService();
            binded = true;
            System.out.println("-------------+++++++++++++++++=******************-----------------------");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            binded = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kaitou_learn);
        System.out.println("---------------------------------------------------------------------Kaitou LEARN Activity creating...");

        Intent intent = getIntent();
        khoangCach = intent.getFloatExtra("khoangcach",1);
        levels = intent.getIntArrayExtra("levels");

        Intent intentSend = new Intent(KaitouLearnActivity.this, KaitouService.class);
        this.bindService(intentSend, kaitouServiceConnection, Context.BIND_AUTO_CREATE);


        Button btnKhoangCach = (Button) findViewById(R.id.kaitoulearn_btn_setDistance);
        btnKhoangCach.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                etKhoangCach = (EditText) findViewById(R.id.kaitoulearn_et_khoangcach);
                if (etKhoangCach.getText().toString().equals(""))
                    Alert.alertNotification(Constant.TRUONGTRONG, Constant.TRUONGTRONG_YEUCAU, KaitouLearnActivity.this);
                else {
                    float tmp = Float.parseFloat(etKhoangCach.getText().toString());
                    //khoangCach = (tmp < 0.05) ? MINKC : tmp;
                    if (tmp < Constant.MINKC) {
                        Alert.alertNotification(Constant.SOQUANHO, Constant.SOQUANHO_YEUCAU, KaitouLearnActivity.this);
                    } else {
                        KaitouLearnActivity.this.kaitouService.setKhoangCach(tmp);
                        etKhoangCach.setText("");
                    }
                }
            }
        });
        toggleButton = (ToggleButton) findViewById(R.id.kaitoulearn_tgbtn_pause);
        toggleButton.setOnClickListener(new ToggleButton.OnClickListener(){
            @Override
            public void onClick(View v){
                if(first) {
                    KaitouLearnActivity.this.kaitouService.prepare(khoangCach, levels);
                    first = false;
                }
                if (((ToggleButton)v).isChecked())
                    KaitouLearnActivity.this.kaitouService.play();
                else
                    KaitouLearnActivity.this.kaitouService.pause();
            }
        });
        Button btnRepeat = (Button) findViewById(R.id.kaitoulearn_btn_repeat);
        btnRepeat.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                toggleButton.setChecked(false);
                KaitouLearnActivity.this.kaitouService.repeat();
            }
        });
        Button btnRepeatLast = (Button) findViewById(R.id.kaitoulearn_btn_repeatlast);
        btnRepeatLast.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                toggleButton.setChecked(false);
                KaitouLearnActivity.this.kaitouService.repeatLast();
            }
        });
        Button btnShutdown = (Button) findViewById(R.id.kaitoulearn_btn_shutdown);
        btnShutdown.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                if (binded){
                    KaitouLearnActivity.this.kaitouService.setRuning(false);
                    KaitouLearnActivity.this.unbindService(kaitouServiceConnection);
                    binded = false;
                    Intent intentShutdown = new Intent(KaitouLearnActivity.this, MainActivity.class);
                    KaitouLearnActivity.this.startActivity(intentShutdown);
                }
            }
        });
    }

    @Override
    public void onBackPressed(){
        Intent intentShutdown = new Intent(KaitouLearnActivity.this, MainActivity.class);
        KaitouLearnActivity.this.startActivity(intentShutdown);
    }

    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("---------------------------------------------------------------------Kaitou LEARN Activity starting...");
    }
    @Override
    protected void onStop(){
        super.onStop();
        System.out.println("---------------------------------------------------------------------Kaitou LEARN Activity stoping...");
    }
    @Override
    protected void onPause(){
        super.onPause();
        System.out.println("---------------------------------------------------------------------Kaitou LEARN Activity pausing...");
    }
    @Override
    protected void onResume(){
        super.onResume();
        System.out.println("---------------------------------------------------------------------Kaitou LEARN Activity resuming...");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        System.out.println("---------------------------------------------------------------------Kaitou LEARN Activity destroying...");
    }
}
