package edu.upm.midas.data.relational.service;

import edu.upm.midas.data.relational.entities.addb.Code;
import edu.upm.midas.data.relational.entities.addb.CodePK;

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
