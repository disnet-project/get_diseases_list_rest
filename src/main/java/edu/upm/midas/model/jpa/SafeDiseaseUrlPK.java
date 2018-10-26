package edu.upm.midas.model.jpa;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by gerardo on 29/01/2018.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className SafeDiseaseUrlPK
 * @see
 */
public class SafeDiseaseUrlPK implements Serializable {
    private String diseaseId;
    private String urlId;
    private String sourceId;

    @Column(name = "disease_id", nullable = false, length = 150)
    @Id
    public String getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(String diseaseId) {
        this.diseaseId = diseaseId;
    }

    @Column(name = "url_id", nullable = false, length = 50)
    @Id
    public String getUrlId() {
        return urlId;
    }

    public void setUrlId(String urlId) {
        this.urlId = urlId;
    }

    @Column(name = "source_id", nullable = false, length = 10)
    @Id
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
        SafeDiseaseUrlPK that = (SafeDiseaseUrlPK) o;
        return Objects.equals(diseaseId, that.diseaseId) &&
                Objects.equals(urlId, that.urlId) &&
                Objects.equals(sourceId, that.sourceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diseaseId, urlId, sourceId);
    }
}
