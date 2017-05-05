package com.mobintum.spotifymusic;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Ricardo on 04/05/17.
 * www.mobintum.com
 */

public class RVSpotifyAdapter extends RecyclerView.Adapter<RVSpotifyAdapter.ViewHolder>{

    private List<Track> objects;
    private Context context;


    public RVSpotifyAdapter(List<Track> objects) {
        this.objects = objects;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ViewHolder holder = new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_track,parent,false));
        this.context = parent.getContext();
        holder.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Track track = objects.get(holder.getAdapterPosition());
                Intent intent = new Intent(MediaStore.INTENT_ACTION_MEDIA_PLAY_FROM_SEARCH);
                intent.setData(Uri.parse(track.getUriSpotify()));
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Track track = objects.get(position);
        holder.txtAlbum.setText(track.getAlbum());
        holder.txtArtist.setText(track.getArtist());
        holder.txtTrack.setText(track.getName());
        Picasso.with(context).load(track.getPathImage()).into(holder.imgAlbum);
    }

    @Override
    public int getItemCount() {
        return objects.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        TextView txtTrack;
        TextView txtArtist;
        TextView txtAlbum;
        ImageButton btnPlay;
        ImageView imgAlbum;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTrack = (TextView) itemView.findViewById(R.id.txtTrack);
            txtArtist = (TextView) itemView.findViewById(R.id.txtArtist);
            txtAlbum = (TextView) itemView.findViewById(R.id.txtAlbum);
            btnPlay = (ImageButton) itemView.findViewById(R.id.btnPlay);
            imgAlbum = (ImageView) itemView.findViewById(R.id.imgAlbum);
        }

    }
}
