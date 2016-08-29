package com.example.asouza.myapplication.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;

import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by asouza on 28/07/16.
 */
public class UtilIntent {


    private final Intent intent;

    @Inject
    Context context;

    public UtilIntent(){
        this.intent = new Intent();
    }

    public Intent getIntent() {
        return intent;
    }

    public void newIntent(Class<?> activityClass){
        intent.setClass(context,activityClass);
        context.startActivity(intent);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public void newIntentWithSharedElements(Class<?> activityClass, String transictionName, View element){

        intent.setClass(context,activityClass);

        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation((Activity) context, element, transictionName);

        context.startActivity(intent,options.toBundle());

    }

}
