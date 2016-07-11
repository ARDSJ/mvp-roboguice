package com.example.asouza.myapplication.view.contract;

/**
 * Created by asouza on 11/07/16.
 */
public interface MainContract {

    interface View{

        void onSuccessGetText();

        void onErrorGetText();

    }

    interface Presenter{

        void presenteText();

    }

}
