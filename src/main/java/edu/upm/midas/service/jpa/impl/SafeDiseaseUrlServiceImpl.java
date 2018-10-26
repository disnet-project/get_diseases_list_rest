package edu.upm.midas.service.jpa.impl;

import edu.upm.midas.model.jpa.SafeDiseaseUrl;
import edu.upm.midas.model.jpa.SafeDiseaseUrlPK;
import edu.upm.midas.repository.jpa.SafeDiseaseUrlRepository;
import edu.upm.midas.service.jpa.SafeDiseaseUrlService;
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
 * @className DiseaseUrlServiceImpl
 * @see
 */
@Service("safeDiseaseUrlService")
public class SafeDiseaseUrlServiceImpl implements SafeDiseaseUrlService {

    @Autowired
    private SafeDiseaseUrlRepository daoDiseaseUrl;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public SafeDiseaseUrl findById(SafeDiseaseUrlPK diseaseUrlPK) {
        SafeDiseaseUrl diseaseUrl = daoDiseaseUrl.findById(diseaseUrlPK);
        //if(source!=null)
        //Hibernate.initialize(source.getDiseasesBySidsource());
        return diseaseUrl;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public SafeDiseaseUrl findByIdNative(SafeDiseaseUrlPK diseaseUrlPK) {
        SafeDiseaseUrl diseaseUrl = daoDiseaseUrl.findByIdNative(diseaseUrlPK);
        return diseaseUrl;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<SafeDiseaseUrl> findAllQuery() {
        return daoDiseaseUrl.findAllQuery();
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void save(SafeDiseaseUrl diseaseUrl) {
        daoDiseaseUrl.persist(diseaseUrl);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public int insertNative(String diseaseId, String urlId, String sourceId) {
        return daoDiseaseUrl.insertNative(diseaseId, urlId, sourceId);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void delete(SafeDiseaseUrl diseaseUrl) {
        daoDiseaseUrl.delete(diseaseUrl);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public SafeDiseaseUrl update(SafeDiseaseUrl diseaseUrl) {
        return daoDiseaseUrl.update(diseaseUrl);
    }
}
