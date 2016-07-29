package com.example.asouza.myapplication.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.example.asouza.myapplication.R;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

@ContentView(R.layout.activity_search_result)
public class SearchResultActivity extends RoboActivity {

    @InjectExtra(value = "paramSearchQuery",optional = true)
    String paramSearchQuery;

    @InjectView(R.id.input_search)
    EditText inputSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        inputSearch.setText(paramSearchQuery);

    }
}
