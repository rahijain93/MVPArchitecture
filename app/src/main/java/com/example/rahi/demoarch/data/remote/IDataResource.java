package com.example.rahi.demoarch.data.remote;

import com.example.rahi.demoarch.model.Posts;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.List;

public interface IDataResource {


    interface fileManagement {

        void saveImage(ByteArrayOutputStream byteArrayOutputStream, File filePath, int position);


    }

    interface sharedPref {

//        void setLoginid(int id);
//        int getLoginid();

        void putStringShatrdPref(String key, String value);

        String getStringShatrdPref(String key, String defvalue);

        void putIntShatrdPref(String key, int value);

        int getIntShatrdPref(String key, int defvalue);

    }

    interface Remote {
        void getListUser(RemoteCallback<List<Posts>> callback);


    }


    interface RemoteCallback<T> {
        void onSuccess(T response);

        void onFailure(String message);
    }

    interface roomManager {
        void getAllProducts(RoomFetchListener<List<Posts>> roomFetchListener);

        void deleteProduct(int id);

        void insertPostsList(List<Posts> listResponsePojoList);

        Posts getRowObject(int id, RoomFetchListener<Posts> postsRoomFetchListener);

        void updatePost(int id, String body);

        void singleRowPost(Posts posts);

    }


}
