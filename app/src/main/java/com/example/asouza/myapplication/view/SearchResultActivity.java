package com.example.asouza.myapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.asouza.myapplication.R;
import com.example.asouza.myapplication.model.entity.Volumes;
import com.example.asouza.myapplication.util.UtilProgressDialog;
import com.example.asouza.myapplication.view.contract.SearchResultContrat;
import com.google.inject.Inject;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewEditorActionEvent;

import roboguice.activity.RoboActivity;
import roboguice.context.event.OnCreateEvent;
import roboguice.event.Observes;
import roboguice.inject.ContentView;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

@ContentView(R.layout.activity_search_result)
public class SearchResultActivity extends RoboActivity implements SearchResultContrat.View{

    @InjectExtra(value = "paramSearchQuery",optional = true)
    String paramSearchQuery;

    @InjectView(R.id.input_search)
    EditText inputSearch;

    @Inject
    SearchResultContrat.Presenter presenter;

    @Inject
    UtilProgressDialog progressDialog;

    @InjectResource(R.string.searching_loading_message)
    String searchingLoadingMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void startSearch(@Observes OnCreateEvent onCreateEvent){
        inputSearch.setText(paramSearchQuery);
        presenter.search(paramSearchQuery);
    }

    public void setup(@Observes OnCreateEvent onCreateEvent){

        RxTextView.editorActionEvents(inputSearch)
                .observeOn(AndroidSchedulers.mainThread())
                .filter(new Func1<TextViewEditorActionEvent, Boolean>() {
                    @Override
                    public Boolean call(TextViewEditorActionEvent textViewEditorActionEvent) {
                        return textViewEditorActionEvent.view().getText().toString().trim().length() > 0;
                    }
                })
                .subscribe(new Action1<TextViewEditorActionEvent>() {
            @Override
            public void call(TextViewEditorActionEvent textViewEditorActionEvent) {
                String query = textViewEditorActionEvent.view().getText().toString();
                presenter.search(query);
            }
        });

    }

    @Override
    public void searching() {
        progressDialog.show(searchingLoadingMessage);
    }

    @Override
    public void successSearch(Volumes response) {
        progressDialog.dismiss();
    }

    @Override
    public void errorSearch() {
        progressDialog.dismiss();
    }
}