package com.example.shubham.todoapprealm;

import android.app.Application;
import android.app.PendingIntent;

import io.realm.Realm;

/**
 * Created by shubham on 7/2/17.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
