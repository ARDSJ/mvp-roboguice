package com.example.asouza.myapplication.util;

import com.example.asouza.myapplication.R;

import roboguice.inject.ContextSingleton;
import roboguice.inject.InjectResource;

/**
 * Created by asouza on 30/07/16.
 */
@ContextSingleton
public class UtilPagination {

    @InjectResource(R.string.api_books_max_results_pagination)
    private String apiBooksMaxResultsPagination;

    private Integer currentPage = 0;

    public void reset(){
        this.currentPage = 0;
    }

    public Integer incrementPage() {
        this.currentPage +=Integer.parseInt(this.apiBooksMaxResultsPagination);
        return this.currentPage;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

}
