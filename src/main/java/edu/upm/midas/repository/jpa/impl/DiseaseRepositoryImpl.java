package edu.upm.midas.repository.jpa.impl;
import edu.upm.midas.common.util.Common;
import edu.upm.midas.model.jpa.Disease;
import edu.upm.midas.repository.jpa.AbstractDao;
import edu.upm.midas.repository.jpa.DiseaseRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
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


    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> getAllDiseasesFromDiseaseSafeListAndLastDiseaseAlbumBySource(Date current_version, Date penultimate_version, String source) {
        List<Object[]> diseases = null;
        List<Object[]> diseaseList = (List<Object[]>) getEntityManager()
                .createNamedQuery("Disease.getAllDiseasesFromDiseaseSafeListAndLastDiseaseAlbumBySource")
                .setParameter("current_version", current_version)
                .setParameter("penultimate_version", penultimate_version)
                .setParameter("source", source)
                //.setMaxResults(100)
                .getResultList();
        if (CollectionUtils.isNotEmpty(diseaseList))
            diseases = diseaseList;
        return diseases;
    }

    @Override
    public void persist(Disease disease) {
        super.persist(disease);
    }

    @Override
    public int insertNative(String diseaseId, String name, String sourceId, boolean partly_irrelevant, boolean totally_irrelevant) {
        return getEntityManager()
                .createNamedQuery("Disease.insertNative")
                .setParameter("diseaseId", diseaseId)
                .setParameter("name", name)
                .setParameter("sourceId", sourceId)
                .setParameter("partly_irrelevant", partly_irrelevant)
                .setParameter("totally_irrelevant", totally_irrelevant)
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
