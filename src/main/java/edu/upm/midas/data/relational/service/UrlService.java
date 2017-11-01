package edu.upm.midas.data.relational.service;


import edu.upm.midas.data.relational.entities.addb.Url;

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

    void save(Url url);

    int insertNative(String urlId, String url);

    void delete(Url url);

    Url update(Url url);
    
}
