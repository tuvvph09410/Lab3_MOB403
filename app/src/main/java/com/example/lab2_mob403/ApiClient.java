package com.example.lab2_mob403;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static String BASE_URL ="https://tucaomypham.000webhostapp.com/";
    private static Retrofit retrofit;
    public static Retrofit getClient(){
        OkHttpClient client = new OkHttpClient();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        if(retrofit == null){

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofit;
    }
}
