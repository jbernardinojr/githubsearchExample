package com.example.josebernardino.githubsearchexample.api;

import com.example.josebernardino.githubsearchexample.model.Repos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GitHub {
    public static final String API_BASE_URL = "https://api.github.com/";

    @GET("/users/{user}/repos")
    Call<List<Repos>> reposData(@Path("user") String user);
}
