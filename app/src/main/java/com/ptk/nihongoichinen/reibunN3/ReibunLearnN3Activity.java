package com.ptk.nihongoichinen.reibunN3;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.ptk.nihongoichinen.MainDatabase;
import com.ptk.nihongoichinen.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReibunLearnN3Activity extends AppCompatActivity {

    private static final int TISO = 3;
    private MediaPlayer reibunMediaPlayer;
    private TextView reibunTV;
    private int socauconlai = 0;
    private List<ReibunN3> reibunList = new ArrayList<ReibunN3>();
    private List<ReibunN3> reibunListDaThuoc = new ArrayList<ReibunN3>();
    private List<ReibunN3> reibunListChuaThuoc = new ArrayList<ReibunN3>();
    private ReibunN3 reibun;
    private Random random = new Random();
    private String path = "";
    private long time = 0;
    private int tocDo = 0;
    private Handler handler;
    private boolean isRuning;
    private TextView socauconlaiET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reibun_learn_n3);

        reibunTV = (TextView) findViewById(R.id.reibun_learnN3_reibun);
        socauconlaiET = (TextView) findViewById(R.id.reibun_learnN3_conlai);

        Intent intent = getIntent();
        int[] bai = intent.getIntArrayExtra("bai");
        tocDo = Integer.parseInt(intent.getStringExtra("tocDo"));

        MainDatabase mdb = new MainDatabase(this);
        ReibunN3Database rdb = new ReibunN3Database();
        reibunList = rdb.getReibuns(mdb.getReadableDatabase(), bai);
        socauconlai = reibunList.size();
        socauconlaiET.setText(Integer.toString(socauconlai));


        handler = new Handler(){
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                reibunTV.setText(reibun.getReibun());
                reibunMediaPlayer.start();
            }
        };

        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.reibun_learnN3_tgbtn1);
        toggleButton.setOnClickListener(new ToggleButton.OnClickListener(){

            @Override
            public void onClick(View v){
                boolean checked = ((ToggleButton)v).isChecked();
                if (checked){
                    doStart();
                }
                else
                    doPausse();
            }
        });

        Button button = (Button) findViewById(R.id.reibun_learnN3_lodi);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                socauconlai--;
                socauconlaiET.setText(Integer.toString(socauconlai));
                daThuoc(reibun);
            }
        });
    }

    private void doStart() {

        isRuning = false;
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRuning) {
                    Log.e("ALERT","---------------------------------------------------------"+reibunList.size());
                    reibun = getRandomReibun();
                    chuaThuoc(reibun);
                    Message msg = handler.obtainMessage();
                    path = "android.resource://" + getPackageName() + "/raw/k" + Integer.toString(reibun.getPhan());
                    Uri uri = Uri.parse(path);
                    //reibunMediaPlayer.stop();
                    reibunMediaPlayer = MediaPlayer.create(ReibunLearnN3Activity.this, uri);
                    handler.sendMessage(msg);
                    //System.out.println((int) reibun.getDai()*1000*2/7*tocDo);
                    SystemClock.sleep(reibunMediaPlayer.getDuration() + ((int) reibun.getDai()*1000*TISO/7*tocDo));
                    reibunMediaPlayer.release();
                }
            }
        });
        isRuning = true;
        th.start();
    }

    private void doPausse() {
        isRuning = false;
    }

    private void daThuoc(ReibunN3 reibun) {
        reibunListDaThuoc.add(reibun);
        reibunListChuaThuoc.remove(reibun);
    }

    private void chuaThuoc(ReibunN3 reibun) {
        reibunListChuaThuoc.add(reibun);
        reibunList.remove(reibun);
    }

    public ReibunN3 getRandomReibun() {

        if (reibunList.size() == 0){
            if (reibunListChuaThuoc.size() != 0) {
                reibunList.addAll(reibunListChuaThuoc);
                reibunListChuaThuoc.removeAll(reibunListChuaThuoc);
            }
            else {
                reibunList.addAll(reibunListDaThuoc);
                reibunListDaThuoc.removeAll(reibunListDaThuoc);
                socauconlai = reibunList.size();
            }
        }
        return reibunList.get(random.nextInt(reibunList.size()));
    }
}