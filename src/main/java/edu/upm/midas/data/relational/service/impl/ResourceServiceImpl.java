package edu.upm.midas.data.relational.service.impl;
import edu.upm.midas.data.relational.entities.addb.Resource;
import edu.upm.midas.data.relational.repository.ResourceRepository;
import edu.upm.midas.data.relational.service.ResourceService;
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
 * @className ResourceServiceImpl
 * @see
 */
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository daoResource;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public Resource findById(Integer resourceId) {
        Resource resource = daoResource.findById(resourceId);
        //if(source!=null)
        //Hibernate.initialize(source.getDiseasesBySidsource());
        return resource;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public Resource findByIdQuery(Integer resourceId) {
        Resource resource = daoResource.findByIdQuery(resourceId);
        //if(source!=null)
        //Hibernate.initialize(source.getDiseasesBySidsource());
        return resource;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public Resource findByNameQuery(String name) {
        return daoResource.findByNameQuery(name);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public int findIdByNameQuery(String name) {
        return daoResource.findIdByNameQuery(name);
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public Resource findByIdNative(Integer resourceId) {
        return daoResource.findByIdNative( resourceId );
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Resource> findAllQuery() {
        List<Resource> resourceEntityList = daoResource.findAllQuery();
        return resourceEntityList;
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void save(Resource resource) {
        daoResource.persist(resource);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public int insertNative(int resourceId, String name) {
        return daoResource.insertNative( resourceId, name );
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void delete(Resource resource) {
        daoResource.delete(resource);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public Resource update(Resource resource) {
        return daoResource.update(resource);
    }
}
