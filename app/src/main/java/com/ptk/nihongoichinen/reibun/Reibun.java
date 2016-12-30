package com.ptk.nihongoichinen.reibun;

import java.io.Serializable;

public class Reibun implements Serializable {

    private String reibun;
    private int bai;
    private int phan;
    private int dai;

    public Reibun(){

    }

    public Reibun(String reibunReibun, int reibunBai, int reibunPhan, int reibunDai){
        this.reibun = reibunReibun;
        this.bai = reibunBai;
        this.phan = reibunPhan;
        this.dai = reibunDai;
    }

    public String getReibun() {
        return reibun;
    }

    public void setReibun(String reibun) {
        this.reibun = reibun;
    }

    public int getBai() {
        return bai;
    }

    public void setBai(int bai) {
        this.bai = bai;
    }

    public int getPhan() {
        return phan;
    }

    public void setPhan(int phan) {
        this.phan = phan;
    }

    public int getDai() {
        return dai;
    }

    public void setDai(int dai) {
        this.dai = dai;
    }

    @Override
    public String toString(){
        return getReibun();
    }
}
