package com.mobintum.myfirstrv;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TownsRVFragment extends Fragment {

    private RecyclerView rvTowns;
    private TownRVAdapter adapter;
    private List<Town> towns = new ArrayList<>();

    public TownsRVFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_towns_rv, container, false);
        rvTowns = (RecyclerView) view.findViewById(R.id.rvTowns);
        loadData();
        adapter = new TownRVAdapter(towns);
        LinearLayoutManager llm = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        rvTowns.setLayoutManager(llm);
        rvTowns.setAdapter(adapter);
        return view;
    }

    void loadData(){
        towns.add(new Town("Bacalar","Quintana Roo",4.8f,"http://www.pueblosmexico.com.mx/IMG/arton171.jpg"));
        towns.add(new Town("Tulum","Quintana Roo",4.5f,"http://www.pueblosmexico.com.mx/IMG/arton25158.jpg"));
        towns.add(new Town("Mazunte","Oaxaca",4.0f,"http://www.pueblosmexico.com.mx/IMG/arton25150.jpg"));
        towns.add(new Town("Valladolid","Yucatan",4.3f,"http://www.pueblosmexico.com.mx/IMG/arton23488.jpg"));
        towns.add(new Town("Sayulita","Nayarit",4.6f,"http://www.pueblosmexico.com.mx/IMG/arton25437.jpg"));
    }

}
