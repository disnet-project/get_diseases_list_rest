package edu.upm.midas.service.jpa.impl;
import edu.upm.midas.model.jpa.DiseaseUrl;
import edu.upm.midas.model.jpa.DiseaseUrlPK;
import edu.upm.midas.repository.jpa.DiseaseUrlRepository;
import edu.upm.midas.service.jpa.DiseaseUrlService;
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
@Service("diseaseUrlService")
public class DiseaseUrlServiceImpl implements DiseaseUrlService {

    @Autowired
    private DiseaseUrlRepository daoDiseaseUrl;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public DiseaseUrl findById(DiseaseUrlPK diseaseUrlPK) {
        DiseaseUrl diseaseUrl = daoDiseaseUrl.findById(diseaseUrlPK);
        //if(source!=null)
        //Hibernate.initialize(source.getDiseasesBySidsource());
        return diseaseUrl;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public DiseaseUrl findByIdNative(DiseaseUrlPK diseaseUrlPK) {
        DiseaseUrl diseaseUrl = daoDiseaseUrl.findByIdNative(diseaseUrlPK);
        return diseaseUrl;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<DiseaseUrl> findAllQuery() {
        return daoDiseaseUrl.findAllQuery();
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void save(DiseaseUrl diseaseUrl) {
        daoDiseaseUrl.persist(diseaseUrl);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public int insertNative(String diseaseId, String urlId, String sourceId) {
        return daoDiseaseUrl.insertNative(diseaseId, urlId, sourceId);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void delete(DiseaseUrl diseaseUrl) {
        daoDiseaseUrl.delete(diseaseUrl);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public DiseaseUrl update(DiseaseUrl diseaseUrl) {
        return daoDiseaseUrl.update(diseaseUrl);
    }
}
