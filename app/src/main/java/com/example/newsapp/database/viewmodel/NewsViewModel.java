package com.example.newsapp.database.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.newsapp.database.entity.SavedNews;
import com.example.newsapp.database.repository.NewsRepository;
import com.example.newsapp.network.response.NewsResponse;

import java.util.List;

public class NewsViewModel extends AndroidViewModel {
    private NewsRepository newsRepository;

    public NewsViewModel(Application application) {
        super(application);
        newsRepository = new NewsRepository(application);
    }

    public LiveData<List<NewsResponse>> getNewsByCategory(String selectedCategory){
        return newsRepository.getNewsByCategory(selectedCategory);
    }

    public void getRefreshedData(String category){
        newsRepository.requestDataFromServer(category);
    }

    public LiveData<List<SavedNews>> getSavedNews(){
        return newsRepository.getSavedNews();
    }


    public void insertData(String title, String image, String authorName, String description, String urlToShare) {
        newsRepository.insertData(title, image, authorName,description,urlToShare);
    }
}
