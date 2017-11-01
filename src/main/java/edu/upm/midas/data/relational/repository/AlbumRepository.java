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

    Album findByVersionNative(Date version);

    Date findLastVersionNative();

    Album findByLastVersionNative();

    List<Album> findByVersionGraterThanNative(Date version);

    List<Album> findByVersionSmallerThanNative(Date version);

    List<Album> findByNumberDiseasesNative(int number);

    List<Album> findByNumberDiseasesGreaterThanNative(int number);

    List<Album> findByNumberDiseasesSmallerThanNative(int number);
    //Importante!
    List<Object[]> findLinksOnWikipediaById(String albumId, Date version);
    //Importante!
    List<Object[]> findLinksByIdAndSourceNameNative(String albumId, Date version, String source);

    int maxSizeNative();

    List<Album> findByMaxSizeNative();
    
    List<Album> findAllQuery();

    void persist(Album album);

    int insertNative(String albumId, Date version, int number);

    int insertByAlbumIdAndVersionNative(String albumId, Date version);

    boolean deleteById(AlbumPK albumPK);

    void delete(Album album);

    Album update(Album album);

    int updateNumberDiseasesByIdNative(String albumId, Date version, int numberDiseases);

    Integer updateByIdQuery(Album album);
    
}