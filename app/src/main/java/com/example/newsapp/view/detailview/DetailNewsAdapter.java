package com.example.newsapp.view.detailview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.network.response.NewsResponse;
import com.example.newsapp.network.response.Results;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailNewsAdapter extends RecyclerView.Adapter<DetailNewsAdapter.DetailNewsViewHolder> {

    private List<NewsResponse> resultsList;
    private String defaultUrl = "https://static01.nyt.com/images/2019/12/15/arts/15STAR-WARS/15STAR-WARS-thumbStandard.jpg";
    private OnItemViewClickedListener listener;

    public DetailNewsAdapter(DetailNewsView detailNewsView,List<NewsResponse> resultsList) {
        this.resultsList = resultsList;
        this.listener = detailNewsView;
    }

    @NonNull
    @Override
    public DetailNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_news_item,parent,false);
        return new DetailNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailNewsViewHolder holder, int position) {
        List<Results> results = resultsList.get(0).getResults();
        final String headingText = results.get(position).getTitle();
        holder.heading.setText(headingText);
        final String authorText = results.get(position).getByline();
        holder.authorName.setText(authorText);
        final String description = results.get(position).getAbstract();
        holder.description.setText(description);
        String url = "";
        final String urlToShare = results.get(position).getUrl();
        if(results.get(position).getMultimedia().size() > 0){
            url = results.get(position).getMultimedia().get(0).getUrl();
        if(!url.isEmpty()){
            Picasso.get()
                    .load(url)
                    .resize(100, 100)
                    .centerInside()
                    .into(holder.imageView);
          }
        }else{
            Picasso.get()
                    .load(defaultUrl)
                    .resize(100, 100)
                    .centerInside()
                    .into(holder.imageView);
        }
       holder.shareButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               listener.onShareButtonClicked(headingText,urlToShare);
           }
       });
        holder.itemView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                listener.onItemViewClicked(urlToShare);
            }
        });
        final String finalUrl = url;
        holder.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onSaveButtonClicked(headingText, finalUrl,authorText,description,urlToShare);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultsList.get(0).getResults().size();
    }

    class DetailNewsViewHolder extends RecyclerView.ViewHolder{
        TextView heading, authorName, description;
        Button shareButton,saveButton;
        ImageView imageView;
        public DetailNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            heading = itemView.findViewById(R.id.heading);
            authorName = itemView.findViewById(R.id.author_name);
            description = itemView.findViewById(R.id.description);
            shareButton = itemView.findViewById(R.id.share_button);
            saveButton = itemView.findViewById(R.id.save_button);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    public void setList(List<NewsResponse> resultsList){
        this.resultsList = resultsList;
    }

    interface OnItemViewClickedListener {
        void onShareButtonClicked(String title,String urlToShare);
        void onItemViewClicked(String url);
        void onSaveButtonClicked(String title, String image, String authorName,String description, String urlToShare);
    }


}
