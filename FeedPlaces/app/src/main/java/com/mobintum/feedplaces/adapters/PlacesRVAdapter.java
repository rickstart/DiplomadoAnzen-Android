package com.mobintum.feedplaces.adapters;

import android.content.Context;
import android.graphics.ColorFilter;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobintum.feedplaces.R;
import com.mobintum.feedplaces.models.Place;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ricardo on 19/05/17.
 * www.mobintum.com
 */

public class PlacesRVAdapter extends RecyclerView.Adapter<PlacesRVAdapter.ViewHolder> {

    private List<Place> objects;
    private Context context;
    private Callbacks callbacks;
    private boolean favorite;

    public PlacesRVAdapter(List<Place> objects, @NonNull Callbacks callbacks,boolean favorite) {
        this.objects = objects;
        this.callbacks = callbacks;
        this.favorite = favorite;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_place,parent,false));
        this.context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        final Place place = objects.get(position);
        holder.txtPlaceName.setText(place.getName());
        if(place.getIsFavorite()==1)
            holder.btnFavorite.setColorFilter(context.getResources().getColor(R.color.colorAccent));
        else
            holder.btnFavorite.setColorFilter(context.getResources().getColor(android.R.color.white));


        holder.imgPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callbacks.placeDetail(place);
            }
        });
        holder.btnComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Place.updatePlaceById(context,place);
                int fav = (place.getIsFavorite()==1)?0:1;
                objects.get(position).setIsFavorite(fav);
                if (favorite && fav==0)
                    objects.remove(position);
                notifyDataSetChanged();
            }
        });
        if (place.getPictures().size()>0)
        Picasso.with(context).load(place.getPictures().get(0).getPath()).into(holder.imgPlace);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPlace;
        TextView txtPlaceName;
        ImageButton btnFavorite;
        Button btnComments;

        public ViewHolder(View itemView) {
            super(itemView);
            imgPlace = (ImageView) itemView.findViewById(R.id.imgPlace);
            txtPlaceName = (TextView) itemView.findViewById(R.id.txtPlaceName);
            btnFavorite = (ImageButton) itemView.findViewById(R.id.btnFavorite);
            btnComments = (Button) itemView.findViewById(R.id.btnComments);

        }
    }

    public interface Callbacks{
        void placeDetail(Place place);
    }
}
