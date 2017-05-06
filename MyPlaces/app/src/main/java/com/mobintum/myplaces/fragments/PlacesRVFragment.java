package com.mobintum.myplaces.fragments;

import android.app.SearchManager;
import android.content.Context;
import android.icu.text.DisplayContext;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mobintum.myplaces.R;
import com.mobintum.myplaces.adapters.PlacesRVAdapter;
import com.mobintum.myplaces.models.Venue;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class PlacesRVFragment extends Fragment implements Response.ErrorListener, Response.Listener<String> {


    private RecyclerView rvPlaces;
    private PlacesRVAdapter adapter;
    private List<Venue> venues = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search_places,menu);
        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchVenues(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_places_rv, container, false);
        rvPlaces = (RecyclerView) view.findViewById(R.id.rvPlaces);
        rvPlaces.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
        adapter = new PlacesRVAdapter(venues, getActivity().getSupportFragmentManager());
        rvPlaces.setAdapter(adapter);
        return view;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void searchVenues(String query){

        String buildUrl= URL +query;
        StringRequest stringRequest = new StringRequest(Request.Method.GET,buildUrl,this,this);
        Volley.newRequestQueue(getContext()).add(stringRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {
        Log.e("DEBUG", response);
        venues.clear();
        try {
            JSONObject jsonObject = new JSONObject(response);
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            Type listType = new TypeToken<List<Venue>>(){}.getType();
            venues = gson.fromJson(jsonObject.getJSONObject("response").getJSONArray("venues").toString(),listType);
            adapter = new PlacesRVAdapter(venues,getActivity().getSupportFragmentManager());
            rvPlaces.setAdapter(adapter);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
