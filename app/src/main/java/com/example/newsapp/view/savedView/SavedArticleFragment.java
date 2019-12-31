package com.example.newsapp.view.savedView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.newsapp.R;

public class SavedArticleFragment extends Fragment {
    private SavedArticleView savedArticleView;

    public SavedArticleFragment newInstance(){
        SavedArticleFragment savedArticleFragment = new SavedArticleFragment();
        return savedArticleFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.saved_article_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        savedArticleView = new SavedArticleView();
        savedArticleView.onViewCreated(this);
    }
}
