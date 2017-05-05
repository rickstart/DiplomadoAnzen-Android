package com.mobintum.spotifymusic;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class SpotifyRVFragment extends Fragment implements Response.ErrorListener, Response.Listener<String> {

    private RecyclerView rvSpotify;
    private RVSpotifyAdapter adapter;
    private static final String URL = "https://api.spotify.com/v1/search";
    private List<Track> tracks = new ArrayList<>();

    public SpotifyRVFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_spotify_rv, container, false);
        rvSpotify = (RecyclerView) view.findViewById(R.id.rvSpotify);
        adapter = new RVSpotifyAdapter(tracks);
        LinearLayoutManager llm = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvSpotify.setLayoutManager(llm);
        rvSpotify.setAdapter(adapter);
        requestSearch("Imagine");
        return view;
    }

    private void requestSearch(String query){
        String buildUrl = URL+"?type=track&q="+query;
        StringRequest stringRequest = new StringRequest(Request.Method.GET,buildUrl,this,this);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private List<Track> loadData(){
        List<Track> objects = new ArrayList<>();
        objects.add(new Track("La Incondicional","Luis Miguel","Busca Una Mujer","https://i.scdn.co/image/f0d51eb580625e4ae4a494a529506ee13ff97455","spotify:track:6F9yAYUaNbUhdlQyt5uZ3b"));
        return objects;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e("DEBUG ERROR", error.toString());
    }

    @Override
    public void onResponse(String response) {
        Log.e("DEBUG", response);
        tracks.clear();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONObject jsonTracks = jsonObject.getJSONObject("tracks");
            JSONArray jsonArray = jsonTracks.getJSONArray("items");
            for (int i=0; i<jsonArray.length();i++){
                Track track = new Track();
                JSONObject item = jsonArray.getJSONObject(i);
                track.setName(item.getString("name"));
                track.setUriSpotify(item.getString("uri"));
                JSONObject album = item.getJSONObject("album");
                track.setAlbum(album.getString("name"));
                JSONArray artists = album.getJSONArray("artists");
                track.setArtist(artists.getJSONObject(0).getString("name"));
                track.setPathImage(album.getJSONArray("images").getJSONObject(0).getString("url"));
                tracks.add(track);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adapter = new RVSpotifyAdapter(tracks);
        rvSpotify.setAdapter(adapter);
    }
}
