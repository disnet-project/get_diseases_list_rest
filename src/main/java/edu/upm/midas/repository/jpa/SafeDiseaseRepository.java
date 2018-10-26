package edu.upm.midas.repository.jpa;

import edu.upm.midas.model.jpa.SafeDisease;

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
public interface SafeDiseaseRepository {

    SafeDisease findById(String diseaseId);

    SafeDisease findByIdNative(String diseaseId);

    Object[] findByNameNative(String name);

    String findLastIdNative();

    List<Object[]> findAllDiseasesBySourceName(String sourceName);
    
    List<SafeDisease> findAllQuery();

    void persist(SafeDisease disease);

    int insertNative(String diseaseId, String name);

    boolean deleteById(String diseaseId);

    void delete(SafeDisease disease);

    SafeDisease update(SafeDisease disease);

    int updateNative(String diseaseId, String name);

    Integer updateByIdQuery(SafeDisease disease);
    
}
