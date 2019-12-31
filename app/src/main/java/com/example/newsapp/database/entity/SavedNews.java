package com.example.newsapp.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "saved_news")
public class SavedNews {
    @PrimaryKey(autoGenerate = true)
    public int _id = 0;
    private String title;
    private String byLine;
    private String abstractStr;
    private String imageUrl;
    private String urlToShare;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getByLine() {
        return byLine;
    }

    public void setByLine(String byLine) {
        this.byLine = byLine;
    }

    public String getAbstractStr() {
        return abstractStr;
    }

    public void setAbstractStr(String abstractStr) {
        this.abstractStr = abstractStr;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUrlToShare() {
        return urlToShare;
    }

    public void setUrlToShare(String urlToShare) {
        this.urlToShare = urlToShare;
    }
}
