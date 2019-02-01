package edu.upm.midas.service.jpa.impl;
import edu.upm.midas.model.jpa.Disease;
import edu.upm.midas.repository.jpa.DiseaseRepository;
import edu.upm.midas.service.jpa.DiseaseService;
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
 * @className DiseaseServiceImpl
 * @see
 */
@Service("diseaseService")
public class DiseaseServiceImpl implements DiseaseService {

    @Autowired
    private DiseaseRepository daoDisease;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public Disease findById(String diseaseId) {
        Disease disease = daoDisease.findById(diseaseId);
        //if(source!=null)
        //Hibernate.initialize(source.getDiseasesBySidsource());
        return disease;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public Disease findByIdNative(String diseaseId) {
        Disease disease = daoDisease.findByIdNative(diseaseId);
        //if(source!=null)
        //Hibernate.initialize(source.getDiseasesBySidsource());
        return disease;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public Disease findByNameNative(String name) {
        Disease disease = null;
        Object[] oQuery = daoDisease.findByNameNative(name);
        if (oQuery != null){
            disease = new Disease();
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
    public List<Disease> findAllQuery() {
        return daoDisease.findAllQuery();
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void save(Disease disease) {
        daoDisease.persist(disease);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public int insertNative(String diseaseId, String name, String sourceId) {
        return daoDisease.insertNative(diseaseId, name, sourceId);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void delete(Disease disease) {
        daoDisease.delete(disease);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public Disease update(Disease disease) {
        return daoDisease.update(disease);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public int updateNative(String diseaseId, String name) {
        return daoDisease.updateNative(diseaseId, name);
    }
}
