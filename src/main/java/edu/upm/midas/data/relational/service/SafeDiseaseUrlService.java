package edu.upm.midas.data.relational.service;

import edu.upm.midas.data.relational.entities.addb.SafeDiseaseUrl;
import edu.upm.midas.data.relational.entities.addb.SafeDiseaseUrlPK;

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
public interface SafeDiseaseUrlService {

    SafeDiseaseUrl findById(SafeDiseaseUrlPK diseaseUrlPK);

    SafeDiseaseUrl findByIdNative(SafeDiseaseUrlPK diseaseUrlPK);
    
    List<SafeDiseaseUrl> findAllQuery();

    void save(SafeDiseaseUrl diseaseUrl);

    int insertNative(String diseaseId, String urlId, String sourceId);

    void delete(SafeDiseaseUrl diseaseUrl);

    SafeDiseaseUrl update(SafeDiseaseUrl diseaseUrl);

}
