package com.mobintum.myplaces.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.mobintum.myplaces.R;
import com.mobintum.myplaces.models.Photo;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ricardo on 05/05/17.
 * www.mobintum.com
 */

public class PhotosRVAdapter extends RecyclerView.Adapter<PhotosRVAdapter.ViewHolder> {
    private Context context;
    private List<Photo> objects;


    public PhotosRVAdapter(List<Photo> objects) {
        this.objects = objects;
    }

    @Override
    public PhotosRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_photo,parent,false));
        this.context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(PhotosRVAdapter.ViewHolder holder, int position) {
        final Photo photo = objects.get(position);
        Picasso.with(context).load(photo.getPrefix()+"200x200"+photo.getSuffix()).into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        public ViewHolder(View itemView) {
            super(itemView);
            imgPhoto = (ImageView) itemView.findViewById(R.id.imgPhoto);
        }
    }
}
