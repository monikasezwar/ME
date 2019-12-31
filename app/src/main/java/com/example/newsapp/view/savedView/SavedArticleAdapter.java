package com.example.newsapp.view.savedView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.database.entity.SavedNews;
import com.example.newsapp.view.detailview.BrowserActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SavedArticleAdapter extends RecyclerView.Adapter<SavedArticleAdapter.SavedArticleClass>{

    private List<SavedNews> savedList;
    private SavedArticleFragment mFragment;
    private String defaultUrl = "https://static01.nyt.com/images/2019/12/15/arts/15STAR-WARS/15STAR-WARS-thumbStandard.jpg";

    public SavedArticleAdapter(SavedArticleFragment mFragment, List<SavedNews> savedList) {
        this.savedList = savedList;
        this.mFragment = mFragment;
    }

    @NonNull
    @Override
    public SavedArticleClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.saved_news_item,parent,false);
        return new SavedArticleClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SavedArticleClass holder, final int position) {
        final String headingText = savedList.get(position).getTitle();
        holder.heading.setText(headingText);
        final String authorText = savedList.get(position).getByLine();
        holder.authorName.setText(authorText);
        final String description = savedList.get(position).getAbstractStr();
        holder.description.setText(description);
        final String url = savedList.get(position).getImageUrl();
        if (!url.isEmpty()) {
            Picasso.get()
                    .load(url)
                    .resize(100, 100)
                    .centerInside()
                    .into(holder.imageView);
        } else {
            Picasso.get()
                    .load(defaultUrl)
                    .resize(100, 100)
                    .centerInside()
                    .into(holder.imageView);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mFragment.getContext(), BrowserActivity.class);
                intent.putExtra("url",savedList.get(position).getUrlToShare());
                mFragment.requireActivity().startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return savedList.size();
    }

    class SavedArticleClass extends RecyclerView.ViewHolder{
        TextView heading, authorName, description;
        ImageView imageView;

        public SavedArticleClass(@NonNull View itemView) {
            super(itemView);
            heading = itemView.findViewById(R.id.heading);
            authorName = itemView.findViewById(R.id.author_name);
            description = itemView.findViewById(R.id.description);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    public void setList(List<SavedNews> resultsList){
        this.savedList = resultsList;
    }
}
