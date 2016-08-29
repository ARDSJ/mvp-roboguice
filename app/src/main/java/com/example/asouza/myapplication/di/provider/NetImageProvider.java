package com.example.asouza.myapplication.di.provider;

import android.content.Context;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.squareup.picasso.Picasso;

import roboguice.inject.ContextSingleton;

/**
 * Created by asouza on 30/07/16.
 */
@ContextSingleton
public class NetImageProvider implements Provider<Picasso> {
    @Inject
    Context context;

    @Override
    public Picasso get() {
        return new Picasso.Builder(context).build();
    }
}
