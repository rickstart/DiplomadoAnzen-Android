package com.mobintum.feedplaces.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.mobintum.feedplaces.database.DatabaseAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricardo on 19/05/17.
 * www.mobintum.com
 */

public class Place implements Serializable {

    public static final String TABLE_NAME = "Place";
    public static final String PLACE_ID = "placeId";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String FK_ADDRESS_ID = "fk_addressId";
    public static final String IS_FAVORITE = "isFavorite";

    private Integer placeId;
    private String name;
    private String description;
    private Integer fk_addressId;
    private Integer isFavorite;
    private Address  address;
    private List<Picture> pictures;

    public Place(Integer placeId, String name, String description, Integer fk_addressId, Integer isFavorite, Address address, List<Picture> pictures) {
        this.placeId = placeId;
        this.name = name;
        this.description = description;
        this.fk_addressId = fk_addressId;
        this.isFavorite = isFavorite;
        this.address = address;
        this.pictures = pictures;
    }

    public Integer getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Integer placeId) {
        this.placeId = placeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getFk_addressId() {
        return fk_addressId;
    }

    public void setFk_addressId(Integer fk_addressId) {
        this.fk_addressId = fk_addressId;
    }

    public Integer getIsFavorite() {
        return isFavorite;
    }

    public void setIsFavorite(Integer isFavorite) {
        this.isFavorite = isFavorite;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public static List<Place> getPlaces(Context context,boolean favorite){
        List<Place> places = new ArrayList<>();
        Cursor cursor = (favorite)
                ? DatabaseAdapter.getDB(context).query(TABLE_NAME,null,IS_FAVORITE+"="+1,null,null,null,NAME)
                : DatabaseAdapter.getDB(context).query(TABLE_NAME,null,null,null,null,null,NAME);
        if (cursor!=null){
            for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                int placeId = cursor.getInt(cursor.getColumnIndexOrThrow(PLACE_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(NAME));
                String description = cursor.getString(cursor.getColumnIndexOrThrow(DESCRIPTION));
                int fk_addressId = cursor.getInt(cursor.getColumnIndexOrThrow(FK_ADDRESS_ID));
                int isFavorite = cursor.getInt(cursor.getColumnIndexOrThrow(IS_FAVORITE));
                Address address = Address.getAddressById(context,fk_addressId);
                List<Picture> pictures = Picture.getPictureByPlaceId(context,placeId);
                places.add(new Place(placeId,name,description,fk_addressId,isFavorite,address,pictures));
            }
            cursor.close();
        }
        return places;
    }

    public static long updatePlaceById(Context context,Place place){
        ContentValues cv = new ContentValues();
        int fav = (place.getIsFavorite()==1)?0:1;
        cv.put(IS_FAVORITE,fav);
        return DatabaseAdapter.getDB(context).update(TABLE_NAME,cv,PLACE_ID+"="+place.getPlaceId(),null);
    }
}
