package com.example.newsapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.newsapp.database.dao.NewsDao;
import com.example.newsapp.database.entity.SavedNews;
import com.example.newsapp.network.converter.MultimediaConverter;
import com.example.newsapp.network.converter.ResultsConverter;
import com.example.newsapp.network.response.NewsResponse;

@Database(entities = {NewsResponse.class, SavedNews.class},version = 2,exportSchema = false)

@TypeConverters({ResultsConverter.class, MultimediaConverter.class})

public abstract class NewsRoomDatabase  extends RoomDatabase {

    public abstract NewsDao newsDao();

    public static NewsRoomDatabase INSTANCE ;

    public static NewsRoomDatabase getDatabase(final Context context){
       if(INSTANCE == null){
           synchronized (NewsRoomDatabase.class){
               if(INSTANCE == null){
                   INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                           NewsRoomDatabase.class,"news_db").fallbackToDestructiveMigration().build();
               }
           }
       }
       return INSTANCE;
    }
}
