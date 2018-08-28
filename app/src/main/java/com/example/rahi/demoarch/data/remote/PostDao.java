package com.example.rahi.demoarch.data.remote;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.rahi.demoarch.model.Posts;

import java.util.List;

@Dao
public interface PostDao {

    @Query("select * from Posts")
    List<Posts> getListOfPosts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertListOfPosts(List<Posts> posts);

    @Query("delete from Posts where id = :id")
    void deletePosts(int id);

    @Query("Select * from Posts where id= :id")
    Posts getRowIdObject(int id);

    @Query("UPDATE Posts SET body = :body  WHERE id = :id")
    void updateTour(int id, String body);

}
