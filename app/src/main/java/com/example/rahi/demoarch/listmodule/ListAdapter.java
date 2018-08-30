package com.example.rahi.demoarch.listmodule;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableArrayList;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.rahi.demoarch.ItemclickListener;
import com.example.rahi.demoarch.R;
import com.example.rahi.demoarch.databinding.RowUserBinding;
import com.example.rahi.demoarch.model.Posts;

import java.util.HashMap;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.TagViewHolder> {
    Context context;
    private ItemclickListener itemclickListener;
    private RowUserBinding rowUserBinding;
    private LayoutInflater layoutInflater;
    private ObservableArrayList<Posts> responsePojoObservableArrayList = new ObservableArrayList<>();
    private int count = 0;
    private HashMap<Integer, Boolean> heartMap = new HashMap<>();


    public ListAdapter(ObservableArrayList<Posts> responsePojoObservableArrayList, Context context, ItemclickListener itemclickListener) {
        this.context = context;
        this.itemclickListener = itemclickListener;
        this.responsePojoObservableArrayList = responsePojoObservableArrayList;
        this.count = responsePojoObservableArrayList.size();
        Log.v("responsePojoObservab", "responsePojoObservableArrayList-size---" + responsePojoObservableArrayList.size());
        //to make const empty do this
        // this.responsePojoObservableArrayList= new ObservableArrayList<>();
        //and initilaize all things u want here
    }

    @Override
    public TagViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflate the layout file

        rowUserBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.row_user, parent, false);
        TagViewHolder gvh = new TagViewHolder(rowUserBinding);
        return gvh;
    }

    @Override
    public void onBindViewHolder(TagViewHolder holder, final int position) {

        final Posts tagWithFullInfoPojo = responsePojoObservableArrayList.get(position);
        holder.binding.setModel(tagWithFullInfoPojo);
        holder.binding.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemclickListener.onItemclick(v, position, tagWithFullInfoPojo.getId());
                }
        });


        holder.binding.heartImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    itemclickListener.onItemclick(v, position, tagWithFullInfoPojo.getId());
            }
        });


        holder.binding.getRoot().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemclickListener.onItemclick(v, position, tagWithFullInfoPojo.getId());
            }
        });

        if (heartMap.containsKey(position)|| tagWithFullInfoPojo.getBody().contains("HEART CLICLKED")) {
            holder.binding.heartImg.setImageResource(R.drawable.heart_red);
        }
        else
            holder.binding.heartImg.setImageResource(R.drawable.heart);

    }

    @Override
    public int getItemCount() {
        return responsePojoObservableArrayList.size();
    }

    public class TagViewHolder extends RecyclerView.ViewHolder {
        private final RowUserBinding binding;

        public TagViewHolder(RowUserBinding rowUserBinding) {
            super(rowUserBinding.getRoot());
            this.binding = rowUserBinding;
        }
    }


    public HashMap<Integer, Boolean> gethearthashmap() {

        return heartMap;
    }


  //Style to make constructor empty...initiali aal the things we pass in constructor like below..create set methods for them just like below..and only initialize them in constructor



}
