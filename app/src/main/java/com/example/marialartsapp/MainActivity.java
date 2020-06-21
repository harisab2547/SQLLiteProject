package com.example.marialartsapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;

import com.example.marialartsapp.Model.DatabaseHandler;
import com.example.marialartsapp.Model.MartialArt;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    DatabaseHandler databaseHandler;
    private double totalMartialArtsPrice;
    private ScrollView scrollView;
    private int martialArtButtonWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseHandler = new DatabaseHandler(this);
        totalMartialArtsPrice = 0.0;
        scrollView = findViewById(R.id.scrollView);
        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        martialArtButtonWidth = screenSize.x / 2;

        modifyUserInterFace();
    }

    private void modifyUserInterFace() {

        ArrayList<MartialArt> allMartialArts = databaseHandler.returnAllMartialObject();
        scrollView.removeAllViewsInLayout();

        if (allMartialArts.size() > 0){
            GridLayout gridLayout = new GridLayout(MainActivity.this);
            gridLayout.setRowCount((allMartialArts.size()+1)/2);
            gridLayout.setColumnCount(2);
            MartialArtsButton[] martialArtsButtons = new MartialArtsButton[allMartialArts.size()];

            int index = 0;

            for (MartialArt martialArtObject : allMartialArts) {

                martialArtsButtons[index] = new MartialArtsButton(MainActivity.this,martialArtObject);
                martialArtsButtons[index].setText(martialArtObject.getMartialArtPrice() +"\n"+ martialArtObject.getMartialArtname() +"\n"
                        +martialArtObject.getMartialArtPrice());

                switch (martialArtObject.getMartialArtColor()){
                    case "Red":
                        martialArtsButtons[index].setBackgroundColor(Color.RED);
                        break;
                    case "red":
                        martialArtsButtons[index].setBackgroundColor(Color.RED);
                        break;
                    case "Blue":
                        martialArtsButtons[index].setBackgroundColor(Color.BLUE);
                        break;
                    case "blue":
                        martialArtsButtons[index].setBackgroundColor(Color.BLUE);
                        break;
                    case "green":
                        martialArtsButtons[index].setBackgroundColor(Color.GREEN);
                        break;
                    case "Green":
                        martialArtsButtons[index].setBackgroundColor(Color.GREEN);
                        break;
                    case "Purple":
                        martialArtsButtons[index].setBackgroundColor(Color.CYAN);
                        break;
                    case "purple":
                        martialArtsButtons[index].setBackgroundColor(Color.CYAN);
                        break;
                    case "Yellow":
                        martialArtsButtons[index].setBackgroundColor(Color.YELLOW);
                        break;
                    case "yellow":
                        martialArtsButtons[index].setBackgroundColor(Color.YELLOW);
                        break;
                    case "Black":
                        martialArtsButtons[index].setBackgroundColor(Color.BLACK);
                        break;
                    case "black":
                        martialArtsButtons[index].setBackgroundColor(Color.BLACK);
                        break;
                    case "White":
                        martialArtsButtons[index].setBackgroundColor(Color.WHITE);
                        break;
                    case "white":
                        martialArtsButtons[index].setBackgroundColor(Color.WHITE);
                        break;
                    default:
                        martialArtsButtons[index].setBackgroundColor(Color.GRAY);

                }
                martialArtsButtons[index].setOnClickListener(MainActivity.this);
                gridLayout.addView(martialArtsButtons[index],martialArtButtonWidth,
                        ViewGroup.LayoutParams.WRAP_CONTENT);


            }
            scrollView.addView(gridLayout);
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();
        if (id == R.id.addMartialarts) {
            Intent intent = new Intent(this, AddMartialArts.class);
            startActivity(intent);
            return true;
        }
        else if ( id == R.id.deleteMartialarts){

            Intent intent = new Intent(this,DeleteMartialArts.class);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.updateItem){

            Intent intent =  new Intent(this,UpdateMartialArts.class);
            startActivity(intent);
            return true;
        }
        else if(id == R.id.Reset){
            totalMartialArtsPrice = 0;
            Toast.makeText(MainActivity.this, "Value Rested", Toast.LENGTH_SHORT).show();
            return  true;
        }




        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        MartialArtsButton martialArtsButton = (MartialArtsButton) v;
        totalMartialArtsPrice = totalMartialArtsPrice + martialArtsButton.martialArtPrice();
        Toast.makeText(MainActivity.this,totalMartialArtsPrice+"",Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
    modifyUserInterFace();
    }
}
