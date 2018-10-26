package edu.upm.midas.repository.jpa;

import edu.upm.midas.model.jpa.SafeDiseaseUrl;
import edu.upm.midas.model.jpa.SafeDiseaseUrlPK;

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
public interface SafeDiseaseUrlRepository {

    SafeDiseaseUrl findById(SafeDiseaseUrlPK diseaseUrlPK);

    SafeDiseaseUrl findByIdNative(SafeDiseaseUrlPK diseaseUrlPK);
    
    List<SafeDiseaseUrl> findAllQuery();

    void persist(SafeDiseaseUrl diseaseUrl);

    int insertNative(String diseaseId, String urlId, String sourceId);

    boolean deleteById(SafeDiseaseUrlPK diseaseUrlPK);

    void delete(SafeDiseaseUrl diseaseUrl);

    SafeDiseaseUrl update(SafeDiseaseUrl diseaseUrl);

    Integer updateByIdQuery(SafeDiseaseUrl diseaseUrl);
    
}
