package edu.upm.midas.repository.jpa;

import edu.upm.midas.model.jpa.Album;
import edu.upm.midas.model.jpa.AlbumPK;

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

    Object[] findByIdNative(AlbumPK albumPK);

    Album findByVersionNative(Date version);

    Object[] findByVersionAndSourceNative(Date version, String sourceName);

    Date findLastVersionNative();

    Date getPenultimateDiseaseAlbumDateNative(boolean currentDate, java.sql.Date specificDate);

    Date getPenultimateDiseaseAlbumDateBySourceNative(boolean currentDate, java.sql.Date specificDate, String source);

    Object[] findByLastVersionNative();

    Object[] findFirstVersionNative();

    List<Album> findByVersionGraterThanNative(Date version);

    List<Album> findByVersionSmallerThanNative(Date version);

    List<Album> findByNumberDiseasesNative(int number);

    List<Album> findByNumberDiseasesGreaterThanNative(int number);

    List<Album> findByNumberDiseasesSmallerThanNative(int number);
    //Importante!
    List<Object[]> findLinksOnWikipediaById(String albumId, Date version);
    //Importante!
    List<Object[]> findLinksByIdAndSourceNameNative(String albumId, Date version, String source);
    //Importante!
    List<Object[]> findSafeDiseaseListNative(String sourceName);
    //Importante! usando la safe disease list (la primer version 2018-02-01)
    List<Object[]> getMergeSafeDiseaseListAndCurrentDiseaseListByAlbumIdAndVersionAndSourceNameNative(String albumId, Date version, String source);

    int maxSizeNative();

    List<Album> findByMaxSizeNative();
    
    List<Object[]> findAllNative();

    void persist(Album album);

    int insertNative(String albumId, Date version, int number);

    int insertByAlbumIdAndVersionNative(String albumId, Date version);

    boolean deleteById(AlbumPK albumPK);

    void delete(Album album);

    Album update(Album album);

    int updateNumberDiseasesByIdNative(String albumId, Date version);

    Integer updateByIdQuery(Album album);
    
}
