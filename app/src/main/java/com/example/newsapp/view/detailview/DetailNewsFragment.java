package com.example.newsapp.view.detailview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.newsapp.R;

public class DetailNewsFragment extends Fragment {

    private DetailNewsView detailNewsView;

    public DetailNewsFragment newInstance(Bundle bundle){
        DetailNewsFragment detailNewsFragment = new DetailNewsFragment();
        detailNewsFragment.setArguments(bundle);
        return detailNewsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        detailNewsView = new DetailNewsView();
        detailNewsView.onViewCreated(this);
    }
}
