package com.example.newsapp.workmanager;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.Worker;

import com.example.newsapp.common.NewsApplication;
import com.example.newsapp.database.repository.NewsRepository;

import java.util.ArrayList;
import java.util.List;


public class RefreshNewsWorker extends Worker {
    @NonNull
    @Override
    public Worker.Result doWork() {
        //Context context = getApplicationContext();
        List<String> categoryList = new ArrayList<>();
        categoryList.add("Science");
        categoryList.add("Technology");
        categoryList.add("Business");
        categoryList.add("World");
        categoryList.add("Movies");
        categoryList.add("Travel");

        NewsRepository newsRepository = new NewsRepository(NewsApplication.instance);
        for(int i=0;i<categoryList.size();i++){
            newsRepository.requestDataFromServer(categoryList.get(i));
        }

        //set output data
        Data refreshTime = new Data.Builder()
                .putString("refreshTime", ""+System.currentTimeMillis())
                .build();
        setOutputData(refreshTime);

        Log.d("RefreshDataWorker", "refreshing data....");
        return Worker.Result.SUCCESS;
    }
}