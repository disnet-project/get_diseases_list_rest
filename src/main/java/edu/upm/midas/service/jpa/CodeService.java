package edu.upm.midas.service.jpa;

import edu.upm.midas.model.jpa.Code;
import edu.upm.midas.model.jpa.CodePK;

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
public interface CodeService {

    Code findById(CodePK codePK);
    
    Code findByIdNative(CodePK codePK);
    
    List<Code> findAllQuery();

    void save(Code code);

    int insertNative(String codeId, Integer resourceId);

    void delete(Code code);

    Code update(Code code);

}
