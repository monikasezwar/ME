package com.example.newsapp.common;

import android.content.Context;
import android.widget.Toast;

public class ServerError {
    public static void handleServerError(String code, Context context){
        if(code.equalsIgnoreCase(String.valueOf(JSONParserConstants.ERROR_CODE))){
            Toast.makeText(context, "Some error", Toast.LENGTH_SHORT).show();
        }else if(code.equalsIgnoreCase(String.valueOf(JSONParserConstants.ERROR_CODE_2))){
            Toast.makeText(context, "Server error", Toast.LENGTH_SHORT).show();
        }
    }
}
