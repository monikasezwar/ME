package com.example.newsapp.view.homeview;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import com.example.newsapp.R;
import com.example.newsapp.common.Constants;
import com.example.newsapp.database.viewmodel.NewsViewModel;
import com.example.newsapp.view.detailview.NewsDetailActivity;
import com.example.newsapp.view.savedView.SavedArticleActivity;

import java.util.ArrayList;
import java.util.List;

public class NewsView implements CategoryAdapter.OnCategoryClickListener {

    private CategoryAdapter categoryAdapter;
    private RecyclerView rv;
    private NewsFragment mFragment;
    private NewsViewModel newsViewModel;
    private Button savedArticles;
    List<String> categoryList;

    public void onViewCreated(NewsFragment mFragment){
        this.mFragment = mFragment;
        newsViewModel = ViewModelProviders.of(mFragment).get(NewsViewModel.class);
        categoryList = new ArrayList<>();
        categoryList.add("Science");
        categoryList.add("Technology");
        categoryList.add("Business");
        categoryList.add("World");
        categoryList.add("Movies");
        categoryList.add("Travel");
        savedArticles = mFragment.requireActivity().findViewById(R.id.saved_article);
        setListeners();
        setAdapter();

    }

    private void setListeners() {
        savedArticles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mFragment.requireContext(), SavedArticleActivity.class);
                mFragment.getActivity().startActivity(intent);
            }
        });
    }

    private void setAdapter(){
        rv = mFragment.requireActivity().findViewById(R.id.rv);
        categoryAdapter = new CategoryAdapter(this,categoryList);
        rv.setAdapter(categoryAdapter);
    }

    @Override
    public void onCategoryClicked(String category) {
        newsViewModel.getRefreshedData(category);
        Intent intent = new Intent(mFragment.requireContext(), NewsDetailActivity.class);
        intent.putExtra(Constants.SELECTED_CATEGORY,category);
        mFragment.getActivity().startActivity(intent);
    }
}
