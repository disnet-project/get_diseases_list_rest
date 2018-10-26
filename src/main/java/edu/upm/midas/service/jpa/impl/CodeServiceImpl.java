package edu.upm.midas.service.jpa.impl;
import edu.upm.midas.model.jpa.Code;
import edu.upm.midas.model.jpa.CodePK;
import edu.upm.midas.repository.jpa.CodeRepository;
import edu.upm.midas.service.jpa.CodeService;
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
 * @className CodeServiceImpl
 * @see
 */
@Service("codeService")
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeRepository daoCode;

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public Code findById(CodePK codePK) {
        Code disease = daoCode.findById(codePK);
        //if(source!=null)
        //Hibernate.initialize(source.getDiseasesBySidsource());
        return disease;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public Code findByIdNative(CodePK codePK) {
        Code code = null;
        Object[] oQuery = daoCode.findByIdNative(codePK);
        if (oQuery != null){
            code = new Code();
            code.setCodeId( (String) oQuery[0] );
            code.setResourceId( (Integer) oQuery[1] );
        }
        //if(source!=null)
        //Hibernate.initialize(source.getDiseasesBySidsource());
        return code;
    }

    @Transactional(propagation= Propagation.REQUIRED,readOnly=true)
    @Override
    public List<Code> findAllQuery() {
        return daoCode.findAllQuery();
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void save(Code code) {
        daoCode.persist(code);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public int insertNative(String codeId, Integer resourceId) {
        return daoCode.insertNative(codeId, resourceId);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public void delete(Code code) {
        daoCode.delete(code);
    }

    @Transactional(propagation= Propagation.REQUIRED)
    @Override
    public Code update(Code code) {
        return daoCode.update(code);
    }
}
