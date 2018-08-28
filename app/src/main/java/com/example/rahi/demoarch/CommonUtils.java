package com.example.rahi.demoarch;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CommonUtils {



    public static boolean isNetworkAvailable(Context context) {
        boolean value = false;
        try {

            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if (info != null && info.isAvailable()) {
                value = true;
            }
            return value;
        } catch (Exception e) {
            return value;
        }

    }
}
