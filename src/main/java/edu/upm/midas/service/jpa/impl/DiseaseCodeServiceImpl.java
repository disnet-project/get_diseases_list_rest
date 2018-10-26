package edu.upm.midas.service.jpa.impl;
import edu.upm.midas.model.jpa.DiseaseCode;
import edu.upm.midas.model.jpa.DiseaseCodePK;
import edu.upm.midas.repository.jpa.DiseaseCodeRepository;
import edu.upm.midas.service.jpa.DiseaseCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by gerardo on 27/10/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className DiseaseCodeServiceImpl
 * @see
 */
@Service("diseaseCodeService")
public class DiseaseCodeServiceImpl implements DiseaseCodeService {

    @Autowired
    private DiseaseCodeRepository daoDiseaseCode;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public DiseaseCode findById(DiseaseCodePK diseaseCodePK) {
        DiseaseCode diseaseCode = daoDiseaseCode.findById(diseaseCodePK);
        //if(source!=null)
        //Hibernate.initialize(source.getDiseasesBySidsource());
        return diseaseCode;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public DiseaseCode findByIdNative(DiseaseCodePK diseaseCodePK) {
        DiseaseCode diseaseCode = null;
        Object[] oQuery = daoDiseaseCode.findByIdNative(diseaseCodePK);
        if (oQuery != null){
            diseaseCode = new DiseaseCode();
            diseaseCode.setDiseaseId( (String) oQuery[0] );
            diseaseCode.setCodeId( (String) oQuery[1] );
            diseaseCode.setResourceId( (Integer) oQuery[2] );
        }
        //if(source!=null)
        //Hibernate.initialize(source.getDiseasesBySidsource());
        return diseaseCode;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<DiseaseCode> findAllQuery() {
        return daoDiseaseCode.findAllQuery();
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void save(DiseaseCode diseaseCode) {
        daoDiseaseCode.persist(diseaseCode);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public int insertNative(String diseaseId, String codeId, Integer resourceId) {
        return daoDiseaseCode.insertNative(diseaseId, codeId, resourceId);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void delete(DiseaseCode diseaseCode) {
        daoDiseaseCode.delete(diseaseCode);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public DiseaseCode update(DiseaseCode diseaseCode) {
        return daoDiseaseCode.update(diseaseCode);
    }
}
