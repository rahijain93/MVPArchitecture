package com.example.rahi.demoarch.data.remote;

import com.example.rahi.demoarch.model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IApiServices {

    @GET("/posts")
    Call<List<Posts>> getList();

}
