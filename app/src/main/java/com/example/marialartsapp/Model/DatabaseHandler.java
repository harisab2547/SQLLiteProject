package com.example.marialartsapp.Model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "martialArtsDatabase";
    private static final int DATABASE_VERSION = 1;
    private static final String MARTIAL_ARTS_TABLE = "MartialArts";
    private static final String ID_KEY = "id";
    private static final String NAME_KEY = "name";
    private static final String PRICE_KEY = "price";
    private static final String COLOR_KEY = "color";

    public DatabaseHandler(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createDatabaseSql = " create table " + MARTIAL_ARTS_TABLE + " ( " +ID_KEY +
                " integer primary key autoincrement " + ", " +NAME_KEY + " text" + ", " + PRICE_KEY + " real"
                +", " + COLOR_KEY + " text " +" ) ";
        db.execSQL(createDatabaseSql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists "+ MARTIAL_ARTS_TABLE);
        onCreate(db);
    }

    public void addMartialArts(MartialArt martialArtObject){

        SQLiteDatabase database = getWritableDatabase();
        String addMartialArtsCommand = "insert into " +
                MARTIAL_ARTS_TABLE + " values(null, '"+ martialArtObject.getMartialArtname()
                +"', '" + martialArtObject.getMartialArtPrice() + "', '"
                + martialArtObject.getMartialArtColor()
                +"')";
        database.execSQL(addMartialArtsCommand);
        database.close();

    }

    public void deleteMartialArtFromDatabase(int id){

        SQLiteDatabase database = getWritableDatabase();
        String deleteMartialArtCommad = "delete from " + MARTIAL_ARTS_TABLE + " where " + ID_KEY
                +" = " +id;
        database.execSQL(deleteMartialArtCommad);
        database.close();


    }
    public void modifyMartialArts(int martialArtsId, String MartialArtsName,double marialArtsPrice, String martialArtsColor ){
        SQLiteDatabase database = getWritableDatabase();
        String updateDatabase = "update "+MARTIAL_ARTS_TABLE +" set " + NAME_KEY +" = '"+ MartialArtsName
                +"'," + PRICE_KEY + " = '"+ marialArtsPrice +"',"+COLOR_KEY + " = '" +martialArtsColor +"'" +
                " where "+ID_KEY+" = "+ martialArtsId;

        database.execSQL(updateDatabase);
        database.close();
    }

    public ArrayList<MartialArt> returnAllMartialObject(){
        SQLiteDatabase database = getWritableDatabase();
        String sqlCommand = "select * from " + MARTIAL_ARTS_TABLE;
        Cursor cursor = database.rawQuery(sqlCommand,null);
        ArrayList<MartialArt> martialArts = new ArrayList<>();
        while (cursor.moveToNext()){
            MartialArt currentMartialArtsObject = new MartialArt(Integer.parseInt(
                    cursor.getString(0)),cursor.getString(1),cursor.getDouble(2),
                    cursor.getString(3));
            martialArts.add(currentMartialArtsObject);

        }
        database.close();

        return martialArts;
    }
    public MartialArt returnMartialArtObjectbyID(int id){
        SQLiteDatabase database = getWritableDatabase();
        String sqlCommand = "select * from " + MARTIAL_ARTS_TABLE + "where "+ ID_KEY +"="+id;
        Cursor cursor = database.rawQuery(sqlCommand,null);
        MartialArt martialArt = null;
        if (cursor.moveToFirst()){
            martialArt = new MartialArt(Integer.parseInt(
                    cursor.getString(0)),cursor.getString(1),
                    cursor.getDouble(2),cursor.getString(3));
        }
        database.close();
        return martialArt;
    }

}
