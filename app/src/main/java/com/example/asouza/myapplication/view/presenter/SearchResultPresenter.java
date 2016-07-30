package com.example.asouza.myapplication.view.presenter;

import com.example.asouza.myapplication.model.entity.Volumes;
import com.example.asouza.myapplication.model.service.VolumesService;
import com.example.asouza.myapplication.view.contract.SearchResultContrat;
import com.google.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by asouza on 29/07/16.
 */
public class SearchResultPresenter extends BasePresenter<SearchResultContrat.View> implements SearchResultContrat.Presenter {

    @Inject
    VolumesService volumesService;

    @Override
    public void search(String query) {
        getView().searching();
        volumesService.search(query)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<Volumes, Boolean>() {
                    @Override
                    public Boolean call(Volumes volumes) {
                        return volumes.getItems().size()>0;
                    }
                })
                .subscribe(new Observer<Volumes>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                getView().errorSearch();
            }

            @Override
            public void onNext(Volumes volumes) {
                getView().successSearch(volumes);
            }
        });

    }
}
