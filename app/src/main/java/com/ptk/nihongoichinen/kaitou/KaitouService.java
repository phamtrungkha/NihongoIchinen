package com.ptk.nihongoichinen.kaitou;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import com.ptk.nihongoichinen.R;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KaitouService extends Service {
    public static boolean isAlive = false;
    private static String LOG_TAG = "KaitouService";
    private final IBinder binder = new LocalKaitouBinder();
    private MediaPlayer mediaPlayer;
    private float khoangCach;
    private int[] levels;
    private int[] soCauTrongLevel;
    private String[] danhSachPhat;
    private Random random = new Random();
    private Boolean isRuning;
    private int index = 0;
    private String path;
    private List<Integer> listTmp;
    private Runnable runnable;
    private Thread thread;

    public class LocalKaitouBinder extends Binder{
        public KaitouService getService(){
            return KaitouService.this;
        }
    }
    public KaitouService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(LOG_TAG, "onBind");
        isAlive = true;
        return this.binder;
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i(LOG_TAG, "onRebind");
        super.onRebind(intent);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(LOG_TAG, "onUnbind");
        return true;
    }

    @Override
    public void onDestroy(){
        //mediaPlayer.release();
        super.onDestroy();
        isAlive = false;
    }

    public void prepare(float mkhoangCach, int[] mlevels){
        khoangCach = mkhoangCach;
        levels = mlevels;
        soCauTrongLevel = getSoCau();
        listTmp = createListTmp();
        danhSachPhat = createPlayList(listTmp);
        runnable = new Runnable() {
            @Override
            public void run() {
                while (isRuning) {
                    playMp3();
                    SystemClock.sleep(mediaPlayer.getDuration() + ((int) (khoangCach*60*1000)));
                }
            }
        };
    }

    public void play(){
        isRuning = true;
        thread = new Thread(runnable);
        thread.start();
    }

    public void pause(){
        isRuning = false;
    }

    public void repeat(){
        isRuning = false;
        if (index != 0)
            index--;
        playMp3();
    }

    public void repeatLast(){
        isRuning = false;
        if (index > 1)
            index -= 2;
        playMp3();
        index++;
    }

    public void setKhoangCach(float tmp) {
        khoangCach = tmp;
    }
    public void setRuning(boolean value) {
        isRuning = value;
    }

    private void playMp3(){
        path = "android.resource://" + getPackageName() + "/raw/" + danhSachPhat[index];
        Uri uri = Uri.parse(path);
        if (mediaPlayer != null)
            mediaPlayer.release();
        mediaPlayer = MediaPlayer.create(KaitouService.this, uri);
        mediaPlayer.start();
        System.out.println("=========================================== " + index + " " + danhSachPhat[index] + " " + danhSachPhat.length);
        if (index >= danhSachPhat.length){
            danhSachPhat = createPlayList(listTmp);
            index = 0;
        }
        else
            index++;
    }

    private List<Integer> createListTmp() {
        List<Integer> result = new ArrayList<Integer>();
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < soCauTrongLevel[i]; j++){
                result.add((i+1)*10000 + (j+1));
            }
        }
        return result;
    }

    private String[] createPlayList(List<Integer> listTmp) {
        System.out.println("---------------- create play list");
        String[] result = new String[listTmp.size()];
        int index2;
        int tongSoCau = listTmp.size();
        for (int i = tongSoCau; i > 0; i--){
            String name = "l";
            index2 = random.nextInt(i);
            name += Integer.toString((listTmp.get(index2)/10000-1));
            name += Integer.toString(listTmp.get(index2)%10000);
            result[tongSoCau-i] = name;
            listTmp.remove(index2);
        }
        return result;
    }

    private int[] getSoCau() {
        int[] result = new int[5];
        Field[] fields = R.raw.class.getFields();
        for (int i = 0; i < fields.length; i++){
            if (fields[i].getName().charAt(0) == 'l') {
                switch (fields[i].getName().charAt(1)) {
                    case '0':
                        result[0]++;
                        break;
                    case '1':
                        result[1]++;
                        break;
                    case '2':
                        result[2]++;
                        break;
                    case '3':
                        result[3]++;
                        break;
                    case '4':
                        result[4]++;
                        break;
                }
            }
        }
        for (int i = 0; i < 5; i++){
            if (levels[i] == 0)
                result[i] = 0;
        }
        return result;
    }
}
