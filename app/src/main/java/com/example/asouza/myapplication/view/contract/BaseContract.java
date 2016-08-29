package com.example.asouza.myapplication.view.contract;

import roboguice.context.event.OnCreateEvent;
import roboguice.event.Observes;

/**
 * Created by asouza on 30/07/16.
 */
public class BaseContract {
    interface BaseView{
        void setup(@Observes OnCreateEvent onCreateEvent);
    }
}
