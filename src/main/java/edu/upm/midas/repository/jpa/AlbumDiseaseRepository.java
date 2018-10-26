package edu.upm.midas.repository.jpa;

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
public interface AlbumDiseaseRepository {

    AlbumDisease findById(AlbumDiseasePK albumDiseasePK);

    Object[] findByIdNative(AlbumDiseasePK albumDiseasePK);
    
    List<Object[]> findAllNative();

    void persist(AlbumDisease albumDisease);

    int insertNative(String albumId, Date version, String diseaseId);

    int insertIgnoreNative(String albumId, Date version, String diseaseId);

    boolean deleteById(AlbumDiseasePK albumDiseasePK);

    void delete(AlbumDisease albumDisease);

    AlbumDisease update(AlbumDisease albumDisease);

    Integer updateByIdQuery(AlbumDisease albumDisease);
    
}
