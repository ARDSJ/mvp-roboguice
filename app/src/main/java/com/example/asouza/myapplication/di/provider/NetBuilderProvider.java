package com.example.asouza.myapplication.di.provider;

import com.google.inject.Provider;

import retrofit2.Retrofit;

/**
 * Created by asouza on 12/07/16.
 */
public class NetBuilderProvider implements Provider<Retrofit.Builder> {

    @Override
    public Retrofit.Builder get() {
        return new Retrofit.Builder();
    }

}
