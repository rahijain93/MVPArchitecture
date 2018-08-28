package com.example.rahi.demoarch;

import android.content.Context;

import com.example.rahi.demoarch.data.remote.FileHandler;
import com.example.rahi.demoarch.data.remote.RemoteDataSources;
import com.example.rahi.demoarch.data.remote.RoomManager;
import com.example.rahi.demoarch.data.remote.SharedPreferencesRepository;

public class AllRepositoryProvider {

    private static RemoteDataSources remoteDataSources;
    private static SharedPreferencesRepository sharedPreferencesRepository;
    private static FileHandler fileHandler;
    private static RoomManager roomManager;


    public static RemoteDataSources getRemoteDataRepo() {

        if (remoteDataSources == null) {
            remoteDataSources = new RemoteDataSources();
            return remoteDataSources;
        }
        return remoteDataSources;
    }

    public static SharedPreferencesRepository getSharedPrefRepo(Context context) {

        if (sharedPreferencesRepository == null) {
            sharedPreferencesRepository = new SharedPreferencesRepository(context);
            return sharedPreferencesRepository;
        }
        return sharedPreferencesRepository;
    }

    public static FileHandler getFileHandlerRepo() {

        if (fileHandler == null) {
            fileHandler = new FileHandler();
            return fileHandler;
        }
        return fileHandler;
    }

    public static RoomManager getRoomManagerRepo() {

        if (roomManager == null) {
            roomManager = new RoomManager();
            return roomManager;
        }
        return roomManager;
    }


}
