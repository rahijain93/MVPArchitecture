package com.example.rahi.demoarch.listmodule;

import android.util.Log;

import com.example.rahi.demoarch.CommonUtils;
import com.example.rahi.demoarch.data.remote.AllSharedPrefKeys;
import com.example.rahi.demoarch.data.remote.FileHandler;
import com.example.rahi.demoarch.data.remote.IDataResource;
import com.example.rahi.demoarch.data.remote.RoomFetchListener;
import com.example.rahi.demoarch.data.remote.RoomManager;
import com.example.rahi.demoarch.data.remote.SharedPreferencesRepository;
import com.example.rahi.demoarch.listmodule.IListActivityContract.Presenter;
import com.example.rahi.demoarch.model.Posts;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

public class ListPresenter implements Presenter {
    IDataResource.Remote remote;
    IListActivityContract.View view;
    IDataResource.sharedPref sharedPref;
    IDataResource.fileManagement fileManagement;
    IDataResource.roomManager roomManager;


    public ListPresenter(IDataResource.Remote remote, IListActivityContract.View view, SharedPreferencesRepository sharedPreferencesRepository, FileHandler fileHandler, RoomManager roomManager) {
        this.remote = remote;
        this.view = view;
        this.sharedPref = sharedPreferencesRepository;
        this.fileManagement = fileHandler;
        this.roomManager = roomManager;
    }

    @Override
    public void onCreate() {

        if (view.isNetworkOn()) {

            remote.getListUser(new IDataResource.RemoteCallback<List<Posts>>() {

                @Override
                public void onSuccess(List<Posts> posts) {

                    view.setListAdapter(posts);
                    roomManager.insertPostsList(posts);

                }

                @Override
                public void onFailure(String message) {
                    view.showToast(message);
                }
            });
        } else
            roomManager.getAllProducts(new RoomFetchListener<List<Posts>>() {
                @Override
                public void fetchSuccessfull(List<Posts> list) {
                    view.setListAdapter(list);
                }
            });


    }


    @Override
    public void heartService(final IDataResource.RemoteCallback<List<Posts>> remoteCallback, int id) {


        remote.getListUser(new IDataResource.RemoteCallback<List<Posts>>() {

            @Override
            public void onSuccess(List<Posts> response) {
                if (response != null) {

                    Log.v("heartService", "heartService---HIIIIII---" + response);
                    remoteCallback.onSuccess(response);

                    roomManager.getRowObject(id, new RoomFetchListener<Posts>() {
                        @Override
                        public void fetchSuccessfull(Posts posts) {
                            posts.setBody(posts.getBody() + " HEART CLICLKED");
                            Log.v("HEART CLICLKED", "HEART CLICLKED-----" + posts.getBody().contains("HEART CLICLKED"));

                            //roomManager.updatePost(id, posts.getBody());
                            roomManager.singleRowPost(posts);
                            view.notifyList();
                        }
                    });


                }
            }

            @Override
            public void onFailure(String message) {
                remoteCallback.onFailure(message);
            }
        });


    }

    @Override
    public void saveId(int id) {
        sharedPref.putIntShatrdPref(AllSharedPrefKeys.loginId, id);
    }

    @Override
    public int getId() {
        return sharedPref.getIntShatrdPref(AllSharedPrefKeys.loginId, 0);
    }

    @Override
    public void setBitmap(ByteArrayOutputStream bot, String filepath, int position) {
        fileManagement.saveImage(bot, new File(filepath), position);
    }

    @Override
    public void deleteProduct(int id) {
        roomManager.deleteProduct(id);
    }

    @Override
    public void updateRow(int id) {
        roomManager.updatePost(id, "");
       // view.notifyList();
    }

    @Override
    public void getAllPosts() {
        roomManager.getAllProducts(new RoomFetchListener<List<Posts>>() {
            @Override
            public void fetchSuccessfull(List<Posts> list) {

                view.setListAdapter(list);
                Log.v("onSuccess", "response-----getAllPosts------" + list);
            }
        });
    }

    private void notifyAdapterList(){
        view.notifyList();
    }

}
