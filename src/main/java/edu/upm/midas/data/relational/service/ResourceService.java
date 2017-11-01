package edu.upm.midas.data.relational.service;

import edu.upm.midas.data.relational.entities.addb.Resource;

import java.util.List;

/**
 * Created by gerardo on 09/06/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project edsssdb
 * @className ResourceRepository
 * @see
 */
public interface ResourceService {

    Resource findById(Integer resourceId);

    Resource findByIdQuery(Integer resourceId);

    Resource findByNameQuery(String name);

    int findIdByNameQuery(String name);

    Resource findByIdNative(Integer resourceId);

    List<Resource> findAllQuery();

    void save(Resource resource);

    int insertNative(int resourceId, String name);

    void delete(Resource resource);

    Resource update(Resource resource);

}
