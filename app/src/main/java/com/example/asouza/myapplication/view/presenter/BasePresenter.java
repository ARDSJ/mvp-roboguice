package com.example.asouza.myapplication.view.presenter;

import android.app.Activity;

import com.google.inject.Inject;

/**
 * Created by asouza on 11/07/16.
 */
public abstract class BasePresenter<T>{

    @Inject public Activity activity;
    @Inject public T view;

    public Activity getActivity() {
        return activity;
    }

    public T getView() {
        return view;
    }

}
