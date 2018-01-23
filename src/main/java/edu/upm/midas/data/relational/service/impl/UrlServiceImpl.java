package edu.upm.midas.data.relational.service.impl;

import edu.upm.midas.data.relational.entities.addb.Url;
import edu.upm.midas.data.relational.repository.UrlRepository;
import edu.upm.midas.data.relational.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by gerardo on 27/10/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className UrlServiceImpl
 * @see
 */
@Service("urlService")
public class UrlServiceImpl implements UrlService {

    @Autowired
    private UrlRepository daoUrl;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public Url findById(String urlId) {
        Url url = daoUrl.findById(urlId);
        //if(source!=null)
        //Hibernate.initialize(source.getDiseasesBySidsource());
        return url;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public Url findByIdNative(String urlId) {
        Url url = daoUrl.findByIdNative(urlId);
        return url;
    }

    @Override
    public Url findByUrlNative(String url) {
        Url url_ = daoUrl.findByUrlNative(url);
        return url_;
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void save(Url url) {
        daoUrl.persist(url);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public int insertNative(String urlId, String url) {
        return daoUrl.insertNative( urlId, url );
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void delete(Url url) {
        daoUrl.delete(url);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public Url update(Url url) {
        return daoUrl.update(url);
    }

}
