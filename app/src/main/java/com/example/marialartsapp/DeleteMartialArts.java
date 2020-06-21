package com.example.marialartsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.marialartsapp.Model.DatabaseHandler;
import com.example.marialartsapp.Model.MartialArt;

import java.util.ArrayList;

public class DeleteMartialArts extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_martial_arts);

        databaseHandler = new DatabaseHandler(DeleteMartialArts.this);
        updateTheUserInterface();

    }

    private void updateTheUserInterface() {

        ArrayList<MartialArt> allMartialArtsObject = databaseHandler.returnAllMartialObject();
        RelativeLayout relativeLayout = new RelativeLayout(DeleteMartialArts.this);
        ScrollView scrollView = new ScrollView(DeleteMartialArts.this);
        RadioGroup radioGroup = new RadioGroup(DeleteMartialArts.this);
        for (MartialArt martialArt : allMartialArtsObject){
            RadioButton radioButton = new RadioButton(getApplicationContext());
            radioButton.setId(martialArt.getMartialArtid());
            radioButton.setText(martialArt.toString());
            radioGroup.addView(radioButton);
        }
        radioGroup.setOnCheckedChangeListener(DeleteMartialArts.this);
        Button btnBack = new Button(DeleteMartialArts.this);
        btnBack.setText("Go Back");
        btnBack.setOnClickListener(DeleteMartialArts.this);

        scrollView.addView(radioGroup);

        RelativeLayout.LayoutParams btnParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT
        , RelativeLayout.LayoutParams.WRAP_CONTENT);
        btnParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        btnParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        btnParams.setMargins(0,0,0,70);
        relativeLayout.addView(btnBack,btnParams);

        ScrollView.LayoutParams scrollViewParams = new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT,
                ScrollView.LayoutParams.MATCH_PARENT);
        relativeLayout.addView(scrollView,scrollViewParams);
        setContentView(relativeLayout);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        databaseHandler.deleteMartialArtFromDatabase(checkedId);
        Toast.makeText(getApplicationContext(),checkedId+" is Been Deleted",Toast.LENGTH_SHORT).show();
        updateTheUserInterface();

    }

    @Override
    public void onClick(View v) {
        finish();

    }
}
