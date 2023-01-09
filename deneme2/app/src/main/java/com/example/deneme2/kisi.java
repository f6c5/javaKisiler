package com.example.deneme2;

public class kisi {
    public int id;
    public String ad;
    public String numara;

    public kisi(){}

    public kisi(int id, String ad, String numara){
        this.id = id;
        this.ad = ad;
        this.numara = numara;
    }

    public kisi(String ad, String numara){
        this.id = id;
        this.ad = ad;
        this.numara = numara;
    }

    public int getId(){return id; }

    public String getAd(){
        return ad;
    }

    public String getNumara(){
        return numara;
    }

    public void setAd(String ad){
        this.ad = ad;
    }

    public void setNumara(String numara){
        this.numara = numara;
    }

    public void setId(int id){this.id = id;}

}
