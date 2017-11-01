package edu.upm.midas.data.relational.repository.impl;
import edu.upm.midas.data.relational.entities.addb.Source;
import edu.upm.midas.data.relational.repository.AbstractDao;
import edu.upm.midas.data.relational.repository.SourceRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gerardo on 27/10/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className SourceRepositoryImpl
 * @see
 */
@Repository("SourceRepositoryDao")
public class SourceRepositoryImpl extends AbstractDao<String, Source>
                                    implements SourceRepository{

    @SuppressWarnings("unchecked")
    @Override
    public Source findById(String sourceId) {
        Source source = getByKey(sourceId);
        return source;    }

    @SuppressWarnings("unchecked")
    @Override
    public Source findByNameQuery(String name) {
        Source source = null;
        List<Source> listSource = (List<Source>) getEntityManager()
                .createNamedQuery("Source.findByName")
                .setParameter("name", name)
                .getResultList();
        if (CollectionUtils.isNotEmpty(listSource))
            source = listSource.get(0);
        return source;    }

    @SuppressWarnings("unchecked")
    @Override
    public String findIdByNameNative(String name) {
        String sourceId = (String) getEntityManager()
                .createNamedQuery("Source.findByNameNative")
                .setParameter("name", name)
                .setMaxResults(1)
                .getSingleResult();
        return sourceId;    }

    @SuppressWarnings("unchecked")
    @Override
    public Source findByIdNative(String sourceId) {
        Source source = null;
        List<Source> listSource = (List<Source>) getEntityManager()
                .createNamedQuery("Source.findByIdNative")
                .setParameter("sourceId", sourceId)
                .getResultList();
        if (CollectionUtils.isNotEmpty(listSource))
            source = listSource.get(0);
        return source;    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Source> findAllNative() {
        return (List<Source>) getEntityManager()
                .createNamedQuery("Source.findAllNative")
                .setMaxResults(0)
                .getResultList();    }

    @Override
    public void persist(Source source) {
        super.persist(source);
    }

    @Override
    public int insertNative(String sourceId, String name) {
        return getEntityManager()
                .createNamedQuery("Source.insertNative")
                .setParameter("sourceId", sourceId)
                .setParameter("name", name)
                .executeUpdate();    }

    @Override
    public boolean deleteById(String sourceId) {
        Source source = findById( sourceId );
        if(source ==null)
            return false;
        super.delete(source);
        return true;    }

    @Override
    public void delete(Source source) {
        super.delete(source);
    }

    @Override
    public Source update(Source source) {
        return super.update(source);    }

    @Override
    public int updateByIdQuery(Source source) {
        return 0;
    }
}
