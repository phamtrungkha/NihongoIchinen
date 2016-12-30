package com.ptk.nihongoichinen.itkotoba;

import java.io.Serializable;

/**
 * Created by Phong Tran on 12/18/2016.
 */
public class ITKotoba implements Serializable {
    private int id;
    private String kanji;
    private String hiragana;
    private String meaning;
    private String katakana;

    public ITKotoba(){

    }

    public ITKotoba(String kanjiKanji, String kanjiHiragana, String kanjiMeaning, String kanjiKatakana){
        this.kanji = kanjiKanji;
        this.hiragana = kanjiHiragana;
        this.meaning = kanjiMeaning;
        this.katakana = kanjiKatakana;
    }

    public ITKotoba(int kanjiId, String kanjiKanji, String kanjiHiragana, String kanjiMeaning, String kanjiKatakana){
        this.id = kanjiId;
        this.kanji = kanjiKanji;
        this.hiragana = kanjiHiragana;
        this.meaning = kanjiMeaning;
        this.katakana = kanjiKatakana;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getKatakana() {
        return katakana;
    }

    public void setKatakana(String katakana) {
        this.katakana = katakana;
    }

    @Override
    public String toString(){
        String result;
        if (this.kanji == null  ||  this.kanji.equals(""))
            result = this.katakana + "\n" + this.meaning;
        else
            result = this.kanji + "\n" + this.hiragana + "\n" + this.meaning + "\n" + this.katakana;
        return result;
    }
}
