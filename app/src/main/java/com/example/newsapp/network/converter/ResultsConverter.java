package com.example.newsapp.network.converter;

import androidx.room.TypeConverter;

import com.example.newsapp.network.response.Results;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import static java.util.Collections.emptyList;

public class ResultsConverter {
    @TypeConverter
    public List<Results> stringToObject(String data) {
        if (data == null) {
            return emptyList();
        }
        Type listType = new TypeToken<List<Results>> () {}.getType();

        return new Gson().fromJson(data, listType);
    }

    @TypeConverter
    public String objectToString(List<Results> result) {
        return new Gson().toJson(result);
    }
}
