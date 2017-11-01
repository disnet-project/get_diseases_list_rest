package edu.upm.midas.data.relational.repository;

import edu.upm.midas.data.relational.entities.addb.AlbumDisease;
import edu.upm.midas.data.relational.entities.addb.AlbumDiseasePK;

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
public interface AlbumDiseaseRepository {

    AlbumDisease findById(AlbumDiseasePK albumDiseasePK);

    Object[] findByIdNative(AlbumDiseasePK albumDiseasePK);
    
    List<AlbumDisease> findAllQuery();

    void persist(AlbumDisease albumDisease);

    int insertNative(String albumId, Date version, String diseaseId);

    boolean deleteById(AlbumDiseasePK albumDiseasePK);

    void delete(AlbumDisease albumDisease);

    AlbumDisease update(AlbumDisease albumDisease);

    Integer updateByIdQuery(AlbumDisease albumDisease);
    
}
