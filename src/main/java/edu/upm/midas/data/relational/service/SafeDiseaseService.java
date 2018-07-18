package edu.upm.midas.data.relational.service;

import edu.upm.midas.data.relational.entities.addb.SafeDisease;
import edu.upm.midas.model.response.Disease;

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
public interface SafeDiseaseService {

    SafeDisease findById(String diseaseId);

    SafeDisease findByIdNative(String diseaseId);

    SafeDisease findByNameNative(String name);

    String findLastIdNative();

    List<Disease> findAllDiseasesBySourceName(String sourceName);

    List<SafeDisease> findAllQuery();

    void save(SafeDisease disease);

    int insertNative(String diseaseId, String name);

    void delete(SafeDisease disease);

    SafeDisease update(SafeDisease disease);

    int updateNative(String diseaseId, String name);

}
