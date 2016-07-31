package com.example.asouza.myapplication.view.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.asouza.myapplication.R;

/**
 * Created by asouza on 31/07/16.
 */
public class SearchResultViewHolder extends RecyclerView.ViewHolder{

    public ImageView imgVolumeCover;

    public SearchResultViewHolder(View itemView) {
        super(itemView);
        imgVolumeCover = (ImageView)itemView.findViewById(R.id.img_volume_cover);
    }
}

