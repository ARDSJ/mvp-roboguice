package com.example.asouza.myapplication.view.contract;

import com.example.asouza.myapplication.model.entity.Volumes;

/**
 * Created by asouza on 29/07/16.
 */
public interface SearchResultContrat{

    interface View{

        void searching();

        void successSearch(Volumes response);

        void errorSearch();

    }

    interface Presenter{

        void search(String query);

    }

}
