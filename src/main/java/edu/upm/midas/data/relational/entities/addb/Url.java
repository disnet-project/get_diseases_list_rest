package edu.upm.midas.data.relational.entities.addb;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Objects;

/**
 * Created by gerardo on 26/10/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className Url
 * @see
 */
@Entity
@Table(name = "url", catalog = "addb", schema = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Url.findAll", query = "SELECT u FROM Url u")
        , @NamedQuery(name = "Url.findById", query = "SELECT u FROM Url u WHERE u.urlId = :urlId")
        , @NamedQuery(name = "Url.findByUrl", query = "SELECT u FROM Url u WHERE u.url = :url")
})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Url.findByIdNative",
                query = "SELECT u.url_id, u.url "
                        + "FROM url u WHERE u.url_id = :urlId",
                resultSetMapping="UrlMapping"

        ),
        @NamedNativeQuery(
                name = "Url.findByIdNativeResultClass",
                query = "SELECT u.url_id, u.url "
                        + "FROM url u WHERE u.url_id = :urlId",
                resultClass = Url.class
        ),
        @NamedNativeQuery(
                name = "Url.findByUrlNativeResultClass",
                query = "SELECT u.url_id, u.url "
                        + "FROM url u WHERE u.url COLLATE utf8_bin = :url",
                resultClass = Url.class
        ),

        @NamedNativeQuery(
                name = "Url.insertNative",
                query = "INSERT INTO url (url_id, url) "
                        + "VALUES (:urlId, :url)"
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "UrlMapping",
                entities = @EntityResult(
                        entityClass = Url.class,
                        fields = {
                                @FieldResult(name = "urlId", column = "url_id"),
                                @FieldResult(name = "url", column = "url")
                        }
                )
        )
})

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="urlId")
public class Url {
    private String urlId;
    private String url;
    private List<DiseaseUrl> diseaseUrlsByUrlId;

    @Id
    @Column(name = "url_id", nullable = false, length = 15)
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
        Url url1 = (Url) o;
        return Objects.equals(urlId, url1.urlId) &&
                Objects.equals(url, url1.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(urlId, url);
    }

    @OneToMany(mappedBy = "urlByUrlId")
    public List<DiseaseUrl> getDiseaseUrlsByUrlId() {
        return diseaseUrlsByUrlId;
    }

    public void setDiseaseUrlsByUrlId(List<DiseaseUrl> diseaseUrlsByUrlId) {
        this.diseaseUrlsByUrlId = diseaseUrlsByUrlId;
    }
}
