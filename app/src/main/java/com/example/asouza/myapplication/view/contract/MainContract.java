package com.example.asouza.myapplication.view.contract;

import com.example.asouza.myapplication.model.entity.Volumes;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by asouza on 11/07/16.
 */
public interface MainContract {

    interface View{

        void successSearch(Volumes response);

        void errorSearch();

    }

    interface Presenter{

        void search(String query);

    }

}
