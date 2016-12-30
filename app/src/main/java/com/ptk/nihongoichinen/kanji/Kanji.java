package com.ptk.nihongoichinen.kanji;

import java.io.Serializable;

/**
 * Created by PHONGTRAN on 8/13/2016.
 */
public class Kanji implements Serializable{

    private int id;
    private int bai;
    private int level;
    private String kanji;
    private String hiragana;
    private String vietnamese;

    public Kanji(){

    }

    public Kanji(String kanjiKanji, String kanjiHiragana, String kanjiVietnamses, int kanjiBai, int kanjiLevel){
        this.kanji = kanjiKanji;
        this.hiragana = kanjiHiragana;
        this.vietnamese = kanjiVietnamses;
        this.bai = kanjiBai;
        this.level = kanjiLevel;
    }

    public Kanji(int kanjiId, String kanjiKanji, String kanjiHiragana, String kanjiVietnamses, int kanjiBai, int kanjiLevel){
        this.id = kanjiId;
        this.kanji = kanjiKanji;
        this.hiragana = kanjiHiragana;
        this.vietnamese = kanjiVietnamses;
        this.bai = kanjiBai;
        this.level = kanjiLevel;
    }

    public int getBai() {
        return bai;
    }

    public void setBai(int bai) {
        this.bai = bai;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getKanji() {
        return kanji;
    }

    public void setKanji(String kanji) {
        this.kanji = kanji;
    }

    public String getHiragana() {
        return hiragana;
    }

    public void setHiragana(String hiragana) {
        this.hiragana = hiragana;
    }

    public String getVietnamese() {
        return vietnamese;
    }

    public void setVietnamese(String vietnamese) {
        this.vietnamese = vietnamese;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        String result = this.kanji + "\n" + this.hiragana + "\n" + this.vietnamese;
        return result;
    }
}
