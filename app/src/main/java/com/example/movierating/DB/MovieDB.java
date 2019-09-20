package com.example.movierating.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.movierating.Models.GenreRespondModel;

import java.util.ArrayList;
import java.util.List;

public class MovieDB extends SQLiteOpenHelper {
    public static final String DB_NAME="MovieDB";
    public static final int DB_VERSION=1;

    public final String GENRE_TABLE="Genre";
    public final String MOVIE_TABLE="Movies";

    public MovieDB(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION );
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE "+GENRE_TABLE+"(id INTEGER NOT NULL PRIMARY KEY UNIQUE,name TEXT)");
    }
    public void AddGenre(List<GenreRespondModel.GenreModel> genreModelList)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        for(int i=0;i<genreModelList.size();i++) {
            ContentValues cv = new ContentValues();
            cv.put("id", genreModelList.get(i).getId());
            cv.put("name", genreModelList.get(i).getName());


                db.insert(GENRE_TABLE, null, cv);


            }
db.close();

    }

    //Before we insert data from Server, we need to clear old records
    public void deleteAllGenre(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM "+GENRE_TABLE);
        db.close();
    }

    public List<GenreRespondModel.GenreModel> getGenreByIds(int [] genreIds){
        List<GenreRespondModel.GenreModel> genreModelList=new ArrayList<>();
        String whereCondition = "";
        for(int i = 0; i<genreIds.length; i++){
            if(i!= genreIds.length -1)
                whereCondition += genreIds[i]+",";
            else
                whereCondition += genreIds[i];
        }
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cs=db.rawQuery("SELECT * FROM "+GENRE_TABLE+ " WHERE id IN("+whereCondition+")",null);

        if(cs.moveToFirst())
        {

            while (!cs.isAfterLast())
            {
                GenreRespondModel.GenreModel genreModel=new GenreRespondModel.GenreModel(cs.getInt(cs.getColumnIndex("id")),cs.getString(cs.getColumnIndex("name")));
                genreModelList.add(genreModel);
                cs.moveToNext();
            }
        }
        cs.close();
        db.close();

        return  genreModelList;
    }
    public List<GenreRespondModel.GenreModel> GetGenre()
    {
        List<GenreRespondModel.GenreModel> genreModelList=new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cs=db.rawQuery("SELECT * FROM "+GENRE_TABLE,null);
        if(cs.moveToFirst())
        {

            while (!cs.isAfterLast())
            {
                GenreRespondModel.GenreModel genreModel=new GenreRespondModel.GenreModel(cs.getInt(cs.getColumnIndex("id")),cs.getString(cs.getColumnIndex("name")));
                    genreModelList.add(genreModel);
                cs.moveToNext();
            }
        }
        cs.close();
        db.close();
        return genreModelList;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
