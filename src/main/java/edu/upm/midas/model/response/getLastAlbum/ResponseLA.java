package edu.upm.midas.model.response.getLastAlbum;

import edu.upm.midas.model.response.Album;
import edu.upm.midas.model.response.ResponseFather;

import java.util.List;

public class ResponseLA extends ResponseFather{

    private Album album;


    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
}
