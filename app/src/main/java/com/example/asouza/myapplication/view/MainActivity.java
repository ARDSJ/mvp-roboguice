package com.example.asouza.myapplication.view;

import android.os.Bundle;
import android.widget.EditText;

import com.example.asouza.myapplication.R;
import com.example.asouza.myapplication.model.entity.Volumes;
import com.example.asouza.myapplication.view.contract.MainContract;
import com.google.inject.Inject;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewAfterTextChangeEvent;

import roboguice.RoboGuice;
import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActionBarActivity implements MainContract.View{

    @InjectView(R.id.input_search)
    EditText inputSearch;

    @Inject
    MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setup();
    }

    private void setup() {

        Observable<TextViewAfterTextChangeEvent> observerInputSearch = RxTextView.afterTextChangeEvents(inputSearch).share();

        observerInputSearch
                .filter(new Func1<TextViewAfterTextChangeEvent, Boolean>() {
                    @Override
                    public Boolean call(TextViewAfterTextChangeEvent textViewAfterTextChangeEvent) {
                        return textViewAfterTextChangeEvent.editable().toString().trim().length() > 1;
                    }
                })
                .subscribe(new Action1<TextViewAfterTextChangeEvent>() {
            @Override
            public void call(TextViewAfterTextChangeEvent textViewAfterTextChangeEvent) {
                String query = textViewAfterTextChangeEvent.editable().toString();
                presenter.search(query);
            }
        });
    }

    static {
        RoboGuice.setUseAnnotationDatabases(false);
    }

    @Override
    public void successSearch(Volumes response) {

    }

    @Override
    public void errorSearch() {

    }

}
