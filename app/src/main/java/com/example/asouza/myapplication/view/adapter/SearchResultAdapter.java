package com.example.asouza.myapplication.view.adapter;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.asouza.myapplication.R;
import com.example.asouza.myapplication.model.entity.Item;
import com.example.asouza.myapplication.view.holder.SearchResultViewHolder;
import com.google.inject.Inject;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import roboguice.RoboGuice;
import roboguice.inject.ContextSingleton;
import roboguice.inject.InjectView;

/**
 * Created by asouza on 29/07/16.
 */
@ContextSingleton
public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultViewHolder> {

    @Inject
    LayoutInflater layoutInflater;

    @Inject
    Picasso picasso;

    private static final int VIEW_TYPE_LOADING = 102;

    private static final int VIEW_TYPE_ITEM = 914;

    List<Object> dataSource = new ArrayList<>();

    @Override
    public SearchResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (viewType == VIEW_TYPE_ITEM) {
            view = layoutInflater.inflate(R.layout.item_search_result, parent, false);
        } else {
            view = layoutInflater.inflate(R.layout.item_loading, parent, false);
        }

        return new SearchResultViewHolder(view);

    }

    @Override
    public void onBindViewHolder(SearchResultViewHolder holder, int position) {

        Object dataItem = dataSource.get(position);

        if (dataItem.getClass().equals(Item.class)) {
            Item itemVolume = (Item) dataItem;
            String thumbnail = itemVolume.getVolumeInfo().getImageLinks().getThumbnail();
            picasso
                    .load(thumbnail)
                    .into(holder.imgVolumeCover);
        }

    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    @Override
    public int getItemViewType(int position) {

        super.getItemViewType(position);
        Object o = this.dataSource.get(position);

        if (o.getClass().equals(Item.class)) {
            return VIEW_TYPE_ITEM;
        } else {
            return VIEW_TYPE_LOADING;
        }

    }

    public void addItems(List<Item> i) {
        this.dataSource.addAll(i);
        notifyDataSetChanged();
    }

    public void setItems(List<Item> i) {
        this.dataSource.clear();
        this.dataSource.addAll(i);
        notifyDataSetChanged();
    }

    public void showLoading() {
        hideLoading();
        this.dataSource.add(new Object());
        notifyDataSetChanged();
    }

    public void hideLoading() {

        for (Object item : this.dataSource) {
            if (!item.getClass().equals(Item.class)) {
                this.dataSource.remove(item);
                notifyDataSetChanged();
            }
        }

    }

}