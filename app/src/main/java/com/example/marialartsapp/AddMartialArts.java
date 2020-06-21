package com.example.marialartsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.marialartsapp.Model.DatabaseHandler;
import com.example.marialartsapp.Model.MartialArt;

public class AddMartialArts extends AppCompatActivity implements View.OnClickListener{
    DatabaseHandler databaseHandler ;
    EditText edtName, edtPrice, edtColor;
    Button btnAddMartialArts, btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_martial_arts);
        edtName = findViewById(R.id.editTextName);
        edtPrice = findViewById(R.id.editTextPrice);
        edtColor = findViewById(R.id.editTextColor);

        btnAddMartialArts = findViewById(R.id.btnAddMartialArts);
        btnBack = findViewById(R.id.btnBack);
        databaseHandler = new DatabaseHandler(AddMartialArts.this);
        btnAddMartialArts.setOnClickListener(AddMartialArts.this);
    }
        public void addMartialArtsToDatabase(){

            String nameValue = edtName.getText().toString();
            String priceValue = edtPrice.getText().toString();
            String colorValue = edtColor.getText().toString();
            try {
                Double priceDoubleValue = Double.parseDouble(priceValue);
                MartialArt martialArt = new MartialArt(0,nameValue,priceDoubleValue,colorValue);
                databaseHandler.addMartialArts(martialArt)
                ;
                Log.i("Data SAVED","data has been saved");
                Toast.makeText(getApplicationContext(), martialArt+"\n Has been saved", Toast.LENGTH_SHORT).show();

            } catch (Exception e) {

                e.printStackTrace();
                Toast.makeText(getApplicationContext(), ""+e.getMessage(), Toast.LENGTH_LONG).show();

                Log.i("Data SAVED","data has been not saved" + e.getMessage());
            }


        }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnAddMartialArts) {
            addMartialArtsToDatabase();
        }
        else if (v.getId() == R.id.btnBack) {
            this.finish();
        }
    }
}