package com.example.lab2_mob403.Interface;

import com.example.lab2_mob403.JSONResponse;
import com.example.lab2_mob403.Model.Movies;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface RequestInterface {
    @Headers({"application-id: MY-APPLICATION-ID",
            "secret-key: MY-SECRET-KEY",
            "application-type: REST"})
    @GET("android_networking_mob403/person_json_object.json")
    Call<List<Movies>> getJSONResponse();
}
