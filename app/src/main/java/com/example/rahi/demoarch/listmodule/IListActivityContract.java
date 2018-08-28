package com.example.rahi.demoarch.listmodule;

import com.example.rahi.demoarch.data.remote.IDataResource;
import com.example.rahi.demoarch.model.Posts;

import java.io.ByteArrayOutputStream;
import java.util.List;

public interface IListActivityContract {


    interface View {
        void setListAdapter(List<Posts> list);
        void showToast(String message);
        boolean isNetworkOn();
        void notifyList();
    }

    interface Presenter {
        void onCreate();
        void heartService(IDataResource.RemoteCallback<List<Posts>> remoteCallback,int id);
        void saveId(int id);
        int  getId();
        void setBitmap(ByteArrayOutputStream bot,String filepath,int position);
        void deleteProduct(int id);
        void updateRow(int id);
        void getAllPosts();
    }
}
