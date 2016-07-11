package com.example.asouza.myapplication.view.presenter;

import android.util.Log;

import com.example.asouza.myapplication.view.contract.MainContract;

import roboguice.inject.ContextSingleton;

/**
 * Created by asouza on 11/07/16.
 */
@ContextSingleton
public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private static final String TAG = "MainPresenter";

    public MainPresenter(){
        Log.d(TAG, "MainPresenter: ");
    }

    @Override
    public void presenteText() {
        Log.d(TAG, "presenteText: ");
    }
}
