package edu.upm.midas.data.relational.repository;

import edu.upm.midas.data.relational.entities.addb.Album;
import edu.upm.midas.data.relational.entities.addb.AlbumPK;

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
public interface AlbumRepository {

    Album findById(AlbumPK albumPK);
    
    Album findByIdNative(AlbumPK albumPK);
    
    List<Album> findAllQuery();

    void persist(Album album);

    int insertNative(String diseaseId, Date version);

    boolean deleteById(AlbumPK albumPK);

    void delete(Album album);

    Album update(Album album);

    int updateNative(String diseaseId, Date version);

    Integer updateByIdQuery(Album album);
    
}
