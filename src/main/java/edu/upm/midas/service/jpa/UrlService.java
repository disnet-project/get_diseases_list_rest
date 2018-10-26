package edu.upm.midas.service.jpa;


import edu.upm.midas.model.jpa.Url;

/**
 * Created by gerardo on 13/06/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project edu.upm.midas
 * @className UrlRepository
 * @see
 */
public interface UrlService {

    Url findById(String urlId);

    Url findByIdNative(String urlId);

    Url findByUrlNative(String url);

    void save(Url url);

    int insertNative(String urlId, String url);

    void delete(Url url);

    Url update(Url url);
    
}
