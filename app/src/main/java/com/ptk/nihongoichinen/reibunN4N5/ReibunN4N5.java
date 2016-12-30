package com.ptk.nihongoichinen.reibunN4N5;

import java.io.Serializable;

/**
 * Created by PHONGTRAN on 8/27/2016.
 */
public class ReibunN4N5 implements Serializable {

    private int id;
    private int bai;
    private int dai;

    public ReibunN4N5(){

    }

    public ReibunN4N5(int reibunN4N5Id, int reibunN4N5Bai, int reibunN4N5Dai){
        this.id = reibunN4N5Id;
        this.bai = reibunN4N5Bai;
        this.dai = reibunN4N5Dai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBai() {
        return bai;
    }

    public void setBai(int bai) {
        this.bai = bai;
    }

    public int getDai() {
        return dai;
    }

    public void setDai(int dai) {
        this.dai = dai;
    }

    @Override
    public String toString(){
        return Integer.toString(getDai());
    }
}
