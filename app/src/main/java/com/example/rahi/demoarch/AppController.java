package com.example.rahi.demoarch;

import android.app.Application;

import com.example.rahi.demoarch.data.remote.ARoomDatabase;

public class AppController extends Application {

    private static ARoomDatabase roomDatabase;
    private static AppController appController;

    @Override
    public void onCreate() {
        super.onCreate();
        appController = this;
        roomDatabase = ARoomDatabase.getRoomDatabase(appController);
    }

    public static ARoomDatabase getRoomDatabase() {
        if (roomDatabase == null) {
            roomDatabase = ARoomDatabase.getRoomDatabase(appController);
        }
        return roomDatabase;
    }
}
