package com.mobintum.spotifymusic;

/**
 * Created by Ricardo on 04/05/17.
 * www.mobintum.com
 */

public class Track {

    private String name;
    private String artist;
    private String album;
    private String pathImage;
    private String uriSpotify;

    public Track(String name, String artist, String album, String pathImage, String uriSpotify) {
        this.name = name;
        this.artist = artist;
        this.album = album;
        this.pathImage = pathImage;
        this.uriSpotify = uriSpotify;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getPathImage() {
        return pathImage;
    }

    public void setPathImage(String pathImage) {
        this.pathImage = pathImage;
    }

    public String getUriSpotify() {
        return uriSpotify;
    }

    public void setUriSpotify(String uriSpotify) {
        this.uriSpotify = uriSpotify;
    }
}
