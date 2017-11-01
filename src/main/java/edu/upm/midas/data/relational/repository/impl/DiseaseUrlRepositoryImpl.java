package edu.upm.midas.data.relational.repository.impl;
import edu.upm.midas.data.relational.entities.addb.DiseaseUrl;
import edu.upm.midas.data.relational.entities.addb.DiseaseUrlPK;
import edu.upm.midas.data.relational.repository.AbstractDao;
import edu.upm.midas.data.relational.repository.DiseaseUrlRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gerardo on 27/10/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className DiseaseUrlRepositoryImpl
 * @see
 */
@Repository("DiseaseUrlRepositoryDao")
public class DiseaseUrlRepositoryImpl extends AbstractDao<DiseaseUrlPK, DiseaseUrl>
                                        implements DiseaseUrlRepository{

    @SuppressWarnings("unchecked")
    @Override
    public DiseaseUrl findById(DiseaseUrlPK diseaseUrlPK) {
        DiseaseUrl diseaseUrl = getByKey(diseaseUrlPK);
        return diseaseUrl;
    }

    @SuppressWarnings("unchecked")
    @Override
    public DiseaseUrl findByIdNative(DiseaseUrlPK diseaseUrlPK) {
        DiseaseUrl diseaseUrl = null;
        List<DiseaseUrl> diseaseUrlList = (List<DiseaseUrl>) getEntityManager()
                .createNamedQuery("DiseaseUrl.findByIdNative")
                .setParameter("diseaseId", diseaseUrlPK.getDiseaseId())
                .setParameter("urlId", diseaseUrlPK.getUrlId())
                .setParameter("sourceId", diseaseUrlPK.getSourceId())
                .getResultList();
        if (CollectionUtils.isNotEmpty(diseaseUrlList))
            diseaseUrl = diseaseUrlList.get(0);
        return diseaseUrl;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<DiseaseUrl> findAllQuery() {
        return null;
    }

    @Override
    public void persist(DiseaseUrl diseaseUrl) {
        super.persist(diseaseUrl);
    }

    @Override
    public int insertNative(String diseaseId, String urlId, String sourceId) {
        return getEntityManager()
                .createNamedQuery("DiseaseUrl.insertNative")
                .setParameter("diseaseId", diseaseId)
                .setParameter("urlId", urlId)
                .setParameter("sourceId", sourceId)
                .executeUpdate();
    }

    @Override
    public boolean deleteById(DiseaseUrlPK diseaseUrlPK) {
        DiseaseUrl diseaseUrl = findById( diseaseUrlPK );
        if(diseaseUrl ==null)
            return false;
        super.delete(diseaseUrl);
        return true;
    }

    @Override
    public void delete(DiseaseUrl diseaseUrl) {
        super.delete(diseaseUrl);
    }

    @Override
    public DiseaseUrl update(DiseaseUrl diseaseUrl) {
        return super.update(diseaseUrl);
    }

    @Override
    public Integer updateByIdQuery(DiseaseUrl diseaseUrl) {
        return null;
    }
}
