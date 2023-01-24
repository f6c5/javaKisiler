package com.example.kisiler;

public class kisi {

    private int id;
    private String ad;
    private String numara;

    kisi(){

    }

    kisi(int id, String ad, String numara){
        this.ad = ad;
        this.numara = numara;
    }
    kisi(String ad, String numara){
        this.ad = ad;
        this.numara = numara;
    }

    public int getId() {
        return id;
    }

    public String getAd(){
        return ad;
    }

    public String getNumara(){
        return numara;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public void setNumara(String numara) {
        this.numara = numara;
    }
}
