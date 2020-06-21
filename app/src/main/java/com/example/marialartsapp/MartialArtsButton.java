package com.example.marialartsapp;

import android.content.Context;

import androidx.appcompat.widget.AppCompatButton;

import com.example.marialartsapp.Model.MartialArt;

public class MartialArtsButton extends AppCompatButton {

    private MartialArt martialArtObject;

    public MartialArtsButton(Context context, MartialArt martialArt) {
        super(context);

        martialArtObject = martialArt;
    }
    public String martialArtsColor(){
        return martialArtObject.getMartialArtColor();
    }
    public Double martialArtPrice(){
        return martialArtObject.getMartialArtPrice();
    }
}
