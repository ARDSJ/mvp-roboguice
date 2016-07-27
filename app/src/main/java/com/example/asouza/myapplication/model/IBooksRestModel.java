package com.example.asouza.myapplication.model;

import com.example.asouza.myapplication.model.entity.Volumes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by asouza on 26/07/16.
 */
public interface IBooksRestModel {

    @GET("volumes")
    public Call<Volumes> searchBook(@Query("q") String query);

}
