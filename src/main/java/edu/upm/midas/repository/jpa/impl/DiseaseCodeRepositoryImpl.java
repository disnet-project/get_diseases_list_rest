package edu.upm.midas.repository.jpa.impl;
import edu.upm.midas.model.jpa.DiseaseCode;
import edu.upm.midas.model.jpa.DiseaseCodePK;
import edu.upm.midas.repository.jpa.AbstractDao;
import edu.upm.midas.repository.jpa.DiseaseCodeRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gerardo on 27/10/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className DiseaseCodeRepositoryImpl
 * @see
 */
@Repository("DiseaseCodeRepositoryDao")
public class DiseaseCodeRepositoryImpl extends AbstractDao<DiseaseCodePK, DiseaseCode>
                                        implements DiseaseCodeRepository{

    @SuppressWarnings("unchecked")
    @Override
    public DiseaseCode findById(DiseaseCodePK diseaseCodePK) {
        DiseaseCode diseaseCode = getByKey(diseaseCodePK);
        return diseaseCode;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object[] findByIdNative(DiseaseCodePK diseaseCodePK) {
        Object[] diseaseCode = null;
        List<Object[]> diseaseCodeList = (List<Object[]>) getEntityManager()
                .createNamedQuery("DiseaseCode.findByIdNative")
                .setParameter("diseaseId", diseaseCodePK.getDiseaseId())
                .setParameter("codeId", diseaseCodePK.getCodeId())
                .setParameter("resourceId", diseaseCodePK.getResourceId())
                .getResultList();
        if (CollectionUtils.isNotEmpty(diseaseCodeList))
            diseaseCode = diseaseCodeList.get(0);
        return diseaseCode;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DiseaseCode> findAllQuery() {
        return null;
    }

    @Override
    public void persist(DiseaseCode diseaseCode) {
        super.persist(diseaseCode);
    }

    @Override
    public int insertNative(String diseaseId, String codeId, Integer resourceId) {
        return getEntityManager()
                .createNamedQuery("DiseaseCode.insertNative")
                .setParameter("diseaseId", diseaseId)
                .setParameter("codeId", codeId)
                .setParameter("resourceId", resourceId)
                .executeUpdate();
    }

    @Override
    public boolean deleteById(DiseaseCodePK diseaseCodePK) {
        DiseaseCode diseaseCode = findById( diseaseCodePK );
        if(diseaseCode ==null)
            return false;
        super.delete(diseaseCode);
        return true;
    }

    @Override
    public void delete(DiseaseCode diseaseCode) {
        super.delete(diseaseCode);
    }

    @Override
    public DiseaseCode update(DiseaseCode diseaseCode) {
        return super.update(diseaseCode);
    }

    @Override
    public Integer updateByIdQuery(DiseaseCode diseaseCode) {
        return null;
    }
}
