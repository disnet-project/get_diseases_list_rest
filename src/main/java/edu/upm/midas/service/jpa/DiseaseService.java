package edu.upm.midas.service.jpa;

import edu.upm.midas.model.jpa.Disease;

import java.sql.Date;
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
public interface DiseaseService {

    Disease findById(String diseaseId);

    Disease findByIdNative(String diseaseId);

    Disease findByNameNative(String name);

    String findLastIdNative();

    List<Disease> findAllQuery();

    List<edu.upm.midas.model.response.Disease> getAllDiseasesFromDiseaseSafeListAndLastDiseaseAlbumBySource(Date current_version, Date penultimate_version, String source);

    void save(Disease disease);

    int insertNative(String diseaseId, String name, String sourceId, boolean partly_irrelevant, boolean totally_irrelevant);

    void delete(Disease disease);

    Disease update(Disease disease);

    int updateNative(String diseaseId, String name);

}
