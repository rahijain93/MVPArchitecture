package com.example.rahi.demoarch.data.remote;

import com.example.rahi.demoarch.model.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteDataSources implements IDataResource.Remote {

    private IApiServices apiService = RetrofitConf.getClient().create(IApiServices.class);

    @Override
    public void getListUser(final IDataResource.RemoteCallback<List<Posts>> callback) {

        apiService.getList().enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                callback.onFailure(t.toString());
            }
        });

    }
}
