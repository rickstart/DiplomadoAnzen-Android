package com.mobintum.myfirstrv;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;


/**
 * Created by Ricardo on 03/05/17.
 * www.mobintum.com
 */

public class TownRVAdapter extends RecyclerView.Adapter<TownRVAdapter.ViewHolder> {

    private List<Town> objects;
    private Context context;

    public TownRVAdapter(List<Town> objects){
        this.objects = objects;
    }

    @Override
    public TownRVAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_town,parent,false));
        context = parent.getContext();
        return holder;
    }

    @Override
    public void onBindViewHolder(TownRVAdapter.ViewHolder holder, int position) {
        final Town town = objects.get(position);
        holder.txtTownName.setText(town.getName());
        holder.txtState.setText(town.getState());
        holder.ratingTown.setRating(town.getRating());
        Picasso.with(context).load(town.getPathImage()).into(holder.imgTown);
        holder.btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.btnReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgTown;
        ImageButton btnFavorite;
        RatingBar ratingTown;
        TextView txtTownName;
        TextView txtState;
        Button btnReview;
        Button btnShare;

        public ViewHolder(View itemView) {
            super(itemView);
            imgTown = (ImageView) itemView.findViewById(R.id.imgTown);
            btnFavorite = (ImageButton) itemView.findViewById(R.id.btnFavorite);
            ratingTown = (RatingBar) itemView.findViewById(R.id.ratingTown);
            txtTownName = (TextView) itemView.findViewById(R.id.txtTownName);
            txtState = (TextView) itemView.findViewById(R.id.txtState);
            btnReview = (Button) itemView.findViewById(R.id.btnReview);
            btnShare = (Button) itemView.findViewById(R.id.btnShare);
        }
    }
}
