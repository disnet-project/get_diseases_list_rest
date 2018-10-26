package edu.upm.midas.service.jpa.impl;

import edu.upm.midas.model.jpa.SafeUrl;
import edu.upm.midas.repository.jpa.SafeUrlRepository;
import edu.upm.midas.service.jpa.SafeUrlService;
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
@Service("safeUrlService")
public class SafeUrlServiceImpl implements SafeUrlService {

    @Autowired
    private SafeUrlRepository daoUrl;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public SafeUrl findById(String urlId) {
        SafeUrl url = daoUrl.findById(urlId);
        //if(source!=null)
        //Hibernate.initialize(source.getDiseasesBySidsource());
        return url;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    public SafeUrl findByIdNative(String urlId) {
        SafeUrl url = daoUrl.findByIdNative(urlId);
        return url;
    }

    @Override
    public SafeUrl findByUrlNative(String url) {
        SafeUrl url_ = daoUrl.findByUrlNative(url);
        return url_;
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void save(SafeUrl url) {
        daoUrl.persist(url);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public int insertNative(String urlId, String url) {
        return daoUrl.insertNative( urlId, url );
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public void delete(SafeUrl url) {
        daoUrl.delete(url);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    public SafeUrl update(SafeUrl url) {
        return daoUrl.update(url);
    }

}
