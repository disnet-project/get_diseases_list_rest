package edu.upm.midas.data.relational.repository.impl;
import edu.upm.midas.data.relational.entities.addb.Album;
import edu.upm.midas.data.relational.entities.addb.AlbumPK;
import edu.upm.midas.data.relational.repository.AbstractDao;
import edu.upm.midas.data.relational.repository.AlbumRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by gerardo on 27/10/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className AlbumRepositoryImpl
 * @see
 */
@Repository("AlbumRepositoryDao")
public class AlbumRepositoryImpl extends AbstractDao<AlbumPK, Album>
                                    implements AlbumRepository{

    @SuppressWarnings("unchecked")
    @Override
    public Album findById(AlbumPK albumPK) {
        Album album = getByKey(albumPK);
        return album;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object[] findByIdNative(AlbumPK albumPK) {
        Object[] album = null;
        List<Object[]> albumList = (List<Object[]>) getEntityManager()
                .createNamedQuery("Album.findByIdNative")
                .setParameter("albumId", albumPK.getAlbumId())
                .setParameter("version", albumPK.getDate())
                .getResultList();
        if (CollectionUtils.isNotEmpty(albumList))
            album = albumList.get(0);
        return album;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Album findByVersionNative(Date version) {
        Album album = null;
        List<Album> albumList = (List<Album>) getEntityManager()
                .createNamedQuery("Album.findByVersionNative")
                .setParameter("version", version)
                .getResultList();
        if (CollectionUtils.isNotEmpty(albumList))
            album = albumList.get(0);
        return album;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object[] findByVersionAndSourceNative(Date version, String sourceName) {
        Object[] album = null;
        List<Object[]> albumList = (List<Object[]>) getEntityManager()
                .createNamedQuery("Album.findByVersionAndSourceNative")
                .setParameter("version", version)
                .setParameter("sourceName", sourceName)
                .getResultList();
        if (CollectionUtils.isNotEmpty(albumList))
            album = albumList.get(0);
        return album;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Date findLastVersionNative() {
        return (Date) getEntityManager()
                .createNamedQuery("Album.findLastVersionNative")
                .setMaxResults(1)
                .getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object[] findByLastVersionNative() {
        Object[] album = null;
        List<Object[]> albumList = (List<Object[]>) getEntityManager()
                .createNamedQuery("Album.findByLastVersionNative")
                .getResultList();
        if (CollectionUtils.isNotEmpty(albumList))
            album = albumList.get(0);
        return album;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object[] findFirstVersionNative() {
        Object[] album = null;
        List<Object[]> albumList = (List<Object[]>) getEntityManager()
                .createNamedQuery("Album.findFirstVersionNative")
                .getResultList();
        if (CollectionUtils.isNotEmpty(albumList))
            album = albumList.get(0);
        return album;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Album> findByVersionGraterThanNative(Date version) {
        List<Album> albums = null;
        List<Album> albumList = (List<Album>) getEntityManager()
                .createNamedQuery("Album.findByVersionGraterThanNative")
                .setParameter("version", version)
                .getResultList();
        if (CollectionUtils.isNotEmpty(albumList))
            albums = albumList;
        return albums;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Album> findByVersionSmallerThanNative(Date version) {
        List<Album> albums = null;
        List<Album> albumList = (List<Album>) getEntityManager()
                .createNamedQuery("Album.findByVersionSmallerThanNative")
                .setParameter("version", version)
                .getResultList();
        if (CollectionUtils.isNotEmpty(albumList))
            albums = albumList;
        return albums;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Album> findByNumberDiseasesNative(int number) {
        List<Album> albums = null;
        List<Album> albumList = (List<Album>) getEntityManager()
                .createNamedQuery("Album.findByNumberDiseasesNative")
                .setParameter("numberDiseases", number)
                .getResultList();
        if (CollectionUtils.isNotEmpty(albumList))
            albums = albumList;
        return albums;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Album> findByNumberDiseasesGreaterThanNative(int number) {
        List<Album> albums = null;
        List<Album> albumList = (List<Album>) getEntityManager()
                .createNamedQuery("Album.findByNumberDiseasesGreaterThanNative")
                .setParameter("numberDiseases", number)
                .getResultList();
        if (CollectionUtils.isNotEmpty(albumList))
            albums = albumList;
        return albums;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Album> findByNumberDiseasesSmallerThanNative(int number) {
        List<Album> albums = null;
        List<Album> albumList = (List<Album>) getEntityManager()
                .createNamedQuery("Album.findByNumberDiseasesSmallerThanNative")
                .setParameter("numberDiseases", number)
                .getResultList();
        if (CollectionUtils.isNotEmpty(albumList))
            albums = albumList;
        return albums;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findLinksOnWikipediaById(String albumId, Date version) {
        List<Object[]> diseases = null;
        List<Object[]> diseaseList = (List<Object[]>) getEntityManager()
                .createNamedQuery("Album.findLinksOnWikipediaById")
                .setParameter("albumId", albumId)
                .setParameter("version", version)
                //.setMaxResults(100)
                .getResultList();
        if (CollectionUtils.isNotEmpty(diseaseList))
            diseases = diseaseList;
        return diseases;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findLinksByIdAndSourceNameNative(String albumId, Date version, String sourceName) {
        List<Object[]> diseases = null;
        List<Object[]> diseaseList = (List<Object[]>) getEntityManager()
                .createNamedQuery("Album.findLinksByIdAndSourceNameNative")
                .setParameter("albumId", albumId)
                .setParameter("version", version)
                .setParameter("sourceName", sourceName)
                //.setMaxResults(100)
                .getResultList();
        if (CollectionUtils.isNotEmpty(diseaseList))
            diseases = diseaseList;
        return diseases;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findSafeDiseaseListNative(String sourceName) {
        List<Object[]> diseases = null;
        List<Object[]> diseaseList = (List<Object[]>) getEntityManager()
                .createNamedQuery("Album.findSafeDiseaseListNative")
                .setParameter("sourceName", sourceName)
                //.setMaxResults(100)
                .getResultList();
        if (CollectionUtils.isNotEmpty(diseaseList))
            diseases = diseaseList;
        return diseases;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> getMergeSafeDiseaseListAndCurrentDiseaseListByAlbumIdAndVersionAndSourceNameNative(String albumId, Date version, String source) {
        List<Object[]> diseases = null;
        List<Object[]> diseaseList = (List<Object[]>) getEntityManager()
                .createNamedQuery("Album.getMergeSafeDiseaseListAndCurrentDiseaseListByAlbumIdAndVersionAndSourceNameNative")
                .setParameter("albumId", albumId)
                .setParameter("version", version)
                .setParameter("sourceName", source)
                //.setMaxResults(100)
                .getResultList();
        if (CollectionUtils.isNotEmpty(diseaseList))
            diseases = diseaseList;
        return diseases;
    }


    @SuppressWarnings("unchecked")
    @Override
    public int maxSizeNative() {
        return (int) getEntityManager()
                .createNamedQuery("Album.maxSizeNative")
                .setMaxResults(1)
                .getSingleResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Album> findByMaxSizeNative() {
        List<Album> albums = null;
        List<Album> albumList = (List<Album>) getEntityManager()
                .createNamedQuery("Album.findByMaxSizeNative")
                .getResultList();
        if (CollectionUtils.isNotEmpty(albumList))
            albums = albumList;
        return albums;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Object[]> findAllNative() {
        return (List<Object[]>) getEntityManager()
                .createNamedQuery("Album.findAllNative")
                .setMaxResults(0)
                .getResultList();
    }

    @Override
    public void persist(Album album) {
        super.persist(album);
    }

    @Override
    public int insertNative(String albumId, Date version, int number) {
        return getEntityManager()
                .createNamedQuery("Album.insertNative")
                .setParameter("albumId", albumId)
                .setParameter("version", version)
                .setParameter("numberDiseases", number)
                .executeUpdate();
    }

    @Override
    public int insertByAlbumIdAndVersionNative(String albumId, Date version) {
        return getEntityManager()
                .createNamedQuery("Album.insertByAlbumIdAndVersionNative")
                .setParameter("albumId", albumId)
                .setParameter("version", version)
                .executeUpdate();
    }

    @Override
    public boolean deleteById(AlbumPK albumPK) {
        Album album = findById( albumPK );
        if(album ==null)
            return false;
        super.delete(album);
        return true;
    }

    @Override
    public void delete(Album album) {
        super.delete(album);
    }

    @Override
    public Album update(Album album) {
        return super.update(album);
    }

    @Override
    public int updateNumberDiseasesByIdNative(String albumId, Date version) {
        return getEntityManager()
                .createNamedQuery("Album.updateNumberDiseasesByIdNative")
                .setParameter("albumId", albumId)
                .setParameter("version", version)
                .executeUpdate();
    }

    @Override
    public Integer updateByIdQuery(Album album) {
        return null;
    }
}
