package com.example.newsapp.database.repository;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.newsapp.common.Constants;
import com.example.newsapp.common.ServerError;
import com.example.newsapp.common.Utils;
import com.example.newsapp.database.NewsRoomDatabase;
import com.example.newsapp.database.dao.NewsDao;
import com.example.newsapp.database.entity.SavedNews;
import com.example.newsapp.network.RetrofitClientInstance;
import com.example.newsapp.network.response.NewsResponse;
import com.example.newsapp.network.service.NewsService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsRepository {

    private NewsDao newsDao;
    private static final String TAG = "NewsRepository";
    private MutableLiveData<List<NewsResponse>> searchResults = new MutableLiveData<>();
    private Context context;

    private void asyncFinished(List<NewsResponse> results){
        searchResults.setValue(results);
    }

    public MutableLiveData<List<NewsResponse>> getSearchResults() {
        return searchResults;
    }

    public NewsRepository(Application application) {
        NewsRoomDatabase db;
        db = NewsRoomDatabase.getDatabase(application);
        newsDao = db.newsDao();
        context = application.getApplicationContext();

    }

    private void insertAsyncTask(NewsResponse newsResponse) {
        InsertAsyncTask task = new InsertAsyncTask();
        task.execute(newsResponse);
    }

    private class InsertAsyncTask extends
            AsyncTask<NewsResponse, Void, Void> {


        @Override
        protected Void doInBackground(NewsResponse... newsResponses) {
            newsDao.insertAll(newsResponses);
            return null;
        }
    }

    public void insertData(String title, String image, String authorName, String description, String urlToShare){
        SavedNews savedNews = new SavedNews();
        savedNews.setTitle(title);
        savedNews.setAbstractStr(description);
        savedNews.setImageUrl(image);
        savedNews.setByLine(authorName);
        savedNews.setUrlToShare(urlToShare);
        executeAsyncTask(savedNews);
    }

    public void executeAsyncTask(SavedNews savedNews){
        SaveAsyncTask task = new SaveAsyncTask();
        task.execute(savedNews);
    }

    private class SaveAsyncTask extends
            AsyncTask<SavedNews, Void, Void> {

        @Override
        protected Void doInBackground(SavedNews... savedNews) {
             newsDao.insert(savedNews);
             return null;
        }

    }

    public LiveData<List<NewsResponse>> getNewsByCategory(String selectedCategory){
        return newsDao.getLiveNewsByCategory(selectedCategory);
    }

    public LiveData<List<SavedNews>> getSavedNews(){
        return newsDao.getSavedNews();
    }


    public void requestDataFromServer(String category){
        if (Utils.isNetworkConnected(context)) {
            NewsService service = RetrofitClientInstance.getRetrofitInstance().create(NewsService.class);
            Call<NewsResponse> call = service.getNews(category, Constants.API_KEY);
            call.enqueue(new Callback<NewsResponse>() {
                @Override
                public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getStatus().equalsIgnoreCase("OK") ) {
                            insertAsyncTask(response.body());
                        } else {
                            ServerError.handleServerError(response.body().getStatus(),context);
                            System.out.println("call = [" + call + "], response = [" + response + "]");
                        }
                    }
                }

                @Override
                public void onFailure(Call<NewsResponse> call, Throwable t) {
                    System.out.println("Network Service failure");
                }

            });
        }else{
            System.out.println("No internet connected");
            Toast.makeText(context,"Please connect to your network",Toast.LENGTH_LONG);
        }
    }
}
