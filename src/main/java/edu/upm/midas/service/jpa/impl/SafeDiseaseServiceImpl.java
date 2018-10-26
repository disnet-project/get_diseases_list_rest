package edu.upm.midas.service.jpa.impl;

import edu.upm.midas.model.jpa.SafeDisease;
import edu.upm.midas.repository.jpa.SafeDiseaseRepository;
import edu.upm.midas.service.jpa.SafeDiseaseService;
import edu.upm.midas.model.response.Disease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gerardo on 27/10/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className DiseaseServiceImpl
 * @see
 */
@Service("safeDiseaseService")
public class SafeDiseaseServiceImpl implements SafeDiseaseService {

    @Autowired
    private SafeDiseaseRepository daoDisease;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public SafeDisease findById(String diseaseId) {
        SafeDisease disease = daoDisease.findById(diseaseId);
        //if(source!=null)
        //Hibernate.initialize(source.getDiseasesBySidsource());
        return disease;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public SafeDisease findByIdNative(String diseaseId) {
        SafeDisease disease = daoDisease.findByIdNative(diseaseId);
        //if(source!=null)
        //Hibernate.initialize(source.getDiseasesBySidsource());
        return disease;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public SafeDisease findByNameNative(String name) {
        SafeDisease disease = null;
        Object[] oQuery = daoDisease.findByNameNative(name);
        if (oQuery != null){
            disease = new SafeDisease();
            disease.setDiseaseId( (String) oQuery[0] );
            disease.setName( (String) oQuery[1] );
        }
        //if(source!=null)
        //Hibernate.initialize(source.getDiseasesBySidsource());
        return disease;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public String findLastIdNative() {
        return daoDisease.findLastIdNative();
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Disease> findAllDiseasesBySourceName(String sourceName) {
        List<Disease> diseaseList = new ArrayList<>();
        //System.out.println(albumId +"|"+ version+"|"+source);
        List<Object[]> oQueryList = daoDisease.findAllDiseasesBySourceName(sourceName);
        if (oQueryList != null) {
            for (Object[] dis : oQueryList) {
                Disease disease = new Disease();
                disease.setDiseaseId((String) dis[2]);
                disease.setName((String) dis[0]);
                disease.setUrl((String) dis[1]);
                //System.out.println(disease.getName() + " - " + disease.getUrl());
                diseaseList.add(disease);
            }
        }
        return diseaseList;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<SafeDisease> findAllQuery() {
        return daoDisease.findAllQuery();
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void save(SafeDisease disease) {
        daoDisease.persist(disease);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public int insertNative(String diseaseId, String name) {
        return daoDisease.insertNative(diseaseId, name);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void delete(SafeDisease disease) {
        daoDisease.delete(disease);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public SafeDisease update(SafeDisease disease) {
        return daoDisease.update(disease);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public int updateNative(String diseaseId, String name) {
        return daoDisease.updateNative(diseaseId, name);
    }
}
