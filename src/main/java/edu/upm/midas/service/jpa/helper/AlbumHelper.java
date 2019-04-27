package edu.upm.midas.service.jpa.helper;
import com.google.common.base.Throwables;
import edu.upm.midas.common.util.Common;
import edu.upm.midas.common.util.TimeProvider;
import edu.upm.midas.common.util.UniqueId;
import edu.upm.midas.model.jpa.Album;
import edu.upm.midas.model.jpa.AlbumDisease;
import edu.upm.midas.model.jpa.AlbumDiseasePK;
import edu.upm.midas.model.jpa.AlbumPK;
import edu.upm.midas.service.jpa.AlbumDiseaseService;
import edu.upm.midas.service.jpa.AlbumService;
import edu.upm.midas.enums.ApiErrorEnum;
import edu.upm.midas.model.response.ApiResponseError;
import edu.upm.midas.model.response.Disease;
import edu.upm.midas.service.error.ErrorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    @Autowired
    private ErrorService errorService;



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
            album = new Album();
            album.setAlbumId(albumPK.getAlbumId());
            album.setDate(albumPK.getDate());
            album.setNumberDiseases(numberDiseases);
        }
        return album;

    }


    public Album findById(String albumId, Date version){
        AlbumPK albumPK = new AlbumPK();
        albumPK.setAlbumId(albumId);
        albumPK.setDate((java.sql.Date) version);
        return albumService.findByIdNative(albumPK);
    }


    public void insertDiseases(Album album, String diseaseId){
        AlbumDiseasePK albumDiseasePK = new AlbumDiseasePK();
        albumDiseasePK.setAlbumId(album.getAlbumId());
        albumDiseasePK.setDate(album.getDate());
        albumDiseasePK.setDiseaseId(diseaseId);

        AlbumDisease oAlbumDisease = albumDiseaseService.findByIdNative(albumDiseasePK);
        if (oAlbumDisease == null){
            albumDiseaseService.insertNative(albumDiseasePK.getAlbumId(), albumDiseasePK.getDate(), albumDiseasePK.getDiseaseId());
        }
    }


    public int insertIgnoreDiseases(Album album, String diseaseId){
        return albumDiseaseService.insertIgnoreNative(album.getAlbumId(), album.getDate(), diseaseId);
    }


    public Album update(Album album){
        albumService.updateNumberDiseasesByIdNative(album.getAlbumId(), album.getDate());
        return findById(album.getAlbumId(), album.getDate());
    }

    public List<Disease> findLinksByIdAndSourceNameNativeAndReplaceSpecialCharacters(List<ApiResponseError> apiResponseErrors, String albumId, Date version, String source){
        List<Disease> diseases = new ArrayList<>();
        try {
            //Se sustituye para que cuando obtenga la lista de artículos. La obtiene tanto del album actual
            //como de la safe disease list
            diseases = albumService.findLinksByIdAndSourceNameNative(albumId, version, source);
            //diseases = albumService.getMergeSafeDiseaseListAndCurrentDiseaseListByAlbumIdAndVersionAndSourceNameNative(albumId, version, source);
            if (diseases.size() > 0) {
                for (Disease disease : diseases) {
                    String r = commonService.replaceSpecialCharactersToUnicode(disease.getUrl());
                    //System.out.println(r);
                    //System.out.println(commonService.replaceUnicodeToSpecialCharacters(r));
                    disease.setUrl(r);
                }
            }
        }catch (Exception e){
            //Se agrega el error en la lista principal de la respuesta
            errorService.insertApiErrorEnumGenericError(
                    apiResponseErrors,
                    ApiErrorEnum.INTERNAL_SERVER_ERROR,
                    Throwables.getRootCause(e).getClass().getName()+"",
                    e.getMessage()+"",
                    true,
                    null);
        }
        return diseases;
    }


}
