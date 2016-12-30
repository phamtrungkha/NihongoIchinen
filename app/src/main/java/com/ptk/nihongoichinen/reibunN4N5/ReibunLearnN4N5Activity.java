package com.ptk.nihongoichinen.reibunN4N5;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Message;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.ptk.nihongoichinen.MainDatabase;
import com.ptk.nihongoichinen.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReibunLearnN4N5Activity extends AppCompatActivity {

    private static final int TISO = 5;
    private MediaPlayer reibunMediaPlayer;
    private ImageView reibunIV;
    private int socauconlai = 0;
    private List<ReibunN4N5> reibunN4N5List = new ArrayList<ReibunN4N5>();
    private List<ReibunN4N5> reibunN4N5ListDaThuoc = new ArrayList<ReibunN4N5>();
    private List<ReibunN4N5> reibunN4N5ListChuaThuoc = new ArrayList<ReibunN4N5>();
    private ReibunN4N5 reibunN4N5;
    private Random random = new Random();
    private String path = "";
    private long time = 0;
    private int tocDo = 0;
    private Handler handler;
    private boolean isRuning;
    private TextView socauconlaiET;
    private ImageView imageView;
    private Context cnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reibun_learn_n4n5);

        cnt = this;
        reibunIV = (ImageView) findViewById(R.id.reibun_learnN4N5_reibun);
        socauconlaiET = (TextView) findViewById(R.id.reibun_learnN4N5_conlai);

        Intent intent = getIntent();
        int[] bai = intent.getIntArrayExtra("bai");
        tocDo = Integer.parseInt(intent.getStringExtra("tocDo"));

        MainDatabase mdb = new MainDatabase(this);
        ReibunN4N5Database rdb = new ReibunN4N5Database();
        reibunN4N5List = rdb.getReibuns(mdb.getReadableDatabase(), bai);
        socauconlai = reibunN4N5List.size();
        socauconlaiET.setText(Integer.toString(socauconlai));


        handler = new Handler(){
            public void handleMessage(Message msg){
                super.handleMessage(msg);
                reibunIV.setImageResource(cnt.getResources().getIdentifier("k" + reibunN4N5.getId(), "drawable", cnt.getPackageName()));
                reibunMediaPlayer.start();
            }
        };

        //test
        //imageView = (ImageView) findViewById(R.id.imageView);

        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.reibun_learnN4N5_tgbtn1);
        toggleButton.setOnClickListener(new ToggleButton.OnClickListener(){

            @Override
            public void onClick(View v){
                boolean checked = ((ToggleButton)v).isChecked();
                if (checked){
                    doStart();
                }
                else {
                    doPausse();
                }
            }
        });

        Button button = (Button) findViewById(R.id.reibun_learnN4N5_lodi);
        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View v){
                socauconlai--;
                socauconlaiET.setText(Integer.toString(socauconlai));
                daThuoc(reibunN4N5);
            }
        });
    }

    private void doStart() {

        isRuning = false;
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                while (isRuning) {
                    Log.e("ALERT","---------------------------------------------------------"+ reibunN4N5List.size());
                    reibunN4N5 = getRandomReibun();
                    chuaThuoc(reibunN4N5);
                    Message msg = handler.obtainMessage();
                    path = "android.resource://" + getPackageName() + "/raw/k" + Integer.toString(reibunN4N5.getId());
                    Uri uri = Uri.parse(path);
                    //reibunMediaPlayer.stop();
                    reibunMediaPlayer = MediaPlayer.create(ReibunLearnN4N5Activity.this, uri);
                    handler.sendMessage(msg);
                    //System.out.println((int) reibunN4N5.getDai()*1000*2/7*tocDo);
                    SystemClock.sleep(reibunMediaPlayer.getDuration() + ((int) reibunN4N5.getDai()*1000*TISO/945*tocDo));
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

    private void daThuoc(ReibunN4N5 reibunN4N5) {
        reibunN4N5ListDaThuoc.add(reibunN4N5);
        reibunN4N5ListChuaThuoc.remove(reibunN4N5);
    }

    private void chuaThuoc(ReibunN4N5 reibunN4N5) {
        reibunN4N5ListChuaThuoc.add(reibunN4N5);
        reibunN4N5List.remove(reibunN4N5);
    }

    public ReibunN4N5 getRandomReibun() {

        if (reibunN4N5List.size() == 0){
            if (reibunN4N5ListChuaThuoc.size() != 0) {
                reibunN4N5List.addAll(reibunN4N5ListChuaThuoc);
                reibunN4N5ListChuaThuoc.removeAll(reibunN4N5ListChuaThuoc);
            }
            else {
                reibunN4N5List.addAll(reibunN4N5ListDaThuoc);
                reibunN4N5ListDaThuoc.removeAll(reibunN4N5ListDaThuoc);
                socauconlai = reibunN4N5List.size();
            }
        }
        return reibunN4N5List.get(random.nextInt(reibunN4N5List.size()));
    }
}
