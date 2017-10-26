package edu.upm.midas.data.relational.repository;

import edu.upm.midas.data.relational.entities.addb.Code;
import edu.upm.midas.data.relational.entities.addb.CodePK;

import java.util.Date;
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
public interface CodeRepository {

    Code findById(CodePK codePK);
    
    Code findByIdNative(CodePK codePK);
    
    List<Code> findAllQuery();

    void persist(Code code);

    int insertNative(String codeId, Integer resourceId);

    boolean deleteById(CodePK codePK);

    void delete(Code code);

    Code update(Code code);

    Integer updateByIdQuery(Code code);
    
}
