package edu.upm.midas.data.relational.repository;

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
public interface DiseaseUrlRepository {

    DiseaseUrl findById(DiseaseUrlPK diseaseUrlPK);
    
    DiseaseUrl findByIdNative(DiseaseUrlPK diseaseUrlPK);
    
    List<DiseaseUrl> findAllQuery();

    void persist(DiseaseUrl diseaseUrl);

    int insertNative(String diseaseId, String urlId, String sourceId);

    boolean deleteById(DiseaseUrlPK diseaseUrlPK);

    void delete(DiseaseUrl diseaseUrl);

    DiseaseUrl update(DiseaseUrl diseaseUrl);

    Integer updateByIdQuery(DiseaseUrl diseaseUrl);
    
}
