package com.example.newsapp.view.savedView;

import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.database.entity.SavedNews;
import com.example.newsapp.database.viewmodel.NewsViewModel;
import java.util.List;


public class SavedArticleView {

    private static final String TAG = "SavedArticleView";

    private RecyclerView savedRecyclerView;
    private SavedArticleFragment mFragment;
    private NewsViewModel mNewsViewModel;
    private SavedArticleAdapter savedArticleAdapter;
    private FrameLayout emptyListView;

    public void onViewCreated(SavedArticleFragment mFragment){
        this.mFragment = mFragment;
        mNewsViewModel = ViewModelProviders.of(mFragment).get(NewsViewModel.class);
        savedRecyclerView = mFragment.requireActivity().findViewById(R.id.saved_rv);
        emptyListView = mFragment.requireActivity().findViewById(R.id.list_empty_layout);
        setSavedData();
    }

    private void setSavedData() {

        mNewsViewModel.getSavedNews().observe(mFragment, new Observer<List<SavedNews>>() {
            @Override
            public void onChanged(@Nullable final List<SavedNews> savedNews) {
                if(savedNews.isEmpty()) {
                    Log.i(TAG,"database is empty");
                    emptyListView.setVisibility(View.VISIBLE);
                }else{
                    emptyListView.setVisibility(View.GONE);
                    if (savedRecyclerView.getAdapter() == null) {
                        recyclerViewSetUp(savedNews);
                    } else {
                        savedArticleAdapter.setList(savedNews);
                    }
                }
            }
        });
    }

    private void recyclerViewSetUp(List<SavedNews> savedNews){
        savedArticleAdapter = new SavedArticleAdapter(mFragment,savedNews);
        savedRecyclerView.setAdapter(savedArticleAdapter);
    }
}
