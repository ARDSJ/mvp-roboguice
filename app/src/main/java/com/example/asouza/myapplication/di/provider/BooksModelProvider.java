package com.example.asouza.myapplication.di.provider;

import com.example.asouza.myapplication.model.IBooksRestModel;
import com.google.inject.Inject;
import com.google.inject.Provider;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by asouza on 12/07/16.
 */
public class BooksModelProvider implements Provider<IBooksRestModel> {

    @Inject private Retrofit.Builder builderProvider;

    @Override
    public IBooksRestModel get() {

        return this.builderProvider.baseUrl("https://www.googleapis.com/books/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(IBooksRestModel.class);
    }
}
