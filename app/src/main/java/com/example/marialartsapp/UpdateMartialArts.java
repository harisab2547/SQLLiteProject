package com.example.marialartsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.marialartsapp.Model.DatabaseHandler;
import com.example.marialartsapp.Model.MartialArt;

import java.util.ArrayList;

public class UpdateMartialArts extends AppCompatActivity implements View.OnClickListener {

    DatabaseHandler databaseHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_martial_arts);
        databaseHandler = new DatabaseHandler(UpdateMartialArts.this);
        modifyUserInterface();


    }


    private void modifyUserInterface() {

        ArrayList<MartialArt> martialArtsObject = databaseHandler.returnAllMartialObject();
        if (martialArtsObject.size() > 0){
            ScrollView scrollView = new ScrollView(UpdateMartialArts.this);
            GridLayout gridLayout = new GridLayout(UpdateMartialArts.this);
            gridLayout.setRowCount(martialArtsObject.size());
            gridLayout.setColumnCount(5);

            TextView[] idTextViews = new TextView[martialArtsObject.size()];
            EditText[][] edtNamePriceAndColor = new EditText[martialArtsObject.size()][3];
            Button[] modifyButton = new Button[martialArtsObject.size()];

            Point screenSize = new Point();
            getWindowManager().getDefaultDisplay().getSize(screenSize);
            int screenWidth = screenSize.x;

            int index = 0;
            for (MartialArt martialArt : martialArtsObject){

                idTextViews[index] = new TextView(UpdateMartialArts.this);
                idTextViews[index].setGravity(Gravity.CENTER);
                idTextViews[index].setText(martialArt.getMartialArtid()+"");

                edtNamePriceAndColor[index][0] = new EditText(UpdateMartialArts.this);
                edtNamePriceAndColor[index][1] = new EditText(UpdateMartialArts.this);
                edtNamePriceAndColor[index][2] = new EditText(UpdateMartialArts.this);

                edtNamePriceAndColor[index][0].setText(martialArt.getMartialArtname());

                edtNamePriceAndColor[index][1].setText(martialArt.getMartialArtPrice()+"");
                edtNamePriceAndColor[index][1].setInputType(InputType.TYPE_CLASS_NUMBER);

                edtNamePriceAndColor[index][2].setText(martialArt.getMartialArtColor());
                edtNamePriceAndColor[index][0].setId(martialArt.getMartialArtid()+10);
                edtNamePriceAndColor[index][1].setId(martialArt.getMartialArtid()+20);
                edtNamePriceAndColor[index][2].setId(martialArt.getMartialArtid()+30);


                modifyButton[index] = new Button(UpdateMartialArts.this);
                modifyButton[index].setText("Modify");
                modifyButton[index].setId(martialArt.getMartialArtid());
                modifyButton[index].setOnClickListener(UpdateMartialArts.this);

                gridLayout.addView(idTextViews[index],(int) (screenWidth *0.05), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamePriceAndColor[index][0],(int) (screenWidth *0.20), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamePriceAndColor[index][1],(int) (screenWidth*0.20), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(edtNamePriceAndColor[index][2],(int) (screenWidth*0.20), ViewGroup.LayoutParams.WRAP_CONTENT);
                gridLayout.addView(modifyButton[index], (int) (screenWidth*0.35),ViewGroup.LayoutParams.WRAP_CONTENT);



                index++;
            }
            scrollView.addView(gridLayout);
            setContentView(scrollView);

        }

    }

    @Override
    public void onClick(View v) {

        int martialArtsIdObject = v.getId();

        EditText edtName = (EditText) findViewById(martialArtsIdObject +10);
        EditText edtPrice = (EditText) findViewById(martialArtsIdObject +20);
        EditText edtColor = (EditText) findViewById(martialArtsIdObject +30);

        String martialArtsStringName = edtName.getText().toString();
        String martialArtsStringColor = edtColor.getText().toString();
        String martialArtsStringPrice = edtPrice.getText().toString();

        try {

            double martialArtsStringDoubleValue = Double.parseDouble(martialArtsStringPrice);

            databaseHandler.modifyMartialArts(martialArtsIdObject,martialArtsStringName,martialArtsStringDoubleValue
            ,martialArtsStringColor);


        }catch ( Exception e){

        }

    }
}
