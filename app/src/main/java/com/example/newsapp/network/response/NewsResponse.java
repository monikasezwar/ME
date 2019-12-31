package com.example.newsapp.network.response;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.newsapp.network.converter.ResultsConverter;
import com.google.gson.annotations.SerializedName;

import java.util.List;

@Entity(tableName = "news_response")
public class NewsResponse
{

   /* @SerializedName("status")
    private final String status;

    @SerializedName("num_results")
    private final int num_results;

    @SerializedName("results")
    private final List<News> results;

    public NewsResponse(String status, int num_results, List<News> results) {
        this.status = status;
        this.num_results = num_results;
        this.results = results;
    }

    public List<News> getResults() {
        return results;
    }*/

    @PrimaryKey(autoGenerate = true)
    public int _id = 0;

    private String copyright;

    private String last_updated;

    private String section;

    @TypeConverters(ResultsConverter.class)
    private List<Results> results;

    private String num_results;

    private String status;

    public String getCopyright ()
    {
        return copyright;
    }

    public void setCopyright (String copyright)
    {
        this.copyright = copyright;
    }

    public String getLast_updated ()
    {
        return last_updated;
    }

    public void setLast_updated (String last_updated)
    {
        this.last_updated = last_updated;
    }

    public String getSection ()
    {
        return section;
    }

    public void setSection (String section)
    {
        this.section = section;
    }

    public List<Results> getResults ()
    {
        return results;
    }

    public void setResults (List<Results> results)
    {
        this.results = results;
    }

    public String getNum_results ()
    {
        return num_results;
    }

    public void setNum_results (String num_results)
    {
        this.num_results = num_results;
    }

    public String getStatus ()
    {
        return status;
    }

    public void setStatus (String status)
    {
        this.status = status;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [copyright = "+copyright+", last_updated = "+last_updated+", section = "+section+", results = "+results+", num_results = "+num_results+", status = "+status+"]";
    }
}


