package edu.upm.midas.data.relational.repository;

import edu.upm.midas.data.relational.entities.addb.Disease;

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
public interface DiseaseRepository {

    Disease findById(String diseaseId);
    
    Disease findByIdNative(String diseaseId);
    
    List<Disease> findAllQuery();

    void persist(Disease album);

    int insertNative(String diseaseId, String name);

    boolean deleteById(String diseaseId);

    void delete(Disease album);

    Disease update(Disease album);

    int updateNative(String diseaseId, String name);

    Integer updateByIdQuery(Disease album);
    
}
