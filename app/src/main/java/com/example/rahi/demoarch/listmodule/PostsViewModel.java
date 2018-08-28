package com.example.rahi.demoarch.listmodule;

import java.util.List;
import android.arch.lifecycle.ViewModel;
import com.example.rahi.demoarch.model.Posts;
import android.databinding.ObservableArrayList;

public class PostsViewModel extends ViewModel {

    private ObservableArrayList<Posts> posts;

    public PostsViewModel() {
        posts = new ObservableArrayList<>();
    }

    public ObservableArrayList<Posts> getPosts() {
        return posts;
    }

    public void setPosts(List<Posts> posts) {
        this.posts.addAll(posts);
    }
}
