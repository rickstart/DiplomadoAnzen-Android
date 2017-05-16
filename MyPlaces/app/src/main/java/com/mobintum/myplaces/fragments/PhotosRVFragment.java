package com.mobintum.myplaces.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mobintum.myplaces.R;
import com.mobintum.myplaces.adapters.PhotosRVAdapter;
import com.mobintum.myplaces.models.Photo;
import com.mobintum.myplaces.models.Venue;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class PhotosRVFragment extends Fragment implements Response.ErrorListener, Response.Listener<String> {

    private static final String URL = "https://api.foursquare.com/v2/venues/";
    private static final String CREDENTIALS = "?oauth_token=FYRMHHXQEGFCHET0WLR12SEXUGRMX1R00TXMICDHXRBUUVBM&v=20170515";
    private static final String ARG_PARAM_VENUE_ID = "paramVenueId";
    private RecyclerView rvPhotos;
    private String venueId;
    private List<Photo> photos = new ArrayList<>();
    private PhotosRVAdapter adapter;

    public PhotosRVFragment() {
        // Required empty public constructor
    }

    public static PhotosRVFragment newInstance(String venueId) {

        Bundle args = new Bundle();
        args.putString(ARG_PARAM_VENUE_ID,venueId);
        PhotosRVFragment fragment = new PhotosRVFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            this.venueId = getArguments().getString(ARG_PARAM_VENUE_ID);
        }
    }

    private void getPhotos(){
        if (venueId!=null) {
            String buildUrl = URL + venueId + "/photos"+CREDENTIALS;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, buildUrl, this, this);
            Volley.newRequestQueue(getContext()).add(stringRequest);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photos_rv, container, false);
        rvPhotos = (RecyclerView) view.findViewById(R.id.rvPhotos);
        adapter = new PhotosRVAdapter(photos);
        rvPhotos.setLayoutManager(new GridLayoutManager(getContext(),3));
        rvPhotos.setAdapter(adapter);
        getPhotos();
        return view;
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {
        Log.e("DEBUG", response);

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(response);
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            Type listType = new TypeToken<List<Photo>>(){}.getType();
            photos = gson.fromJson(jsonObject.getJSONObject("response").getJSONObject("photos").getJSONArray("items").toString(),listType);
            adapter = new PhotosRVAdapter(photos);
            rvPhotos.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
