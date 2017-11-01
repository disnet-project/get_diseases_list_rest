package edu.upm.midas.data.relational.repository.impl;
import edu.upm.midas.data.relational.entities.addb.Code;
import edu.upm.midas.data.relational.entities.addb.CodePK;
import edu.upm.midas.data.relational.repository.AbstractDao;
import edu.upm.midas.data.relational.repository.CodeRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gerardo on 27/10/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className CodeRepositoryImpl
 * @see
 */
@Repository("CodeRepositoryDao")
public class CodeRepositoryImpl extends AbstractDao<CodePK, Code>
                                implements CodeRepository{

    @SuppressWarnings("unchecked")
    @Override
    public Code findById(CodePK codePK) {
        Code code = getByKey(codePK);
        return code;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object[] findByIdNative(CodePK codePK) {
        Object[] code = null;
        List<Object[]> codeList = (List<Object[]>) getEntityManager()
                .createNamedQuery("Code.findByIdNative")
                .setParameter("codeId", codePK.getCodeId())
                .setParameter("resourceId", codePK.getResourceId())
                .getResultList();
        if (CollectionUtils.isNotEmpty(codeList))
            code = codeList.get(0);
        return code;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Code> findAllQuery() {
        return (List<Code>) getEntityManager()
                .createNamedQuery("Code.findAll")
                .setMaxResults(0)
                .getResultList();
    }

    @Override
    public void persist(Code code) {
        super.persist(code);
    }

    @Override
    public int insertNative(String codeId, Integer resourceId) {
        return getEntityManager()
                .createNamedQuery("Code.insertNative")
                .setParameter("codeId", codeId)
                .setParameter("resourceId", resourceId)
                .executeUpdate();
    }

    @Override
    public boolean deleteById(CodePK codePK) {
        Code code = findById( codePK );
        if(code ==null)
            return false;
        super.delete(code);
        return true;
    }

    @Override
    public void delete(Code code) {
        super.delete(code);
    }

    @Override
    public Code update(Code code) {
        return super.update(code);
    }

    @Override
    public Integer updateByIdQuery(Code code) {
        return null;
    }
}
