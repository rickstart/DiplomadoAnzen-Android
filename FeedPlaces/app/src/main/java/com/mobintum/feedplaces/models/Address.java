package com.mobintum.feedplaces.models;

import android.content.Context;
import android.database.Cursor;

import com.mobintum.feedplaces.database.DatabaseAdapter;

/**
 * Created by Ricardo on 19/05/17.
 * www.mobintum.com
 */

public class Address {

    public static final String TABLE_NAME = "Address";
    public static final String ADDRESS_ID = "addressId";
    public static final String COUNTRY = "country";
    public static final String STATE = "state";
    public static final String LAT = "lat";
    public static final String LNG = "lng";
    public static final String ADDRESS_LINE = "addressLine";

    private Integer addressId;
    private String country;
    private String state;
    private String addressLine;
    private Double lat;
    private Double lng;

    public Address(Integer addressId, String country, String state, String addressLine, Double lat, Double lng) {
        this.addressId = addressId;
        this.country = country;
        this.state = state;
        this.addressLine = addressLine;
        this.lat = lat;
        this.lng = lng;
    }

    public Integer getAddressId() {
        return addressId;
    }

    public void setAddressId(Integer addressId) {
        this.addressId = addressId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public static Address getAddressById(Context context,int addressId){
        Address address = null;
        Cursor cursor = DatabaseAdapter.getDB(context).query(TABLE_NAME,null,ADDRESS_ID+"="+addressId,null,null,null,null);
        if (cursor!=null){
            if (cursor.getCount()>0){
                cursor.moveToFirst();
                String country = cursor.getString(cursor.getColumnIndexOrThrow(COUNTRY));
                String state = cursor.getString(cursor.getColumnIndexOrThrow(STATE));
                String addressLine = cursor.getString(cursor.getColumnIndexOrThrow(ADDRESS_LINE));
                double lat = cursor.getDouble(cursor.getColumnIndexOrThrow(LAT));
                double lng = cursor.getDouble(cursor.getColumnIndexOrThrow(LNG));
                address = new Address(addressId,country,state,addressLine,lat,lng);
            }
            cursor.close();
        }
        return address;

    }
}
