package com.ptk.nihongoichinen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ptk.nihongoichinen.itkotoba.ITKotobaActivity;
import com.ptk.nihongoichinen.kaitou.KaitouActivity;
import com.ptk.nihongoichinen.kaitou.KaitouLearnActivity;
import com.ptk.nihongoichinen.kaitou.KaitouService;
import com.ptk.nihongoichinen.kanji.KanjiActivity;

import pl.com.salsoft.sqlitestudioremote.SQLiteStudioService;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteStudioService.instance().start(this);

        MainDatabase db = new MainDatabase(this);
       // db.createDefaultKanjis(MainActivity.this);

        Button buttonKanji = (Button) this.findViewById(R.id.btn_kanji);
        buttonKanji.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(MainActivity.this, KanjiActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        Button buttonITKotoba = (Button) this.findViewById(R.id.btn_itkotoba);
        buttonITKotoba.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(MainActivity.this, ITKotobaActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        Button buttonKaitou = (Button) this.findViewById(R.id.btn_kaitou);
        buttonKaitou.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v){
                if (KaitouService.isAlive)
                    MainActivity.this.onBackPressed();
                else {
                    Intent myIntent = new Intent(MainActivity.this, KaitouActivity.class);
                    MainActivity.this.startActivity(myIntent);
                }
            }
        });

        Button buttonAbout = (Button) this.findViewById(R.id.btn_about);
        buttonAbout.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(MainActivity.this, AboutActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });
/*
        Button buttonReibunN4N5 = (Button) this.findViewById(R.id.btn_reibunN4N5);
        buttonReibunN4N5.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(MainActivity.this, EmptyActivity.class);
                MainActivity.this.startActivity(myIntent);
                Intent myIntent = new Intent(MainActivity.this, ReibunN4N5Activity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        Button buttonReibunN3 = (Button) this.findViewById(R.id.btn_reibunN3);
        buttonReibunN3.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(MainActivity.this, EmptyActivity.class);
                MainActivity.this.startActivity(myIntent);
                /*Intent myIntent = new Intent(MainActivity.this, ReibunN3Activity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });

        Button buttonReibun = (Button) this.findViewById(R.id.btn_reibun);
        buttonReibun.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View v){
                Intent myIntent = new Intent(MainActivity.this, EmptyActivity.class);
                MainActivity.this.startActivity(myIntent);
                /*Intent myIntent = new Intent(MainActivity.this, ReibunActivity.class);
                MainActivity.this.startActivity(myIntent);
            }
        });*/
    }

    @Override
    protected void onDestroy() {
        SQLiteStudioService.instance().stop();
        super.onDestroy();
    }

    @Override
    public void onBackPressed(){
        if (KaitouService.isAlive)
            super.onBackPressed();
        else
            Alert.alertNotification(Constant.TATUNGDUNG, Constant.TATUNGDUNG_YEUCAU, MainActivity.this);
    }
}
