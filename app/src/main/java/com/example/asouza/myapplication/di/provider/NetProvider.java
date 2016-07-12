package com.example.asouza.myapplication.di.provider;

import com.google.inject.Inject;
import com.google.inject.Provider;

import retrofit2.Retrofit;

/**
 * Created by asouza on 12/07/16.
 */
public class NetProvider implements Provider{

    @Inject NetBuilderProvider builderProvider;

    @Override
    public  get() {
        return new Retrofit.Builder()
                .baseUrl("")
                .build()
                .create(service.getClass());
    }
}
