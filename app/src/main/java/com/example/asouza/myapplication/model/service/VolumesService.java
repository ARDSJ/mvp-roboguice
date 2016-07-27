package com.example.asouza.myapplication.model.service;

import android.support.annotation.NonNull;

import com.example.asouza.myapplication.model.IBooksRestModel;
import com.example.asouza.myapplication.model.entity.Volumes;
import com.google.inject.Inject;

import retrofit2.Call;

/**
 * Created by asouza on 11/07/16.
 */

public class VolumesService {

    private static final String TAG = "VolumesService";

    @Inject
    IBooksRestModel booksRestModel;

    public VolumesService(){
    }

    public Call<Volumes> search(@NonNull String query){
        return booksRestModel.searchBook(query);
    }


}
