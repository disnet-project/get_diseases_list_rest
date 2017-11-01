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

    Object[] findByIdNative(DiseaseCodePK diseaseCodePK);
    
    List<DiseaseCode> findAllQuery();

    void persist(DiseaseCode diseaseCode);

    int insertNative(String diseaseId, String codeId, Integer resourceId);

    boolean deleteById(DiseaseCodePK diseaseCodePK);

    void delete(DiseaseCode diseaseCode);

    DiseaseCode update(DiseaseCode diseaseCode);

    Integer updateByIdQuery(DiseaseCode diseaseCode);
    
}