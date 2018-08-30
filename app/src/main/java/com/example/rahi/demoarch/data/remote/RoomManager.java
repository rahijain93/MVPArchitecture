package com.example.rahi.demoarch.data.remote;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;
import java.util.logging.Handler;

import com.example.rahi.demoarch.AppController;
import com.example.rahi.demoarch.model.Posts;

public class RoomManager implements IDataResource.roomManager {
    List<Posts> postsList;
    Posts posts_room;

    @SuppressLint("StaticFieldLeak")
    @Override
    public void getAllProducts(final RoomFetchListener<List<Posts>> listRoomFetchListener) {

        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                postsList = AppController.getRoomDatabase().getPostsDao().getListOfPosts();
                listRoomFetchListener.fetchSuccessfull(postsList);
               // Log.v("onSuccess", "response--------RoomManager--getAllProducts------" + postsList);
                return null;
            }
        }.execute();

    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void deleteProduct(final int id) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                AppController.getRoomDatabase().getPostsDao().deletePosts(id);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void insertPostsList(final List<Posts> posts) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                AppController.getRoomDatabase().getPostsDao().insertListOfPosts(posts);
                return null;
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public Posts getRowObject(int id, RoomFetchListener<Posts> postsRoomFetchListener) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                posts_room = AppController.getRoomDatabase().getPostsDao().getRowIdObject(id);
                postsRoomFetchListener.fetchSuccessfull(posts_room);
                return null;
            }
        }.execute();
        return null;
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void updatePost(int id, String body) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {

                if (!body.equals("")) {
                    AppController.getRoomDatabase().getPostsDao().updateTour(id, body);
                    //Log.v("updateTour", "updateTour----" + body.contains("HEART"));
                    return null;
                } else {
                    posts_room = AppController.getRoomDatabase().getPostsDao().getRowIdObject(id);
                    posts_room.setBody(posts_room.getBody().replace("HEART CLICLKED", ""));
                    AppController.getRoomDatabase().getPostsDao().updateTour(id, posts_room.getBody());
                    return null;
                }
            }
        }.execute();
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public void singleRowPost(Posts posts) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                AppController.getRoomDatabase().getPostsDao().updateSinglePost(posts);
                return null;
            }
        }.execute();
    }
}
