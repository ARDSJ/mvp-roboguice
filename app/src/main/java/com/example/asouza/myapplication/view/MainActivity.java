package com.example.asouza.myapplication.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.EditText;

import com.example.asouza.myapplication.R;
import com.example.asouza.myapplication.model.entity.Volumes;
import com.example.asouza.myapplication.util.UtilIntent;
import com.example.asouza.myapplication.util.UtilProgressDialog;
import com.example.asouza.myapplication.view.contract.MainContract;
import com.google.inject.Inject;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewEditorActionEvent;

import roboguice.RoboGuice;
import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectResource;
import roboguice.inject.InjectView;
import rx.functions.Action1;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity implements MainContract.View {

    @InjectResource(R.string.searching_loading_message)
    private String searchingMessage;

    @InjectResource(R.string.transiction_input_search)
    private String transictionInputSearch;

    @InjectView(R.id.input_search)
    EditText inputSearch;

    @InjectView(R.id.toolbar_search)
    Toolbar toolbarSearch;

    @Inject
    MainContract.Presenter presenter;

    @Inject
    UtilProgressDialog utilProgressDialog;

    @Inject
    UtilIntent utilIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setup();
    }

    @Override
    public void setup() {

        RxTextView.editorActionEvents(inputSearch)
                .subscribe(new Action1<TextViewEditorActionEvent>() {
                    @Override
                    public void call(TextViewEditorActionEvent textViewEditorActionEvent) {
                        String query = textViewEditorActionEvent.view().getText().toString();
                        utilIntent.getIntent().putExtra("paramSearchQuery",query);
                        utilIntent.newIntentWithSharedElements(SearchResultActivity.class,transictionInputSearch, toolbarSearch);
                    }
                });
    }

    static {
        RoboGuice.setUseAnnotationDatabases(false);
    }

    @Override
    public void searching() {
        utilProgressDialog.show(searchingMessage);
    }

    @Override
    public void successSearch(Volumes response) {
        utilProgressDialog.dismiss();
    }

    @Override
    public void errorSearch() {
        utilProgressDialog.dismiss();
    }

}
