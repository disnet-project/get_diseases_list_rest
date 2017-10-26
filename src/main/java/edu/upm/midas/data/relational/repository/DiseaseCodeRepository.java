package edu.upm.midas.data.relational.repository;

import edu.upm.midas.data.relational.entities.addb.DiseaseCode;
import edu.upm.midas.data.relational.entities.addb.DiseaseCodePK;

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
public interface DiseaseCodeRepository {

    DiseaseCode findById(DiseaseCodePK diseaseCodePK);
    
    DiseaseCode findByIdNative(DiseaseCodePK diseaseCodePK);
    
    List<DiseaseCode> findAllQuery();

    void persist(DiseaseCode diseaseUrl);

    int insertNative(String diseaseId, String urlId, String sourceId);

    boolean deleteById(DiseaseCodePK diseaseCodePK);

    void delete(DiseaseCode diseaseUrl);

    DiseaseCode update(DiseaseCode diseaseUrl);

    Integer updateByIdQuery(DiseaseCode diseaseUrl);
    
}
