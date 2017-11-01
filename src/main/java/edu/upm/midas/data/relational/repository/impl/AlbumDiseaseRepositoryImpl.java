package edu.upm.midas.data.relational.repository.impl;
import edu.upm.midas.data.relational.entities.addb.AlbumDisease;
import edu.upm.midas.data.relational.entities.addb.AlbumDiseasePK;
import edu.upm.midas.data.relational.repository.AbstractDao;
import edu.upm.midas.data.relational.repository.AlbumDiseaseRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gerardo on 27/10/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className AlbumDiseaseRepositoryImpl
 * @see
 */
@Repository("AlbumDiseaseRepositoryDao")
public class AlbumDiseaseRepositoryImpl extends AbstractDao<AlbumDiseasePK, AlbumDisease>
                                        implements AlbumDiseaseRepository{

    @SuppressWarnings("unchecked")
    @Override
    public AlbumDisease findById(AlbumDiseasePK albumDiseasePK) {
        AlbumDisease albumDisease = getByKey(albumDiseasePK);
        return albumDisease;
    }

    @SuppressWarnings("unchecked")
    @Override
    public AlbumDisease findByIdNative(AlbumDiseasePK albumDiseasePK) {
        AlbumDisease diseaseCode = null;
        List<AlbumDisease> diseaseCodeList = (List<AlbumDisease>) getEntityManager()
                .createNamedQuery("AlbumDisease.findByIdNative")
                .setParameter("albumId", albumDiseasePK.getAlbumId())
                .setParameter("version", albumDiseasePK.getDate())
                .setParameter("diseaseId", albumDiseasePK.getDiseaseId())
                .getResultList();
        if (CollectionUtils.isNotEmpty(diseaseCodeList))
            diseaseCode = diseaseCodeList.get(0);
        return diseaseCode;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<AlbumDisease> findAllQuery() {
        return (List<AlbumDisease>) getEntityManager()
                .createNamedQuery("AlbumDisease.findAll")
                .setMaxResults(0)
                .getResultList();
    }

    @Override
    public void persist(AlbumDisease albumDisease) {
        super.persist(albumDisease);
    }

    @Override
    public int insertNative(String albumId, String version, String diseaseId) {
        return getEntityManager()
                .createNamedQuery("AlbumDisease.insertNative")
                .setParameter("albumId", albumId)
                .setParameter("version", version)
                .setParameter("diseaseId", diseaseId)
                .executeUpdate();
    }

    @Override
    public boolean deleteById(AlbumDiseasePK albumDiseasePK) {
        AlbumDisease albumDisease = findById( albumDiseasePK );
        if(albumDisease ==null)
            return false;
        super.delete(albumDisease);
        return true;
    }

    @Override
    public void delete(AlbumDisease albumDisease) {
        super.delete(albumDisease);
    }

    @Override
    public AlbumDisease update(AlbumDisease albumDisease) {
        return super.update(albumDisease);
    }

    @Override
    public Integer updateByIdQuery(AlbumDisease albumDisease) {
        return null;
    }
}
