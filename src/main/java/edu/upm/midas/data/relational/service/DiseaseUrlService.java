package edu.upm.midas.data.relational.service;

import edu.upm.midas.data.relational.entities.addb.DiseaseUrl;
import edu.upm.midas.data.relational.entities.addb.DiseaseUrlPK;

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
public interface DiseaseUrlService {

    DiseaseUrl findById(DiseaseUrlPK diseaseUrlPK);
    
    DiseaseUrl findByIdNative(DiseaseUrlPK diseaseUrlPK);
    
    List<DiseaseUrl> findAllQuery();

    void save(DiseaseUrl diseaseUrl);

    int insertNative(String diseaseId, String urlId, String sourceId);

    void delete(DiseaseUrl diseaseUrl);

    DiseaseUrl update(DiseaseUrl diseaseUrl);

}
