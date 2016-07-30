package com.example.asouza.myapplication.view.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.asouza.myapplication.model.entity.Volumes;
import com.example.asouza.myapplication.model.service.VolumesService;
import com.example.asouza.myapplication.view.contract.MainContract;
import com.google.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by asouza on 11/07/16.
 */

public class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private static final String TAG = "MainPresenter";

    @Inject
    VolumesService volumesService;

    public MainPresenter(){
        Log.d(TAG, "MainPresenter: ");
    }

    @Override
    public void search(@NonNull String query) {
    }
}
