package com.example.rahi.demoarch.data.remote;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.rahi.demoarch.model.Posts;

@Database(entities = {Posts.class}, version = 1, exportSchema = false)
public abstract class ARoomDatabase extends RoomDatabase {

    public abstract PostDao getPostsDao();

    private static volatile ARoomDatabase roomDatabase;

    public static synchronized ARoomDatabase getRoomDatabase(Context context)
    {
        if(roomDatabase == null)
        {
            roomDatabase = Room.databaseBuilder(context, ARoomDatabase.class, "aroomdb.db").build();
        }
        return roomDatabase;
    }


}
