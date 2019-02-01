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
 * @className SafeDisease
 * @see
 */
@Entity
@Table(name = "safe_disease", schema = "addb", catalog = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "SafeDisease.findAll", query = "SELECT d FROM SafeDisease d")
        , @NamedQuery(name = "SafeDisease.findById", query = "SELECT d FROM SafeDisease d WHERE d.diseaseId = :diseaseId")
        , @NamedQuery(name = "SafeDisease.findByName", query = "SELECT d FROM SafeDisease d WHERE d.name = :name")
})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "SafeDisease.findByIdNative",
                query = "SELECT d.disease_id, d.name "
                        + "FROM safe_disease d WHERE d.disease_id COLLATE utf8_bin = :diseaseId"

        ),
        @NamedNativeQuery(
                name = "SafeDisease.findByNameNative",
                query = "SELECT d.disease_id, d.name "
                        + "FROM safe_disease d WHERE d.name COLLATE utf8_bin = :name"
        ),
        @NamedNativeQuery(
                name = "SafeDisease.findLastIdNative",
                query = "SELECT d.disease_id " +
                        "FROM safe_disease d " +
                        "ORDER BY CAST( SUBSTRING( d.disease_id , 4) AS UNSIGNED) DESC "
        ),


        @NamedNativeQuery(
                name = "SafeDisease.insertNative",
                query = "INSERT IGNORE INTO safe_disease (disease_id, name) "
                        + "VALUES (:diseaseId, :name)"
        ),

        //Obtiene todas las enfermedades de la safe list segun la fuente
        @NamedNativeQuery(
                name = "SafeDisease.findAllDiseasesBySource",
                query = "-- consultar la \"disease safe list\"\n" +
                        "SELECT d.name 'diseaseName', u.url, d.disease_id  " +
                        "FROM safe_disease d " +
                        "INNER JOIN safe_disease_url du ON du.disease_id = d.disease_id  " +
                        "INNER JOIN source s ON s.source_id = du.source_id  " +
                        "INNER JOIN safe_url u ON u.url_id = du.url_id  " +
                        "WHERE s.name = :source "
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "SafeDiseaseMapping",
                entities = @EntityResult(
                        entityClass = SafeDisease.class,
                        fields = {
                                @FieldResult(name = "diseaseId", column = "disease_id"),
                                @FieldResult(name = "name", column = "name")
                        }
                )
        )
})

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="diseaseId")
public class SafeDisease {
    private String diseaseId;
    private String name;
    private String sourceId;
    private List<SafeDiseaseUrl> safeDiseaseUrlsByDiseaseId;

    @Id
    @Column(name = "disease_id", nullable = false, length = 150)
    public String getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(String diseaseId) {
        this.diseaseId = diseaseId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 150)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
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
        SafeDisease that = (SafeDisease) o;
        return Objects.equals(diseaseId, that.diseaseId) &&
                Objects.equals(name, that.name) ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(diseaseId, name);
    }

    @OneToMany(mappedBy = "safeDiseaseByDiseaseId")
    public List<SafeDiseaseUrl> getSafeDiseaseUrlsByDiseaseId() {
        return safeDiseaseUrlsByDiseaseId;
    }

    public void setSafeDiseaseUrlsByDiseaseId(List<SafeDiseaseUrl> safeDiseaseUrlsByDiseaseId) {
        this.safeDiseaseUrlsByDiseaseId = safeDiseaseUrlsByDiseaseId;
    }
}
