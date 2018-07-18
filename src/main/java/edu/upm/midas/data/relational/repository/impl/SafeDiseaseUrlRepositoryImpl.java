package edu.upm.midas.data.relational.repository.impl;
import edu.upm.midas.data.relational.entities.addb.DiseaseUrl;
import edu.upm.midas.data.relational.entities.addb.DiseaseUrlPK;
import edu.upm.midas.data.relational.entities.addb.SafeDiseaseUrl;
import edu.upm.midas.data.relational.entities.addb.SafeDiseaseUrlPK;
import edu.upm.midas.data.relational.repository.AbstractDao;
import edu.upm.midas.data.relational.repository.DiseaseUrlRepository;
import edu.upm.midas.data.relational.repository.SafeDiseaseUrlRepository;
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
@Repository("SafeDiseaseUrlRepositoryDao")
public class SafeDiseaseUrlRepositoryImpl extends AbstractDao<SafeDiseaseUrlPK, SafeDiseaseUrl>
                                        implements SafeDiseaseUrlRepository {

    @SuppressWarnings("unchecked")
    @Override
    public SafeDiseaseUrl findById(SafeDiseaseUrlPK diseaseUrlPK) {
        SafeDiseaseUrl diseaseUrl = getByKey(diseaseUrlPK);
        return diseaseUrl;
    }

    @SuppressWarnings("unchecked")
    @Override
    public SafeDiseaseUrl findByIdNative(SafeDiseaseUrlPK diseaseUrlPK) {
        SafeDiseaseUrl diseaseUrl = null;
        List<SafeDiseaseUrl> diseaseUrlList = (List<SafeDiseaseUrl>) getEntityManager()
                .createNamedQuery("SafeDiseaseUrl.findByIdNative")
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
    public List<SafeDiseaseUrl> findAllQuery() {
        return null;
    }

    @Override
    public void persist(SafeDiseaseUrl diseaseUrl) {
        super.persist(diseaseUrl);
    }

    @Override
    public int insertNative(String diseaseId, String urlId, String sourceId) {
        return getEntityManager()
                .createNamedQuery("SafeDiseaseUrl.insertNative")
                .setParameter("diseaseId", diseaseId)
                .setParameter("urlId", urlId)
                .setParameter("sourceId", sourceId)
                .executeUpdate();
    }

    @Override
    public boolean deleteById(SafeDiseaseUrlPK diseaseUrlPK) {
        SafeDiseaseUrl diseaseUrl = findById( diseaseUrlPK );
        if(diseaseUrl ==null)
            return false;
        super.delete(diseaseUrl);
        return true;
    }

    @Override
    public void delete(SafeDiseaseUrl diseaseUrl) {
        super.delete(diseaseUrl);
    }

    @Override
    public SafeDiseaseUrl update(SafeDiseaseUrl diseaseUrl) {
        return super.update(diseaseUrl);
    }

    @Override
    public Integer updateByIdQuery(SafeDiseaseUrl diseaseUrl) {
        return null;
    }
}
