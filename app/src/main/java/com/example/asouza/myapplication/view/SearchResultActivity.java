package com.example.asouza.myapplication.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.EditText;

import com.example.asouza.myapplication.R;
import com.example.asouza.myapplication.model.entity.Volumes;
import com.example.asouza.myapplication.util.UtilPagination;
import com.example.asouza.myapplication.util.UtilProgressDialog;
import com.example.asouza.myapplication.view.adapter.SearchResultAdapter;
import com.example.asouza.myapplication.view.contract.SearchResultContrat;
import com.google.inject.Inject;
import com.jakewharton.rxbinding.support.v7.widget.RecyclerViewScrollEvent;
import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewEditorActionEvent;

import roboguice.activity.RoboActionBarActivity;
import roboguice.context.event.OnCreateEvent;
import roboguice.event.Observes;
import roboguice.inject.ContentView;
import roboguice.inject.ContextSingleton;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;

@ContextSingleton
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
    SearchResultAdapter searchResultAdapter;

    @InjectResource(R.string.searching_loading_message)
    String searchingLoadingMessage;

    @Inject
    UtilPagination pagination;

    GridLayoutManager gridLayoutManager;

    @Override
    public void setup(@Observes OnCreateEvent onCreateEvent) {
        setSupportActionBar(toolbarSearch);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        gridLayoutManager = new GridLayoutManager(this, 2);
        listSearchResult.setLayoutManager(gridLayoutManager);
        listSearchResult.setAdapter(searchResultAdapter);
        listSearchResult.setHasFixedSize(false);
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

    private void initialSearch(@Observes OnCreateEvent onCreateEvent) {
        presenter.search(paramSearchQuery, pagination.getCurrentPage());
        inputSearch.setText(paramSearchQuery);
    }

    private void searchInputEvents(@Observes OnCreateEvent onCreateEvent) {
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
                        pagination.reset();
                        searchResultAdapter.reset();
                        String query = textViewEditorActionEvent.view().getText().toString();
                        presenter.search(query, pagination.getCurrentPage());
                    }
                });
    }

    private void listSearchResultEvents(@Observes OnCreateEvent onCreateEvent) {
        Observable<RecyclerViewScrollEvent> recyclerViewScrollEventObservable = RxRecyclerView.scrollEvents(listSearchResult);

        recyclerViewScrollEventObservable.subscribe(new Action1<RecyclerViewScrollEvent>() {
            @Override
            public void call(RecyclerViewScrollEvent recyclerViewScrollEvent) {
                int itemCount = searchResultAdapter.getItemCount();
                int lastCompletelyVisibleItemPosition = gridLayoutManager.findLastCompletelyVisibleItemPosition();
                if (lastCompletelyVisibleItemPosition >= (itemCount - 1)) {
                    searchResultAdapter.showLoading();
                    presenter.search(inputSearch.getText().toString(), pagination.incrementPage());
                }
            }
        });

    }

    @Override
    public void searching() {
        searchResultAdapter.showLoading();
    }

    @Override
    public void successSearch(@NonNull Volumes response) {
        searchResultAdapter.addItems(response.getItems());
        searchResultAdapter.hideLoading();
    }

    @Override
    public void errorSearch() {
        searchResultAdapter.hideLoading();
    }
}