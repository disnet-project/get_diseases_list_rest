package edu.upm.midas.model.jpa;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

/**
 * Created by gerardo on 29/01/2018.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className SafeUrl
 * @see
 */
@Entity
@Table(name = "safe_url", schema = "addb", catalog = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "SafeUrl.findAll", query = "SELECT u FROM SafeUrl u")
        , @NamedQuery(name = "SafeUrl.findById", query = "SELECT u FROM SafeUrl u WHERE u.urlId = :urlId")
        , @NamedQuery(name = "SafeUrl.findByUrl", query = "SELECT u FROM SafeUrl u WHERE u.url = :url")
})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "SafeUrl.findByIdNative",
                query = "SELECT u.url_id, u.url "
                        + "FROM safe_url u WHERE u.url_id = :urlId",
                resultSetMapping="UrlMapping"

        ),
        @NamedNativeQuery(
                name = "SafeUrl.findByIdNativeResultClass",
                query = "SELECT u.url_id, u.url "
                        + "FROM safe_url u WHERE u.url_id = :urlId",
                resultClass = Url.class
        ),
        @NamedNativeQuery(
                name = "SafeUrl.findByUrlNative",
                query = "SELECT u.url_id, u.url "
                        + "FROM safe_url u WHERE u.url COLLATE utf8_bin = :url"
        ),

        @NamedNativeQuery(
                name = "SafeUrl.insertNative",
                query = "INSERT IGNORE INTO safe_url (url_id, url) "
                        + "VALUES (:urlId, :url)"
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "SafeUrlMapping",
                entities = @EntityResult(
                        entityClass = SafeUrl.class,
                        fields = {
                                @FieldResult(name = "urlId", column = "url_id"),
                                @FieldResult(name = "url", column = "url")
                        }
                )
        )
})

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="urlId")
public class SafeUrl {
    private String urlId;
    private String url;
    private List<SafeDiseaseUrl> safeDiseaseUrlsByUrlId;

    @Id
    @Column(name = "url_id", nullable = false, length = 50)
    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }

    @Basic
    @Column(name = "url", nullable = false, length = 350)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SafeUrl safeUrl = (SafeUrl) o;
        return Objects.equals(urlId, safeUrl.urlId) &&
                Objects.equals(url, safeUrl.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(urlId, url);
    }

    @OneToMany(mappedBy = "safeUrlByUrlId")
    public List<SafeDiseaseUrl> getSafeDiseaseUrlsByUrlId() {
        return safeDiseaseUrlsByUrlId;
    }

    public void setSafeDiseaseUrlsByUrlId(List<SafeDiseaseUrl> safeDiseaseUrlsByUrlId) {
        this.safeDiseaseUrlsByUrlId = safeDiseaseUrlsByUrlId;
    }
}
