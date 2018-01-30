package edu.upm.midas.data.relational.entities.addb;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Created by gerardo on 29/01/2018.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className SafeDiseaseUrl
 * @see
 */
@Entity
@Table(name = "safe_disease_url", schema = "addb", catalog = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "SafeDiseaseUrl.findAll", query = "SELECT d FROM SafeDiseaseUrl d")
        , @NamedQuery(name = "SafeDiseaseUrl.findById", query = "SELECT d FROM SafeDiseaseUrl d WHERE d.diseaseId = :diseaseId AND d.diseaseId = :diseaseId AND d.urlId = :urlId AND d.sourceId = :sourceId")
        , @NamedQuery(name = "SafeDiseaseUrl.findByDiseaseId", query = "SELECT d FROM SafeDiseaseUrl d WHERE d.diseaseId = :diseaseId")
        , @NamedQuery(name = "SafeDiseaseUrl.findByUrlId", query = "SELECT d FROM SafeDiseaseUrl d WHERE d.urlId = :urlId")
        , @NamedQuery(name = "SafeDiseaseUrl.findBySourceId", query = "SELECT d FROM SafeDiseaseUrl d WHERE d.sourceId = :sourceId")
})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "SafeDiseaseUrl.findByIdNative",
                query = "SELECT d.disease_id, d.url_id, d.source_id "
                        + "FROM safe_disease_url d WHERE d.disease_id = :diseaseId AND d.url_id = :urlId AND d.source_id = :sourceId "

        ),


        @NamedNativeQuery(
                name = "SafeDiseaseUrl.insertNative",
                query = "INSERT INTO safe_disease_url (disease_id, url_id, source_id) "
                        + "VALUES (:diseaseId, :urlId, :sourceId)"
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "SafeDiseaseUrlMapping",
                entities = @EntityResult(
                        entityClass = SafeDiseaseUrl.class,
                        fields = {
                                @FieldResult(name = "diseaseId", column = "disease_id"),
                                @FieldResult(name = "urlId", column = "url_id"),
                                @FieldResult(name = "sourceId", column = "source_id")
                        }
                )
        )
})
@IdClass(SafeDiseaseUrlPK.class)
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="SafeDiseaseUrlPK")

public class SafeDiseaseUrl {
    private String diseaseId;
    private String urlId;
    private String sourceId;
    private SafeDisease safeDiseaseByDiseaseId;
    private SafeUrl safeUrlByUrlId;
    private Source sourceBySourceId;

    @Id
    @Column(name = "disease_id", nullable = false, length = 150)
    public String getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(String diseaseId) {
        this.diseaseId = diseaseId;
    }

    @Id
    @Column(name = "url_id", nullable = false, length = 50)
    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }

    @Id
    @Column(name = "source_id", nullable = false, length = 10)
    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SafeDiseaseUrl that = (SafeDiseaseUrl) o;
        return Objects.equals(diseaseId, that.diseaseId) &&
                Objects.equals(urlId, that.urlId) &&
                Objects.equals(sourceId, that.sourceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diseaseId, urlId, sourceId);
    }

    @ManyToOne
    @JoinColumn(name = "disease_id", referencedColumnName = "disease_id", nullable = false, insertable = false, updatable = false)
    public SafeDisease getSafeDiseaseByDiseaseId() {
        return safeDiseaseByDiseaseId;
    }

    public void setSafeDiseaseByDiseaseId(SafeDisease safeDiseaseByDiseaseId) {
        this.safeDiseaseByDiseaseId = safeDiseaseByDiseaseId;
    }

    @ManyToOne
    @JoinColumn(name = "url_id", referencedColumnName = "url_id", nullable = false, insertable = false, updatable = false)
    public SafeUrl getSafeUrlByUrlId() {
        return safeUrlByUrlId;
    }

    public void setSafeUrlByUrlId(SafeUrl safeUrlByUrlId) {
        this.safeUrlByUrlId = safeUrlByUrlId;
    }

    @ManyToOne
    @JoinColumn(name = "source_id", referencedColumnName = "source_id", nullable = false, insertable = false, updatable = false)
    public Source getSourceBySourceId() {
        return sourceBySourceId;
    }

    public void setSourceBySourceId(Source sourceBySourceId) {
        this.sourceBySourceId = sourceBySourceId;
    }
}
