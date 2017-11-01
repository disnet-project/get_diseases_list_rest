package edu.upm.midas.data.relational.service;

import edu.upm.midas.data.relational.entities.addb.AlbumDisease;
import edu.upm.midas.data.relational.entities.addb.AlbumDiseasePK;

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
    
    List<AlbumDisease> findAllQuery();

    void save(AlbumDisease albumDisease);

    int insertNative(String albumId, String version, String diseaseId);

    void delete(AlbumDisease albumDisease);

    AlbumDisease update(AlbumDisease albumDisease);

}
