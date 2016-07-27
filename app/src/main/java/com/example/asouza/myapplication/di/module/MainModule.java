package com.example.asouza.myapplication.di.module;

import com.example.asouza.myapplication.di.provider.BooksModelProvider;
import com.example.asouza.myapplication.di.provider.NetBuilderProvider;
import com.example.asouza.myapplication.model.IBooksRestModel;
import com.example.asouza.myapplication.view.MainActivity;
import com.example.asouza.myapplication.view.contract.MainContract;
import com.example.asouza.myapplication.view.presenter.MainPresenter;
import com.google.inject.AbstractModule;

import retrofit2.Retrofit;

/**
 * Created by asouza on 11/07/16.
 */
public class MainModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(MainContract.View.class).to(MainActivity.class);
        bind(MainContract.Presenter.class).to(MainPresenter.class);
        bind(Retrofit.Builder.class).toProvider(NetBuilderProvider.class).asEagerSingleton();
        bind(IBooksRestModel.class).toProvider(BooksModelProvider.class).asEagerSingleton();
    }
}
