package com.mobintum.spotifymusic;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.List;


public class SpotifyRVFragment extends Fragment {

    private RecyclerView rvSpotify;
    private RVSpotifyAdapter adapter;

    public SpotifyRVFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spotify_rv, container, false);
        rvSpotify = (RecyclerView) view.findViewById(R.id.rvSpotify);
        adapter = new RVSpotifyAdapter(loadData());
        LinearLayoutManager llm = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvSpotify.setLayoutManager(llm);
        rvSpotify.setAdapter(adapter);
        return view;
    }

    private List<Track> loadData(){
        List<Track> objects = new ArrayList<>();
        objects.add(new Track("La Incondicional","Luis Miguel","Busca Una Mujer","https://i.scdn.co/image/f0d51eb580625e4ae4a494a529506ee13ff97455","spotify:track:6F9yAYUaNbUhdlQyt5uZ3b"));
        return objects;
    }
}
