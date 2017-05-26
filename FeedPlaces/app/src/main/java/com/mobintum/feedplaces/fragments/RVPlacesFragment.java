package com.mobintum.feedplaces.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobintum.feedplaces.R;
import com.mobintum.feedplaces.adapters.PlacesRVAdapter;
import com.mobintum.feedplaces.models.Address;
import com.mobintum.feedplaces.models.Picture;
import com.mobintum.feedplaces.models.Place;

import java.util.ArrayList;
import java.util.List;


public class RVPlacesFragment extends Fragment implements PlacesRVAdapter.Callbacks {

    private static final String ARG_PARAM_FAVORITE = "paramFavorite";
    private RecyclerView rvPlaces;
    private PlacesRVAdapter adapter;
    private List<Place> places = new ArrayList<>();
    private boolean favorite = false;


    public RVPlacesFragment() {
        // Required empty public constructor
    }

    public static RVPlacesFragment newInstance(boolean favorite) {
        Bundle args = new Bundle();
        args.putBoolean(ARG_PARAM_FAVORITE,favorite);
        RVPlacesFragment fragment = new RVPlacesFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null){
            favorite = getArguments().getBoolean(ARG_PARAM_FAVORITE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rvplaces, container, false);
        places = Place.getPlaces(getContext(),favorite);
        rvPlaces = (RecyclerView) view.findViewById(R.id.rvPlaces);
        LinearLayoutManager llm = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvPlaces.setLayoutManager(llm);
        adapter = new PlacesRVAdapter(places,this,favorite);
        rvPlaces.setAdapter(adapter);
        return view;
    }


    @Override
    public void placeDetail(Place place) {
        if (getActivity().getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.rightLayout, PlaceDetailFragment.newInstance(place)).addToBackStack(null).commit();
        else
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, PlaceDetailFragment.newInstance(place)).addToBackStack(null).commit();
    }
}
