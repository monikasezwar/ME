package com.example.newsapp.common;

import android.app.Application;

public class NewsApplication extends Application {

    public static NewsApplication instance;

    public static NewsApplication getInstance(){
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
}
