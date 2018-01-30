package edu.upm.midas.data.relational.repository.impl;
import edu.upm.midas.common.util.Common;
import edu.upm.midas.data.relational.entities.addb.Disease;
import edu.upm.midas.data.relational.repository.AbstractDao;
import edu.upm.midas.data.relational.repository.DiseaseRepository;
import edu.upm.midas.data.relational.repository.DiseaseUrlRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gerardo on 27/10/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className DiseaseRepositoryImpl
 * @see
 */
@Repository("DiseaseRepositoryDao")
public class DiseaseRepositoryImpl extends AbstractDao<String, Disease>
                                    implements DiseaseRepository {

    @Autowired
    private Common common;

    @SuppressWarnings("unchecked")
    @Override
    public Disease findById(String diseaseId) {
        Disease disease = getByKey(diseaseId);
        return disease;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Disease findByIdNative(String diseaseId) {
        Disease disease = null;
        List<Disease> diseaseList = (List<Disease>) getEntityManager()
                .createNamedQuery("Disease.findByIdNative")
                .setParameter("diseaseId", diseaseId)
                .getResultList();
        if (CollectionUtils.isNotEmpty(diseaseList))
            disease = diseaseList.get(0);
        return disease;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object[] findByNameNative(String name) {
        Object[] disease = null;System.out.println(name);
        List<Object[]> diseaseList = (List<Object[]>) getEntityManager()
                .createNamedQuery("Disease.findByNameNative")
                .setParameter("name", name)
                .setMaxResults(1)
                .getResultList();
        if (CollectionUtils.isNotEmpty(diseaseList)) {
            //System.out.println("Disease: " + name + ", " + diseaseList.size() + " | empty?: " + diseaseList.isEmpty());
            //System.out.println( "==> " + diseaseList.get(0)[0]);
            disease = diseaseList.get(0);
        }else{
            //System.out.println("QUE PASA");
        }
        return disease;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String findLastIdNative() {
        String id = "";
        Object[] idObject;
        List<Object[]> res =  (List<Object[]>) getEntityManager()
                .createNamedQuery("Disease.findLastIdNative")
                .setMaxResults(1)
                .getResultList();

        if (CollectionUtils.isNotEmpty(res)) {
            //System.out.println("RESS: "+res.get(0));
            id = String.valueOf(res.get(0));
            //id = idObject[0].toString();
        }

        return id;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Disease> findAllQuery() {
        return (List<Disease>) getEntityManager()
                .createNamedQuery("Disease.findAll")
                .setMaxResults(0)
                .getResultList();
    }

    @Override
    public void persist(Disease disease) {
        super.persist(disease);
    }

    @Override
    public int insertNative(String diseaseId, String name) {
        return getEntityManager()
                .createNamedQuery("Disease.insertNative")
                .setParameter("diseaseId", diseaseId)
                .setParameter("name", name)
                .executeUpdate();
    }

    @Override
    public boolean deleteById(String diseaseId) {
        Disease disease = findById( diseaseId );
        if(disease ==null)
            return false;
        super.delete(disease);
        return true;
    }

    @Override
    public void delete(Disease disease) {
        super.delete(disease);
    }

    @Override
    public Disease update(Disease disease) {
        return super.update(disease);
    }

    @Override
    public int updateNative(String diseaseId, String name) {
        return 0;
    }

    @Override
    public Integer updateByIdQuery(Disease disease) {
        return null;
    }
}
