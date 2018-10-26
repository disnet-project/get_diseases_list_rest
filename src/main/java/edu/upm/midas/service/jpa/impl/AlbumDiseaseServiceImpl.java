package edu.upm.midas.service.jpa.impl;
import edu.upm.midas.model.jpa.AlbumDisease;
import edu.upm.midas.model.jpa.AlbumDiseasePK;
import edu.upm.midas.repository.jpa.AlbumDiseaseRepository;
import edu.upm.midas.service.jpa.AlbumDiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by gerardo on 27/10/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className AlbumDiseaseServiceImpl
 * @see
 */
@Service("albumDiseaseService")
public class AlbumDiseaseServiceImpl implements AlbumDiseaseService {

    @Autowired
    private AlbumDiseaseRepository daoAlbumDisease;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public AlbumDisease findById(AlbumDiseasePK albumDiseasePK) {
        return daoAlbumDisease.findById(albumDiseasePK);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public AlbumDisease findByIdNative(AlbumDiseasePK albumDiseasePK) {
        AlbumDisease albumDisease = null;
        Object[] oQuery = daoAlbumDisease.findByIdNative(albumDiseasePK);
        if (oQuery != null) {
            albumDisease = new AlbumDisease();
            albumDisease.setAlbumId((String) oQuery[0]);
            albumDisease.setDate((java.sql.Date) oQuery[1]);
            albumDisease.setDiseaseId((String) oQuery[2]);
        }
        //if(source!=null)
        //Hibernate.initialize(source.getDiseasesBySidsource());
        return albumDisease;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<AlbumDisease> findAllNative() {
        List<AlbumDisease> albumDiseaseList = null;
        List<Object[]> allNative = daoAlbumDisease.findAllNative();
        if (allNative != null) {
            for (Object[] alb : allNative) {
                AlbumDisease albumDisease = new AlbumDisease();
                albumDisease.setAlbumId((String) alb[0]);
                albumDisease.setDate((java.sql.Date) alb[1]);
                albumDisease.setDiseaseId((String) alb[2]);
                albumDiseaseList.add(albumDisease);
            }
        }
        //if(source!=null)
        //Hibernate.initialize(source.getDiseasesBySidsource());
        return albumDiseaseList;
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void save(AlbumDisease albumDisease) {
        daoAlbumDisease.persist(albumDisease);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public int insertNative(String albumId, Date version, String diseaseId) {
        return daoAlbumDisease.insertNative(albumId, version, diseaseId);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public int insertIgnoreNative(String albumId, Date version, String diseaseId) {
        return daoAlbumDisease.insertIgnoreNative(albumId, version, diseaseId);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void delete(AlbumDisease albumDisease) {
        daoAlbumDisease.delete(albumDisease);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public AlbumDisease update(AlbumDisease albumDisease) {
        return daoAlbumDisease.update(albumDisease);
    }
}
