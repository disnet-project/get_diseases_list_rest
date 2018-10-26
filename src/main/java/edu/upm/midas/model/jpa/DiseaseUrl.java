package edu.upm.midas.model.jpa;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

/**
 * Created by gerardo on 26/10/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className DiseaseUrl
 * @see
 */
@Entity
@Table(name = "disease_url", schema = "addb", catalog = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "DiseaseUrl.findAll", query = "SELECT d FROM DiseaseUrl d")
        , @NamedQuery(name = "DiseaseUrl.findById", query = "SELECT d FROM DiseaseUrl d WHERE d.diseaseId = :diseaseId AND d.diseaseId = :diseaseId AND d.urlId = :urlId AND d.sourceId = :sourceId")
        , @NamedQuery(name = "DiseaseUrl.findByDiseaseId", query = "SELECT d FROM DiseaseUrl d WHERE d.diseaseId = :diseaseId")
        , @NamedQuery(name = "DiseaseUrl.findByUrlId", query = "SELECT d FROM DiseaseUrl d WHERE d.urlId = :urlId")
        , @NamedQuery(name = "DiseaseUrl.findBySourceId", query = "SELECT d FROM DiseaseUrl d WHERE d.sourceId = :sourceId")
})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "DiseaseUrl.findByIdNative",
                query = "SELECT d.disease_id, d.url_id, d.source_id "
                        + "FROM disease_url d WHERE d.disease_id = :diseaseId AND d.url_id = :urlId AND d.source_id = :sourceId "

        ),


        @NamedNativeQuery(
                name = "DiseaseUrl.insertNative",
                query = "INSERT INTO disease_url (disease_id, url_id, source_id) "
                        + "VALUES (:diseaseId, :urlId, :sourceId)"
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "DiseaseUrlMapping",
                entities = @EntityResult(
                        entityClass = DiseaseUrl.class,
                        fields = {
                                @FieldResult(name = "diseaseId", column = "disease_id"),
                                @FieldResult(name = "urlId", column = "url_id"),
                                @FieldResult(name = "sourceId", column = "source_id")
                        }
                )
        )
})

@IdClass(DiseaseUrlPK.class)
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="DiseaseUrlPK")
public class DiseaseUrl {
    private String diseaseId;
    private String urlId;
    private String sourceId;
    private Disease diseaseByDiseaseId;
    private Url urlByUrlId;
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
    @Column(name = "url_id", nullable = false, length = 15)
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
        DiseaseUrl that = (DiseaseUrl) o;
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
    public Disease getDiseaseByDiseaseId() {
        return diseaseByDiseaseId;
    }

    public void setDiseaseByDiseaseId(Disease diseaseByDiseaseId) {
        this.diseaseByDiseaseId = diseaseByDiseaseId;
    }

    @ManyToOne
    @JoinColumn(name = "url_id", referencedColumnName = "url_id", nullable = false, insertable = false, updatable = false)
    public Url getUrlByUrlId() {
        return urlByUrlId;
    }

    public void setUrlByUrlId(Url urlByUrlId) {
        this.urlByUrlId = urlByUrlId;
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
