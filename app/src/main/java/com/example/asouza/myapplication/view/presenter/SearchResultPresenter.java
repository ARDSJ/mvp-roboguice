package com.example.asouza.myapplication.view.presenter;

import com.example.asouza.myapplication.model.entity.Volumes;
import com.example.asouza.myapplication.model.service.VolumesService;
import com.example.asouza.myapplication.view.contract.SearchResultContrat;
import com.google.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by asouza on 29/07/16.
 */
public class SearchResultPresenter extends BasePresenter<SearchResultContrat.View> implements SearchResultContrat.Presenter {

    @Inject
    VolumesService volumesService;

    @Override
    public void search(String query) {
        getView().searching();
        volumesService.search(query).enqueue(new Callback<Volumes>() {
            @Override
            public void onResponse(Call<Volumes> call, Response<Volumes> response) {
                getView().successSearch(response.body());
            }

            @Override
            public void onFailure(Call<Volumes> call, Throwable t) {
                getView().errorSearch();
            }
        });
    }
}
