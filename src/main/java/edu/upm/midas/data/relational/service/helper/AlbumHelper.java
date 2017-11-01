package edu.upm.midas.data.relational.service.helper;
import edu.upm.midas.common.util.Common;
import edu.upm.midas.common.util.TimeProvider;
import edu.upm.midas.common.util.UniqueId;
import edu.upm.midas.data.relational.entities.addb.Album;
import edu.upm.midas.data.relational.entities.addb.AlbumDiseasePK;
import edu.upm.midas.data.relational.entities.addb.AlbumPK;
import edu.upm.midas.data.relational.service.AlbumDiseaseService;
import edu.upm.midas.data.relational.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by gerardo on 31/10/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className AlbumHelper
 * @see
 */
@Service
public class AlbumHelper {

    @Autowired
    private AlbumService albumService;
    @Autowired
    private AlbumDiseaseService albumDiseaseService;

    @Autowired
    private UniqueId uniqueIdService;
    @Autowired
    private Common commonService;
    @Autowired
    private TimeProvider timeProviderService;



    public Album insertIfExist(int numberDiseases){
        Album album = null;
        String albumId = uniqueIdService.generate(12);
        Date version = timeProviderService.getNow();

        AlbumPK albumPK = new AlbumPK();
        albumPK.setAlbumId(albumId);
        albumPK.setDate((java.sql.Date) version);
        album = albumService.findByIdNative(albumPK);
        if (album == null){
            int insert = albumService.insertNative(albumId, version, numberDiseases);
            album.setAlbumId(albumPK.getAlbumId());
            album.setDate(albumPK.getDate());
            album.setNumberDiseases(numberDiseases);
        }
        return album;

    }


    public void insertDiseases(String albumId, edu.upm.midas.model.extract.Disease disease){
        AlbumDiseasePK albumDiseasePK = new AlbumDiseasePK();
        albumDiseasePK.set
        albumDiseaseService.findByIdNative();
        albumDiseaseService.insertNative();
    }

}
