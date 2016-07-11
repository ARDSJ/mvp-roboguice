package com.example.asouza.myapplication.module;

import com.example.asouza.myapplication.view.MainActivity;
import com.example.asouza.myapplication.view.contract.MainContract;
import com.example.asouza.myapplication.view.presenter.MainPresenter;
import com.google.inject.AbstractModule;

/**
 * Created by asouza on 11/07/16.
 */
public class MainModule extends AbstractModule{
    @Override
    protected void configure() {
        bind(MainContract.View.class).to(MainActivity.class);
        bind(MainContract.Presenter.class).to(MainPresenter.class);
    }
}
