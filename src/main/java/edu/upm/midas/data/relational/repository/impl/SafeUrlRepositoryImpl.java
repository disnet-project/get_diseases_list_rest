package edu.upm.midas.data.relational.repository.impl;

import edu.upm.midas.data.relational.entities.addb.SafeUrl;
import edu.upm.midas.data.relational.entities.addb.Url;
import edu.upm.midas.data.relational.repository.AbstractDao;
import edu.upm.midas.data.relational.repository.SafeUrlRepository;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by gerardo on 27/10/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className UrlRepositoryImpl
 * @see
 */
@Repository("SafeUrlRepositoryDao")
public class SafeUrlRepositoryImpl extends AbstractDao<String, SafeUrl>
                                implements SafeUrlRepository {


    public SafeUrl findById(String urlId) {
        SafeUrl url = getByKey(urlId);
        return url;
    }

    @SuppressWarnings("unchecked")
    @Override
    public SafeUrl findByIdNative(String urlId) {
        SafeUrl url = null;
        List<SafeUrl> urlList = (List<SafeUrl>) getEntityManager()
                .createNamedQuery("SafeUrl.findByIdNative")
                .setParameter("urlId", urlId)
                .getResultList();
        if (CollectionUtils.isNotEmpty(urlList))
            url = urlList.get(0);
        return url;
    }

    @SuppressWarnings("unchecked")
    @Override
    public SafeUrl findByUrlNative(String url) {
        SafeUrl url_ = null;
        List<Object[]> urlList = (List<Object[]>) getEntityManager()
                .createNamedQuery("SafeUrl.findByUrlNative")
                .setParameter("url", url)
                .getResultList();
        if (CollectionUtils.isNotEmpty(urlList)) {
            url_ = createUrl(urlList.get(0));
        }
        return url_;
    }

    public SafeUrl createUrl(Object[] obj){
        SafeUrl url = new SafeUrl();
        url.setUrlId((String) obj[0]);
        url.setUrl((String) obj[1]);
        return url;
    }

    @Override
    public void persist(SafeUrl url) {
        super.persist(url);
    }

    @Override
    public int insertNative(String urlId, String url) {
        return getEntityManager()
                .createNamedQuery("SafeUrl.insertNative")
                .setParameter("urlId", urlId)
                .setParameter("url", url)
                .executeUpdate();
    }

    @Override
    public boolean deleteById(String urlId) {
        SafeUrl url = findById( urlId );
        if(url ==null)
            return false;
        super.delete(url);
        return true;
    }

    @Override
    public void delete(SafeUrl url) {
        super.delete(url);
    }

    @Override
    public SafeUrl update(SafeUrl url) {
        return super.update(url);
    }

    @Override
    public int updateByIdQuery(SafeUrl url) {
        return 0;
    }
}
