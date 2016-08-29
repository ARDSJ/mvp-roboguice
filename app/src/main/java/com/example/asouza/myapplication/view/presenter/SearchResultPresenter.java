package com.example.asouza.myapplication.view.presenter;

import com.example.asouza.myapplication.model.entity.ImageLinks;
import com.example.asouza.myapplication.model.entity.Item;
import com.example.asouza.myapplication.model.entity.Volumes;
import com.example.asouza.myapplication.model.service.VolumesService;
import com.example.asouza.myapplication.view.contract.SearchResultContrat;
import com.google.inject.Inject;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import roboguice.inject.ContextSingleton;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by asouza on 29/07/16.
 */
@ContextSingleton
public class SearchResultPresenter extends BasePresenter<SearchResultContrat.View> implements SearchResultContrat.Presenter {

    @Inject
    VolumesService volumesService;

    Observer<Volumes> volumesObserver = new Observer<Volumes>() {
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
    };

    @Override
    public void search(String query, Integer startIndex) {
        getView().searching();

        Observable<Volumes> share = volumesService.search(query, startIndex).distinct();

        share
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<Volumes, Boolean>() {
                    @Override
                    public Boolean call(Volumes volumes) {
                        return volumes.getItems().size() > 0;
                    }
                })
                .filter(new Func1<Volumes, Boolean>() {
                    @Override
                    public Boolean call(Volumes volumes) {
                        List<Item> items = volumes.getItems();
                        for (Item item : items) {
                            ImageLinks imageLinks = item.getVolumeInfo().getImageLinks();
                            if (null == imageLinks)
                                return false;
                        }
                        return true;
                    }
                })
                .subscribe(volumesObserver);
    }
}
