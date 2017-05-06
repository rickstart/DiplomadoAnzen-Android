package com.mobintum.myplaces.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobintum.myplaces.R;


public class PhotosRVFragment extends Fragment {

    private static final String ARG_PARAM_VENUE_ID = "paramVenueId";
    private RecyclerView rvPhotos;

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_photos_rv, container, false);
        rvPhotos = (RecyclerView) view.findViewById(R.id.rvPhotos);
        return view;
    }

}
