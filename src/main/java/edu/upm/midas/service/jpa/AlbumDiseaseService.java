package edu.upm.midas.service.jpa;

import edu.upm.midas.model.jpa.AlbumDisease;
import edu.upm.midas.model.jpa.AlbumDiseasePK;

import java.util.Date;
import java.util.List;

/**
 * Created by gerardo on 09/06/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project edsssdb
 * @className ResourceRepository
 * @see
 */
public interface AlbumDiseaseService {

    AlbumDisease findById(AlbumDiseasePK albumDiseasePK);
    
    AlbumDisease findByIdNative(AlbumDiseasePK albumDiseasePK);
    
    List<AlbumDisease> findAllNative();

    void save(AlbumDisease albumDisease);

    int insertNative(String albumId, Date version, String diseaseId);

    int insertIgnoreNative(String albumId, Date version, String diseaseId);

    void delete(AlbumDisease albumDisease);

    AlbumDisease update(AlbumDisease albumDisease);

}
