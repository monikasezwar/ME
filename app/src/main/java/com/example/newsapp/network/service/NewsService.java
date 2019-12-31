package com.example.newsapp.network.service;

import com.example.newsapp.network.response.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NewsService {

    @GET("{category}.json")
    Call<NewsResponse> getNews(@Path("category") String category, @Query("api-key") String key);


}
