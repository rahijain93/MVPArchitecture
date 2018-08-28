package com.example.rahi.demoarch.data.remote;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesRepository implements IDataResource.sharedPref {

    private SharedPreferences sharedPreferences;
    private final String prefName = "DEMOARCH";

    public SharedPreferencesRepository(Context context) {
        sharedPreferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
    }


    @Override
    public void putStringShatrdPref(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    @Override
    public String getStringShatrdPref(String key, String defvalue) {
        return sharedPreferences.getString(key, defvalue);
    }

    @Override
    public void putIntShatrdPref(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    @Override
    public int getIntShatrdPref(String key, int defvalue) {
        return sharedPreferences.getInt(key, defvalue);
    }

}
