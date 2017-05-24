package com.mobintum.feedplaces.fragments;

import android.os.Bundle;
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

    private RecyclerView rvPlaces;
    private PlacesRVAdapter adapter;
    private List<Place> places = new ArrayList<>();


    public RVPlacesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_rvplaces, container, false);
        loadData();
        rvPlaces = (RecyclerView) view.findViewById(R.id.rvPlaces);
        LinearLayoutManager llm = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvPlaces.setLayoutManager(llm);
        adapter = new PlacesRVAdapter(places,this);
        rvPlaces.setAdapter(adapter);
        return view;
    }

    private void loadData(){
        Address address = new Address("México","Quintana Roo", "Al sur de México",18.548878,-93.763647);
        List<Picture> pictures = new ArrayList<>();
        pictures.add(new Picture("http://www.pueblosmexico.com.mx/IMG/arton171.jpg",1));
        places.add(new Place("Bacalar","",address,pictures));
        places.add(new Place("Bacalar","",address,pictures));
        places.add(new Place("Bacalar","",address,pictures));
        places.add(new Place("Bacalar","",address,pictures));
        places.add(new Place("Bacalar","",address,pictures));
    }

    @Override
    public void placeDetail(Place place) {
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, new PlaceDetailFragment()).commit();
    }
}
