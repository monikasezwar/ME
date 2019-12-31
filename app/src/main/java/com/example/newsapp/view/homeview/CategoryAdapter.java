package com.example.newsapp.view.homeview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<String> categoryList;
    private OnCategoryClickListener listener;

    public CategoryAdapter(NewsView newsView, List<String> categoryList) {
        this.categoryList = categoryList;
        this.listener = newsView;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder,int position) {
        final String item = categoryList.get(position);
        holder.categoryName.setText(item);
        holder.categoryName.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
               listener.onCategoryClicked(item.toLowerCase());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.category_name);

        }
    }

    interface OnCategoryClickListener{
        void onCategoryClicked(String category);
    }
}
