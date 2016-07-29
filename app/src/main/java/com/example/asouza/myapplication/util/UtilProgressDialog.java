package com.example.asouza.myapplication.util;

import android.app.ProgressDialog;
import android.content.Context;

import com.google.inject.Inject;

/**
 * Created by asouza on 28/07/16.
 */
public class UtilProgressDialog {

    @Inject
    Context context;

    private ProgressDialog progressDialog;

    public void show(String message){
        this.progressDialog = ProgressDialog.show(context, null,message, true);
    }

    public void dismiss(){
        this.progressDialog.hide();
    }

}