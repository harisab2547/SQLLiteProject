package com.example.marialartsapp.Model;

import androidx.annotation.NonNull;

public class MartialArt {
    private int martialArtid;
    private String martialArtname;
   private double martialArtPrice;
   private String martialArtColor;

    public MartialArt(int martialArtid, String martialArtname, double martialArtPrice, String martialArtColor) {
        this.martialArtid = martialArtid;
        this.martialArtname = martialArtname;
        this.martialArtPrice = martialArtPrice;
        this.martialArtColor = martialArtColor;
    }

    public int getMartialArtid() {
        return martialArtid;
    }

    public void setMartialArtid(int martialArtid) {
        this.martialArtid = martialArtid;
    }

    public String getMartialArtname() {
        return martialArtname;
    }

    public void setMartialArtname(String martialArtname) {
        this.martialArtname = martialArtname;
    }

    public double getMartialArtPrice() {
        return martialArtPrice;
    }

    public void setMartialArtPrice(double martialArtPrice) {
        this.martialArtPrice = martialArtPrice;
    }

    public String getMartialArtColor() {
        return martialArtColor;
    }

    public void setMartialArtColor(String martialArtColor) {
        this.martialArtColor = martialArtColor;
    }

    @NonNull
    @Override
    public String toString() {
        return getMartialArtname() + "\n" + getMartialArtPrice()+"\n" + getMartialArtColor()+"\n";
    }
}
