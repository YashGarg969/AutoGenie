package com.yashgarg969_androiddev.autogenie.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class Retrofithelper {

     private static final String BASE_URL="https://autogenie-api-model.onrender.com";

     public static synchronized Retrofit getInstance()
     {
         return new Retrofit.Builder()
                 .baseUrl(BASE_URL)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();
     }
}
