package edu.upm.midas.repository.jpa.impl;

import edu.upm.midas.common.util.Common;
import edu.upm.midas.model.jpa.SafeDisease;
import edu.upm.midas.repository.jpa.AbstractDao;
import edu.upm.midas.repository.jpa.SafeDiseaseRepository;
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
@Repository("SafeDiseaseRepositoryDao")
public class SafeDiseaseRepositoryImpl extends AbstractDao<String, SafeDisease>
                                    implements SafeDiseaseRepository {

    @Autowired
    private Common common;

    @SuppressWarnings("unchecked")
    @Override
    public SafeDisease findById(String diseaseId) {
        SafeDisease disease = getByKey(diseaseId);
        return disease;
    }

    @SuppressWarnings("unchecked")
    @Override
    public SafeDisease findByIdNative(String diseaseId) {
        SafeDisease disease = null;
        List<SafeDisease> diseaseList = (List<SafeDisease>) getEntityManager()
                .createNamedQuery("SafeDisease.findByIdNative")
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
                .createNamedQuery("SafeDisease.findByNameNative")
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
                .createNamedQuery("SafeDisease.findLastIdNative")
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
    public List<Object[]> findAllDiseasesBySourceName(String sourceName) {
        List<Object[]> diseases = null;
        List<Object[]> diseaseList = (List<Object[]>) getEntityManager()
                .createNamedQuery("SafeDisease.findAllDiseasesBySource")
                .setParameter("source", sourceName)
                //.setMaxResults(100)
                .getResultList();
        if (CollectionUtils.isNotEmpty(diseaseList))
            diseases = diseaseList;
        return diseases;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<SafeDisease> findAllQuery() {
        return (List<SafeDisease>) getEntityManager()
                .createNamedQuery("SafeDisease.findAll")
                .setMaxResults(0)
                .getResultList();
    }

    @Override
    public void persist(SafeDisease disease) {
        super.persist(disease);
    }

    @Override
    public int insertNative(String diseaseId, String name) {
        return getEntityManager()
                .createNamedQuery("SafeDisease.insertNative")
                .setParameter("diseaseId", diseaseId)
                .setParameter("name", name)
                .executeUpdate();
    }

    @Override
    public boolean deleteById(String diseaseId) {
        SafeDisease disease = findById( diseaseId );
        if(disease ==null)
            return false;
        super.delete(disease);
        return true;
    }

    @Override
    public void delete(SafeDisease disease) {
        super.delete(disease);
    }

    @Override
    public SafeDisease update(SafeDisease disease) {
        return super.update(disease);
    }

    @Override
    public int updateNative(String diseaseId, String name) {
        return 0;
    }

    @Override
    public Integer updateByIdQuery(SafeDisease disease) {
        return null;
    }
}
