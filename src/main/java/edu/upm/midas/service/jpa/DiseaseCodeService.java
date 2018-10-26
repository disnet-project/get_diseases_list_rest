package edu.upm.midas.service.jpa;

import edu.upm.midas.model.jpa.DiseaseCode;
import edu.upm.midas.model.jpa.DiseaseCodePK;

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
