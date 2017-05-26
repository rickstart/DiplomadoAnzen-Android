package com.mobintum.feedplaces.models;

import android.content.Context;
import android.database.Cursor;

import com.mobintum.feedplaces.database.DatabaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricardo on 19/05/17.
 * www.mobintum.com
 */

public class Picture {
    public static final String TABLE_NAME = "Picture";
    public static final String PICTURE_ID = "pictureId";
    public static final String PATH = "path";
    public static final String IS_MAIN = "isMain";
    public static final String FK_PLACE_ID = "fk_placeId";

    private Integer pictureId;
    private String path;
    private Integer isMain;
    private Integer fk_placeId;

    public Picture(Integer pictureId, String path, Integer isMain, Integer fk_placeId) {
        this.pictureId = pictureId;
        this.path = path;
        this.isMain = isMain;
        this.fk_placeId = fk_placeId;
    }

    public Integer getPictureId() {
        return pictureId;
    }

    public void setPictureId(Integer pictureId) {
        this.pictureId = pictureId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getIsMain() {
        return isMain;
    }

    public void setIsMain(Integer isMain) {
        this.isMain = isMain;
    }

    public Integer getFk_placeId() {
        return fk_placeId;
    }

    public void setFk_placeId(Integer fk_placeId) {
        this.fk_placeId = fk_placeId;
    }

    public static List<Picture> getPictureByPlaceId(Context context,int placeId){
        List<Picture> pictures = new ArrayList<>();
        Cursor cursor = DatabaseAdapter.getDB(context).query(TABLE_NAME,null,FK_PLACE_ID+"="+placeId,null,null,null,IS_MAIN);
        if (cursor!=null){
            for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()){
                int pictureId = cursor.getInt(cursor.getColumnIndexOrThrow(PICTURE_ID));
                String path = cursor.getString(cursor.getColumnIndexOrThrow(PATH));
                int isMain = cursor.getInt(cursor.getColumnIndexOrThrow(IS_MAIN));
                pictures.add( new Picture(pictureId,path,isMain,placeId));
            }
            cursor.close();
        }
        return pictures;
    }
}
