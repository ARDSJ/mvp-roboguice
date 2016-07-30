package com.example.asouza.myapplication.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asouza.myapplication.R;
import com.example.asouza.myapplication.model.entity.Item;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asouza on 29/07/16.
 */
public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder> {

    @Inject
    Context context;

    @Inject
    LayoutInflater layoutInflater;

    List<Item> items = new ArrayList<>();

    @Override
    public SearchResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.item_search_result, parent);

        return new SearchResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SearchResultViewHolder holder, int position) {
        return;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class SearchResultViewHolder extends RecyclerView.ViewHolder{

        public SearchResultViewHolder(View itemView) {
            super(itemView);
        }
    }

    public List<Item> getItems() {
        return items;
    }

    public void addItems(@NonNull List<Item> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}
