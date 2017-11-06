package edu.upm.midas.data.relational.service.impl;
import edu.upm.midas.data.relational.entities.addb.Album;
import edu.upm.midas.data.relational.entities.addb.AlbumPK;
import edu.upm.midas.data.relational.repository.AlbumRepository;
import edu.upm.midas.data.relational.service.AlbumService;
import edu.upm.midas.model.response.Disease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gerardo on 27/10/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className AlbumServiceImpl
 * @see
 */
@Service("albumService")
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    private AlbumRepository daoAlbum;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public Album findById(AlbumPK albumPK) {
        Album album = daoAlbum.findById(albumPK);
        //if(source!=null)
        //Hibernate.initialize(source.getDiseasesBySidsource());
        return album;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public Album findByIdNative(AlbumPK albumPK) {
        Album album = null;
        Object[] oQuery = daoAlbum.findByIdNative(albumPK);
        if (oQuery != null){
            album = new Album();
            album.setAlbumId( (String) oQuery[0] );
            album.setDate( (java.sql.Date) oQuery[1] );
            album.setNumberDiseases( (Integer) oQuery[2] );
        }
        //if(source!=null)
        //Hibernate.initialize(source.getDiseasesBySidsource());
        return album;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public Album findByVersionNative(Date version) {
        return daoAlbum.findByVersionNative(version);
    }

    @Override
    public Date findLastVersionNative() {
        return daoAlbum.findLastVersionNative();
    }

    @Override
    public Album findByLastVersionNative() {
        Album album = null;
        Object[] oQuery = daoAlbum.findByLastVersionNative();
        if (oQuery != null){
            album = new Album();
            album.setAlbumId( (String) oQuery[0] );
            album.setDate( (java.sql.Date) oQuery[1] );
            album.setNumberDiseases( (Integer) oQuery[2] );
        }
        return album;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Album> findByVersionGraterThanNative(Date version) {
        return daoAlbum.findByVersionGraterThanNative(version);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Album> findByVersionSmallerThanNative(Date version) {
        return daoAlbum.findByVersionSmallerThanNative(version);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Album> findByNumberDiseasesNative(int number) {
        return daoAlbum.findByNumberDiseasesNative(number);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Album> findByNumberDiseasesGreaterThanNative(int number) {
        return daoAlbum.findByNumberDiseasesGreaterThanNative(number);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Album> findByNumberDiseasesSmallerThanNative(int number) {
        return daoAlbum.findByNumberDiseasesSmallerThanNative(number);
    }

    @Override
    public List<Object[]> findLinksOnWikipediaById(String albumId, Date version) {
        return daoAlbum.findLinksOnWikipediaById(albumId, version);
    }

    @Override
    public List<Disease> findLinksByIdAndSourceNameNative(String albumId, Date version, String source) {
        List<Disease> diseaseList = new ArrayList<>();
        System.out.println(albumId +"|"+ version+"|"+source);
        List<Object[]> oQueryList = daoAlbum.findLinksByIdAndSourceNameNative(albumId, version, source);
        if (oQueryList != null) {
            for (Object[] dis : oQueryList) {
                Disease disease = new Disease();
                disease.setAlbumId((String) dis[0]);
                disease.setVersion((Date) dis[1]);
                disease.setDiseaseId((String) dis[2]);
                disease.setName((String) dis[3]);
                disease.setSourceId((String) dis[4]);
                disease.setSourceName((String) dis[5]);
                disease.setUrl((String) dis[6]);
                System.out.println(disease.getName() + " - " + disease.getUrl());
                diseaseList.add(disease);
            }
        }
        return diseaseList;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public int maxSizeNative() {
        return daoAlbum.maxSizeNative();
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Album> findByMaxSizeNative() {
        return daoAlbum.findByMaxSizeNative();
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Album> findAllNative() {
        List<Album> albums = null;
        List<Object[]> allNative = daoAlbum.findAllNative();
        if (allNative != null) {
            for (Object[] alb : allNative) {
                Album album = new Album();
                album.setAlbumId((String) alb[0]);
                album.setDate((java.sql.Date) alb[1]);
                album.setNumberDiseases((Integer) alb[2]);
                albums.add(album);
            }
        }
        //if(source!=null)
        //Hibernate.initialize(source.getDiseasesBySidsource());
        return albums;
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void save(Album album) {
        daoAlbum.persist(album);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public int insertNative(String albumId, Date version, int number) {
        return daoAlbum.insertNative(albumId, version, number);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public int insertByAlbumIdAndVersionNative(String albumId, Date version) {
        return daoAlbum.insertByAlbumIdAndVersionNative(albumId, version);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void delete(Album album) {
        daoAlbum.delete(album);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public Album update(Album album) {
        return daoAlbum.update(album);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public int updateNumberDiseasesByIdNative(String albumId, Date version) {
        return daoAlbum.updateNumberDiseasesByIdNative(albumId, version);
    }
}
