package edu.upm.midas.repository.jpa;

import edu.upm.midas.model.jpa.DiseaseUrl;
import edu.upm.midas.model.jpa.DiseaseUrlPK;

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
