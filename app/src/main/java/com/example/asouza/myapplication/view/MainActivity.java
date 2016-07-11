package com.example.asouza.myapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.asouza.myapplication.R;
import com.example.asouza.myapplication.view.contract.MainContract;
import com.example.asouza.myapplication.view.presenter.MainPresenter;
import com.google.inject.Inject;

import roboguice.RoboGuice;
import roboguice.activity.RoboActionBarActivity;

public class MainActivity extends RoboActionBarActivity implements MainContract.View{

    @Inject
    MainPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter.presenteText();
    }

    @Override
    public void onSuccessGetText() {

    }

    @Override
    public void onErrorGetText() {

    }

    static {
        RoboGuice.setUseAnnotationDatabases(false);
    }

}
