package com.mobintum.myplaces.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobintum.myplaces.R;
import com.mobintum.myplaces.fragments.PhotosRVFragment;
import com.mobintum.myplaces.models.Venue;

import java.util.List;

/**
 * Created by Ricardo on 04/05/17.
 * www.mobintum.com
 */

public class PlacesRVAdapter extends RecyclerView.Adapter<PlacesRVAdapter.ViewHolder>{

    private List<Venue> objects;
    private Context context;
    private FragmentManager fm;


    public PlacesRVAdapter(List<Venue> objects, FragmentManager fm) {
        this.objects = objects;
        this.fm = fm;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_place,parent,false));
        this.context = parent.getContext();
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Venue venue = objects.get(position);
        holder.txtPlaceName.setText(venue.getName());
        holder.txtAddress.setText(venue.getLocation().getAddress());
        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fm.beginTransaction().replace(R.id.content, PhotosRVFragment.newInstance(venue.getVenueId())).addToBackStack(null).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView txtPlaceName;
        TextView txtAddress;
        ImageButton btnDetail;
        ImageView imgPlace;

        public ViewHolder(View itemView) {
            super(itemView);
            txtPlaceName = (TextView) itemView.findViewById(R.id.txtPlaceName);
            txtAddress = (TextView) itemView.findViewById(R.id.txtAddress);
            btnDetail = (ImageButton) itemView.findViewById(R.id.btnDetail);
            imgPlace = (ImageView) itemView.findViewById(R.id.imgPlace);
        }

    }
}
