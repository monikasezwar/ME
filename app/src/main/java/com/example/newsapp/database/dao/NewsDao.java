package com.example.newsapp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.newsapp.database.entity.SavedNews;
import com.example.newsapp.network.response.NewsResponse;

import java.util.List;

@Dao
public interface NewsDao {

    @Query("SELECT * FROM news_response WHERE section == :section")
    LiveData<List<NewsResponse>> getLiveNewsByCategory(String section);

    @Query("SELECT * FROM news_response WHERE section == :section")
    List<NewsResponse> getNewsByCategory(String section);

    @Query("SELECT * FROM saved_news")
    LiveData<List<SavedNews>> getSavedNews();

    @Insert
    void insertAll(NewsResponse[] response);

    @Insert
    void insert(SavedNews[] savedNews);

    @Delete
    void delete(NewsResponse response);
}
