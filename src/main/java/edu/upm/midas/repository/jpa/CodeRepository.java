package edu.upm.midas.repository.jpa;

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
public interface CodeRepository {

    Code findById(CodePK codePK);

    Object[] findByIdNative(CodePK codePK);
    
    List<Code> findAllQuery();

    void persist(Code code);

    int insertNative(String codeId, Integer resourceId);

    boolean deleteById(CodePK codePK);

    void delete(Code code);

    Code update(Code code);

    Integer updateByIdQuery(Code code);
    
}
