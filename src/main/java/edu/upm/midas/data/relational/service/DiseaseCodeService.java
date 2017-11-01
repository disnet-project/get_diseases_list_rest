package edu.upm.midas.data.relational.service;

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
public interface DiseaseCodeService {

    DiseaseCode findById(DiseaseCodePK diseaseCodePK);

    DiseaseCode findByIdNative(DiseaseCodePK diseaseCodePK);
    
    List<DiseaseCode> findAllQuery();

    void save(DiseaseCode diseaseCode);

    int insertNative(String diseaseId, String codeId, Integer resourceId);

    void delete(DiseaseCode diseaseCode);

    DiseaseCode update(DiseaseCode diseaseCode);

}
