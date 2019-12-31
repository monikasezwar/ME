package com.example.newsapp.view.detailview;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.newsapp.R;
import com.example.newsapp.common.Constants;

public class NewsDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        Bundle bundle = new Bundle();
        bundle.putString(Constants.SELECTED_CATEGORY,getIntent().getStringExtra(Constants.SELECTED_CATEGORY));
        if(savedInstanceState == null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().replace(R.id.container,new DetailNewsFragment().newInstance(bundle));
            fragmentTransaction.commit();
        }
    }
}
