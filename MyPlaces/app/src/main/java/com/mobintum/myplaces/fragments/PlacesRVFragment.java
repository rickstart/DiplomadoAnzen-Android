package com.mobintum.myplaces.fragments;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.icu.text.DisplayContext;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PlacesRVFragment extends Fragment implements Response.ErrorListener, Response.Listener<String>, OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private static final String URL = "https://api.foursquare.com/v2/venues/search?client_id=KFBD1D243LNTYSNNUWJ1X3ZD4V5JXJ04IB0OEDV11JR1OROX&client_secret=DMTPETRTOC3BRZEK2VIWZV3A1MPIHV4XO2IHMNH4Q4MM5YNQ&v=20130815&";
    private RecyclerView rvPlaces;
    private PlacesRVAdapter adapter;
    private List<Venue> venues = new ArrayList<>();
    private GoogleMap gMap;
    private boolean isShowMap = true;
    private SupportMapFragment mapFragment;
    private Location myLocation = null;
    private LocationManager locationManager;
    private ProgressDialog pDialog;
    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            myLocation = location;
            Log.e("LOCATION", location.toString());
            if (gMap!=null)
                gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myLocation.getLatitude(),myLocation.getLongitude()),13));
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10.0f, locationListener);
        }else {
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},100);
        }
        this.pDialog = new ProgressDialog(getContext());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_search_places, menu);
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

        final MenuItem showMap = menu.findItem(R.id.menuMap);
        showMap.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if (isShowMap) {
                    showMap.setTitle(getString(R.string.show_map));
                    isShowMap = false;
                    mapFragment.getView().setVisibility(View.GONE);
                    rvPlaces.setVisibility(View.VISIBLE);
                } else {
                    showMap.setTitle(getString(R.string.show_list));
                    isShowMap = true;
                    mapFragment.getView().setVisibility(View.VISIBLE);
                    rvPlaces.setVisibility(View.GONE);
                    if (venues.size() > 0 && gMap != null)
                        loadLocations(venues);
                }
                return false;
            }
        });


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_places_rv, container, false);
        rvPlaces = (RecyclerView) view.findViewById(R.id.rvPlaces);
        rvPlaces.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        adapter = new PlacesRVAdapter(venues, getActivity().getSupportFragmentManager());
        rvPlaces.setAdapter(adapter);
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isShowMap)
            rvPlaces.setVisibility(View.GONE);
        else
            mapFragment.getView().setVisibility(View.INVISIBLE);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void searchVenues(String query) {

        if (myLocation!=null) {

            String buildUrl = URL +"ll="+myLocation.getLatitude()+","+myLocation.getLongitude()+"&query="+ query;
            StringRequest stringRequest = new StringRequest(Request.Method.GET, buildUrl, this, this);
            Volley.newRequestQueue(getContext()).add(stringRequest);
            pDialog.show();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        pDialog.dismiss();
    }

    @Override
    public void onResponse(String response) {
        Log.e("DEBUG", response);
        pDialog.dismiss();
        venues.clear();
        try {
            JSONObject jsonObject = new JSONObject(response);
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            Type listType = new TypeToken<List<Venue>>() {
            }.getType();
            venues = gson.fromJson(jsonObject.getJSONObject("response").getJSONArray("venues").toString(), listType);
            loadLocations(venues);
            adapter = new PlacesRVAdapter(venues, getActivity().getSupportFragmentManager());
            rvPlaces.setAdapter(adapter);
            if (isShowMap)
                rvPlaces.setVisibility(View.GONE);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void loadLocations(List<Venue> venues) {
        if (gMap != null) {
            gMap.clear();
            LatLngBounds.Builder builder = LatLngBounds.builder();
            for (Venue venue : venues) {
                LatLng latLng = new LatLng(venue.getLocation().getLat(), venue.getLocation().getLng());
                gMap.addMarker(new MarkerOptions().position(latLng).title(venue.getName())).setTag(venue);
                builder.include(latLng);
            }
            LatLngBounds bounds = builder.build();
            gMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, 16));
        }

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        gMap = googleMap;
        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            gMap.setMyLocationEnabled(true);
        }else {
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION},100);
        }

        gMap.setOnInfoWindowClickListener(this);

        if (myLocation!=null){
            gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(myLocation.getLatitude(),myLocation.getLongitude()),17));
        }
        Log.e("DEBUG", "Venues "+venues.size());
        if (venues.size()>0 && isShowMap)
            loadLocations(venues);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("DEBUG", "OnDestroy");
        if (mapFragment!=null){
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.remove(mapFragment);
            ft.commit();
        }
    }



    @Override
    public void onInfoWindowClick(Marker marker) {
        Venue venue = (Venue) marker.getTag();
        String venueId = venue.getVenueId();
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.content, PhotosRVFragment.newInstance(venueId)).addToBackStack(null).commit();
    }
}
