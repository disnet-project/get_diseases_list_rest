package edu.upm.midas.service.jpa;


import edu.upm.midas.model.jpa.SafeUrl;

/**
 * Created by gerardo on 13/06/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project edu.upm.midas
 * @className UrlRepository
 * @see
 */
public interface SafeUrlService {

    SafeUrl findById(String urlId);

    SafeUrl findByIdNative(String urlId);

    SafeUrl findByUrlNative(String url);

    void save(SafeUrl url);

    int insertNative(String urlId, String url);

    void delete(SafeUrl url);

    SafeUrl update(SafeUrl url);
    
}
