package com.mobintum.feedplaces.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mobintum.feedplaces.R;
import com.mobintum.feedplaces.models.Place;

import java.io.Serializable;


public class PlaceDetailFragment extends Fragment implements OnMapReadyCallback {

    private static final String ARG_PARAM_PLACE = "paramPlace";
    private GoogleMap gMap;
    private Place place;
    private TextView txtPlaceName, txtDescription;

    public PlaceDetailFragment() {
        // Required empty public constructor
    }

    public static PlaceDetailFragment newInstance(Place place) {

        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_PLACE,  place);
        PlaceDetailFragment fragment = new PlaceDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments()!=null)
            this.place = (Place) getArguments().getSerializable(ARG_PARAM_PLACE);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_place_detail, container, false);
        SupportMapFragment mapFragment = new SupportMapFragment();
        getChildFragmentManager().beginTransaction().replace(R.id.map,mapFragment).commit();
        mapFragment.getMapAsync(this);
        txtPlaceName = (TextView) view.findViewById(R.id.txtPlaceName);
        txtDescription = (TextView) view.findViewById(R.id.txtDescription);
        txtPlaceName.setText(place.getName());
        txtDescription.setText(place.getDescription());
        return view;
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        LatLng locPlace = new LatLng(place.getAddress().getLat(), place.getAddress().getLng());
        googleMap.addMarker(new MarkerOptions().position(locPlace)
                .title(place.getName()));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(locPlace));

    }
}
