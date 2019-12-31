package com.example.newsapp.view.detailview;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.common.Constants;
import com.example.newsapp.database.viewmodel.NewsViewModel;
import com.example.newsapp.network.response.NewsResponse;

import java.util.List;


public class DetailNewsView implements DetailNewsAdapter.OnItemViewClickedListener {

    private NewsViewModel mNewsViewModel;
    private RecyclerView detailRecyclerView;
    private DetailNewsAdapter detailNewsAdapter;
    private DetailNewsFragment mFragment;
    private RelativeLayout progressbar;
    private static final String TAG = "DetailNewsView";


    public void onViewCreated(DetailNewsFragment mFragment){
        this.mFragment = mFragment;
        mNewsViewModel = ViewModelProviders.of(mFragment).get(NewsViewModel.class);
        detailRecyclerView = mFragment.requireActivity().findViewById(R.id.detail_rv);
        progressbar = mFragment.requireActivity().findViewById(R.id.progressBar);
        observerSetup();
    }

    private void observerSetup() {
        String selectedCategory = mFragment.getArguments().getString(Constants.SELECTED_CATEGORY);

        mNewsViewModel.getNewsByCategory(selectedCategory).observe(mFragment, new Observer<List<NewsResponse>>() {
            @Override
            public void onChanged(@Nullable final List<NewsResponse> news) {
                if(news.isEmpty()) {
                    Log.i(TAG,"database is empty");
                }else{
                    progressbar.setVisibility(View.GONE);
                    if (detailRecyclerView.getAdapter() == null) {
                        recyclerViewSetUp(news);
                    } else {
                        detailNewsAdapter.setList(news);
                    }
                }
            }
        });
    }

    private void recyclerViewSetUp(List<NewsResponse> news){
        detailNewsAdapter = new DetailNewsAdapter(this,news);
        detailRecyclerView.setAdapter(detailNewsAdapter);
    }


    @Override
    public void onShareButtonClicked(String title,String urlToShare) {
        Intent share = new Intent(Intent.ACTION_SEND);
        share.setType("text/plain");
        share.putExtra(Intent.EXTRA_SUBJECT, title);
        share.putExtra(Intent.EXTRA_TEXT, urlToShare);
        mFragment.requireActivity().startActivity(Intent.createChooser(share, "Share Link!"));
    }

    @Override
    public void onItemViewClicked(String url) {
        Intent intent = new Intent(mFragment.getContext(),BrowserActivity.class);
        intent.putExtra("url",url);
        mFragment.requireActivity().startActivity(intent);
    }

    @Override
    public void onSaveButtonClicked(String title,String image, String authorName, String description, String urlToshare) {
        mNewsViewModel.insertData(title,image,authorName,description,urlToshare);
    }
}
