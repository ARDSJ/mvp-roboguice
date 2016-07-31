package com.example.asouza.myapplication.view;

import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.asouza.myapplication.R;
import com.example.asouza.myapplication.model.entity.Volumes;
import com.example.asouza.myapplication.util.UtilProgressDialog;
import com.example.asouza.myapplication.view.adapter.SearchResultAdapter;
import com.example.asouza.myapplication.view.contract.SearchResultContrat;
import com.google.inject.Inject;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.RxToolbar;
import com.jakewharton.rxbinding.widget.TextViewAfterTextChangeEvent;
import com.jakewharton.rxbinding.widget.TextViewEditorActionEvent;

import roboguice.activity.RoboActionBarActivity;
import roboguice.activity.RoboActivity;
import roboguice.context.event.OnCreateEvent;
import roboguice.event.Observes;
import roboguice.inject.ContentView;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

@ContentView(R.layout.activity_search_result)
public class SearchResultActivity extends RoboActionBarActivity implements SearchResultContrat.View {

    @InjectExtra(value = "paramSearchQuery", optional = true)
    String paramSearchQuery;

    @InjectView(R.id.input_search)
    EditText inputSearch;

    @InjectView(R.id.toolbar_search)
    Toolbar toolbarSearch;

    @InjectView(R.id.list_search_result)
    RecyclerView listSearchResult;

    @Inject
    SearchResultContrat.Presenter presenter;

    @Inject
    UtilProgressDialog progressDialog;

    @Inject
    SearchResultAdapter searchResultAdapter;

    @InjectResource(R.string.searching_loading_message)
    String searchingLoadingMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setup();
    }

    @Override
    public void setup() {

        presenter.search(paramSearchQuery);
        inputSearch.setText(paramSearchQuery);

        setSupportActionBar(toolbarSearch);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listSearchResult.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true));
        listSearchResult.setAdapter(searchResultAdapter);
        listSearchResult.setHasFixedSize(true);

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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void searching() {
        progressDialog.show(searchingLoadingMessage);
    }

    @Override
    public void successSearch(@NonNull Volumes response) {
        searchResultAdapter.addItems(response.getItems());
        progressDialog.dismiss();
    }

    @Override
    public void errorSearch() {
        progressDialog.dismiss();
    }
}