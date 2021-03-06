package edu.upm.midas.repository.jpa.impl;
import edu.upm.midas.model.jpa.Url;
import edu.upm.midas.repository.jpa.AbstractDao;
import edu.upm.midas.repository.jpa.UrlRepository;
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
@Repository("UrlRepositoryDao")
public class UrlRepositoryImpl extends AbstractDao<String, Url>
                                implements UrlRepository{


    public Url findById(String urlId) {
        Url url = getByKey(urlId);
        return url;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Url findByIdNative(String urlId) {
        Url url = null;
        List<Url> urlList = (List<Url>) getEntityManager()
                .createNamedQuery("Url.findByIdNative")
                .setParameter("urlId", urlId)
                .getResultList();
        if (CollectionUtils.isNotEmpty(urlList))
            url = urlList.get(0);
        return url;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Url findByUrlNative(String url) {
        Url url_ = null;
        List<Object[]> urlList = (List<Object[]>) getEntityManager()
                .createNamedQuery("Url.findByUrlNative")
                .setParameter("url", url)
                //.setMaxResults(1)
                .getResultList();
        if (CollectionUtils.isNotEmpty(urlList)) {
            url_ = createUrl(urlList.get(0));
        }
        return url_;
    }

    public Url createUrl(Object[] obj){
        Url url = new Url();
        url.setUrlId((String) obj[0]);
        url.setUrl((String) obj[1]);
        return url;
    }

    @Override
    public void persist(Url url) {
        super.persist(url);
    }

    @Override
    public int insertNative(String urlId, String url) {
        return getEntityManager()
                .createNamedQuery("Url.insertNative")
                .setParameter("urlId", urlId)
                .setParameter("url", url)
                .executeUpdate();
    }

    @Override
    public boolean deleteById(String urlId) {
        Url url = findById( urlId );
        if(url ==null)
            return false;
        super.delete(url);
        return true;
    }

    @Override
    public void delete(Url url) {
        super.delete(url);
    }

    @Override
    public Url update(Url url) {
        return super.update(url);
    }

    @Override
    public int updateByIdQuery(Url url) {
        return 0;
    }
}
