package edu.upm.midas.data.relational.service.impl;
import edu.upm.midas.data.relational.entities.addb.AlbumDisease;
import edu.upm.midas.data.relational.entities.addb.AlbumDiseasePK;
import edu.upm.midas.data.relational.repository.AlbumDiseaseRepository;
import edu.upm.midas.data.relational.service.AlbumDiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
        return daoAlbumDisease.findByIdNative(albumDiseasePK);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<AlbumDisease> findAllQuery() {
        return daoAlbumDisease.findAllQuery();
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void save(AlbumDisease albumDisease) {
        daoAlbumDisease.persist(albumDisease);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public int insertNative(String albumId, String version, String diseaseId) {
        return daoAlbumDisease.insertNative(albumId, version, diseaseId);
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
