package com.example.rahi.demoarch.listmodule;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rahi.demoarch.databinding.ActivityListBinding;

import com.example.rahi.demoarch.AllRepositoryProvider;
import com.example.rahi.demoarch.CommonUtils;
import com.example.rahi.demoarch.ItemclickListener;
import com.example.rahi.demoarch.R;
import com.example.rahi.demoarch.data.remote.IDataResource;

import com.example.rahi.demoarch.model.Posts;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class ActivityList extends AppCompatActivity implements IListActivityContract.View, ItemclickListener {

    ActivityListBinding activityListBinding;
    IListActivityContract.Presenter presenter;
    ObservableArrayList<Posts> responsePojoObservableArrayList = new ObservableArrayList<>();
    ListAdapter listAdapter;
    LinearLayoutManager linearLayoutManager;
    int firstPosition = 0;
    private PostsViewModel viewModel;

    //ObservableArrayList&lt;Pos&gt;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_list);
        activityListBinding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        presenter = new ListPresenter(AllRepositoryProvider.getRemoteDataRepo(), this, AllRepositoryProvider.getSharedPrefRepo(this), AllRepositoryProvider.getFileHandlerRepo(), AllRepositoryProvider.getRoomManagerRepo());

        setAdapter();
        presenter.onCreate();

        //Log.v("listResponsePojos","listResponsePojos---List----"+listResponsePojos);

        //Binding Adapter things
        /*viewModel = new PostsViewModel();
        viewModel.setPosts(new ArrayList<Posts>());*/

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activityListBinding.recList.setOnScrollChangeListener(new View.OnScrollChangeListener() {
                @Override
                public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    firstPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    // Toast.makeText(ActivityList.this, "findFirstVisibleItemPosition-----"+firstPosition, Toast.LENGTH_SHORT).show();
                    Log.v("ListItem", "findFirstVisibleItemPosition-----" + firstPosition);
                    activityListBinding.fab.setVisibility(View.VISIBLE);
                    if (firstPosition == 0)
                        activityListBinding.fab.setVisibility(View.GONE);

                }
            });
        }


        activityListBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //linearLayoutManager.scrollToPositionWithOffset(0, 0);
                activityListBinding.recList.smoothScrollToPosition(0);
            }
        });

        activityListBinding.swiperefresh.setColorSchemeResources(R.color.colorAccent);

        activityListBinding.swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                responsePojoObservableArrayList.clear();
//                listAdapter.notifyDataSetChanged();
                presenter.onCreate();
                activityListBinding.swiperefresh.setRefreshing(false);
            }
        });

    }

//    @BindingAdapter({"bind:list", "bind:context"})
//    public static void generateList(RecyclerView recyclerView, ObservableArrayList<Posts> listResponsePojos, ActivityList context) {
//        recyclerView.addItemDecoration(new DividerItemDecorator(ContextCompat.getDrawable(context, R.drawable.line_rect)));
//        // recyclerView.addItemDecoration(new DividerItemDecorator(context.getResources().getDrawable(R.drawable.line_rect)));
//        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
//        recyclerView.setAdapter(new ListAdapter(listResponsePojos, context, (ItemclickListener) context));
//    }

    @Override
    public void onItemclick(final View view, final int position, int id) {
        //Toast.makeText(this, "Position--" + position + "---ID--" + id, Toast.LENGTH_SHORT).show();

        switch (view.getId()) {

            case R.id.delete:
                responsePojoObservableArrayList.remove(position);
                if (listAdapter != null) {

                    listAdapter.notifyItemRemoved(position);
                    listAdapter.notifyItemRangeChanged(position, responsePojoObservableArrayList.size());
                }

                if (listAdapter.gethearthashmap().containsKey(position))
                    listAdapter.gethearthashmap().remove(position);

                presenter.deleteProduct(id);


                break;

            case R.id.heart_img:
                //Toast.makeText(this, "heart_img Case--- "+position, Toast.LENGTH_SHORT).show();
                Toast.makeText(this, "heart_img Case--- " + view.getId(), Toast.LENGTH_SHORT).show();

                if (responsePojoObservableArrayList.get(position).getBody().contains("HEART CLICLKED") || listAdapter.gethearthashmap().containsKey(position)) {
                    ((ImageView) view).setImageResource(R.drawable.heart);
                    listAdapter.gethearthashmap().remove(position);
                    presenter.updateRow(id);
                    break;
                }

                presenter.heartService(new IDataResource.RemoteCallback<List<Posts>>() {
                    @Override
                    public void onSuccess(List<Posts> response) {

                        if (response != null)
                            ((ImageView) view).setImageResource(R.drawable.heart_red);
                        listAdapter.gethearthashmap().put(position, true);
                        //notifyList();
                        presenter.getAllPosts();
                    }

                    @Override
                    public void onFailure(String message) {
                        Toast.makeText(getApplicationContext(), "Heart IMage error " + message, Toast.LENGTH_SHORT).show();
                    }
                }, responsePojoObservableArrayList.get(position).getId());
               //notifyList();
                break;

            default:
                presenter.saveId(responsePojoObservableArrayList.get(position).getId());
                Bitmap row_bitmap;
                ByteArrayOutputStream byteArrayOutputStream;

                ((ConstraintLayout) view).setDrawingCacheEnabled(true);
                row_bitmap = view.getDrawingCache();
                byteArrayOutputStream = new ByteArrayOutputStream();
                row_bitmap.compress(Bitmap.CompressFormat.PNG, 80, byteArrayOutputStream);
                view.setDrawingCacheEnabled(false);
                presenter.setBitmap(byteArrayOutputStream, Environment.getExternalStorageDirectory().getAbsolutePath() + "/DemoArchAppData", responsePojoObservableArrayList.get(position).getId());
                Toast.makeText(this, "ID stored to shared preferences is----" + presenter.getId(), Toast.LENGTH_SHORT).show();
                break;
        }


    }


    public void setAdapter() {
        responsePojoObservableArrayList.clear();
        listAdapter = new ListAdapter(responsePojoObservableArrayList, this, this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //responsePojoObservableArrayList.addAll(list);
        activityListBinding.recList.addItemDecoration(new DividerItemDecorator(ContextCompat.getDrawable(this, R.drawable.line_rect)));
        activityListBinding.recList.setLayoutManager(linearLayoutManager);
        activityListBinding.recList.setAdapter(listAdapter);

    }


    @Override
    public void setListAdapter(List<Posts> list) {
        Log.v("onSuccess", "response-----list------" + list);
//        responsePojoObservableArrayList.clear();
//        listAdapter = new ListAdapter(responsePojoObservableArrayList, this, this);
//        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        responsePojoObservableArrayList.addAll(list);
//        activityListBinding.recList.addItemDecoration(new DividerItemDecorator(ContextCompat.getDrawable(this, R.drawable.line_rect)));
//        activityListBinding.recList.setLayoutManager(linearLayoutManager);
//        activityListBinding.recList.setAdapter(listAdapter);

        responsePojoObservableArrayList.addAll(list);
        notifyList();

    }


    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean isNetworkOn() {

        if (CommonUtils.isNetworkAvailable(this))
            return true;

        return false;
    }

    @Override
    public void notifyList() {


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                listAdapter.notifyDataSetChanged();
            }
        });

    }


}
