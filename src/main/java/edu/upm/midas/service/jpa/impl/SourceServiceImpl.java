package edu.upm.midas.service.jpa.impl;
import edu.upm.midas.model.jpa.Source;
import edu.upm.midas.repository.jpa.SourceRepository;
import edu.upm.midas.service.jpa.SourceService;
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
 * @className SourceServiceImpl
 * @see
 */
@Service("sourceService")
public class SourceServiceImpl implements SourceService {

    @Autowired
    private SourceRepository daoSource;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public Source findById(String sourceId) {
        Source source = daoSource.findById((String) sourceId);
        //if(source!=null)
        //Hibernate.initialize(source.getDiseasesBySidsource());
        return source;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public Source findByNameQuery(String name) {
        Source source = daoSource.findByNameQuery(name);
/*
        if(source!=null)
            Hibernate.initialize(source.getVersionList());
*/
        return source;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public String findIdByNameNative(String name) {
        return daoSource.findIdByNameNative( name );
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public Source findByIdNative(String sourceId) {
        return daoSource.findByIdNative( sourceId );
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Source> findAllNative() {
        List<Source> listSourceEntities = daoSource.findAllNative();
        return listSourceEntities;    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void save(Source source) {
        daoSource.persist(source);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public int insertNative(String sourceId, String name) {
        return daoSource.insertNative( sourceId, name );
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void delete(Source source) {
        daoSource.delete(source);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public Source update(Source source) {
        return daoSource.update(source);
    }
}
