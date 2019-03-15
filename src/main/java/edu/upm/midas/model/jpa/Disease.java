package edu.upm.midas.model.jpa;

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
 * @className Disease
 * @see
 */
@Entity
@Table(name = "disease", schema = "addb", catalog = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Disease.findAll", query = "SELECT d FROM Disease d")
        , @NamedQuery(name = "Disease.findById", query = "SELECT d FROM Disease d WHERE d.diseaseId = :diseaseId")
        , @NamedQuery(name = "Disease.findByName", query = "SELECT d FROM Disease d WHERE d.name = :name")
})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Disease.findByIdNative",
                query = "SELECT d.disease_id, d.name "
                        + "FROM disease d WHERE d.disease_id COLLATE utf8_bin = :diseaseId"

        ),
        @NamedNativeQuery(
                name = "Disease.findByNameNative",
                query = "SELECT d.disease_id, d.name "
                        + "FROM disease d WHERE d.name COLLATE utf8_bin = :name"
        ),
        @NamedNativeQuery(
                name = "Disease.findLastIdNative",
                query = "SELECT d.disease_id " +
                        "FROM disease d " +
                        "ORDER BY CAST( SUBSTRING( d.disease_id , 4) AS UNSIGNED) DESC "
        ),


        @NamedNativeQuery(
                name = "Disease.insertNative",
                query = "INSERT INTO disease (disease_id, name, source_id, partly_irrelevant, totally_irrelevant) "
                        + "VALUES (:diseaseId, :name, :sourceId, :partly_irrelevant, :totally_irrelevant)"
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "DiseaseMapping",
                entities = @EntityResult(
                        entityClass = Disease.class,
                        fields = {
                                @FieldResult(name = "diseaseId", column = "disease_id"),
                                @FieldResult(name = "name", column = "name"),
                                @FieldResult(name = "partlyIrrelevant", column = "partly_irrelevant"),
                                @FieldResult(name = "totallyIrrelevant", column = "totally_irrelevant"),
                                @FieldResult(name = "sourceId", column = "source_id")
                        }
                )
        )
})

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="diseaseId")
public class Disease {
    private String diseaseId;
    private String name;
    private Byte partlyIrrelevant;
    private Byte totallyIrrelevant;
    private String sourceId;
    private List<AlbumDisease> albumDiseasesByDiseaseId;
    private List<DiseaseCode> diseaseCodesByDiseaseId;
    private List<DiseaseUrl> diseaseUrlsByDiseaseId;

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
    @Column(name = "partly_irrelevant", nullable = true)
    public Byte getPartlyIrrelevant() {
        return partlyIrrelevant;
    }

    public void setPartlyIrrelevant(Byte partlyIrrelevant) {
        this.partlyIrrelevant = partlyIrrelevant;
    }

    @Basic
    @Column(name = "totally_irrelevant", nullable = true)
    public Byte getTotallyIrrelevant() {
        return totallyIrrelevant;
    }

    public void setTotallyIrrelevant(Byte totallyIrrelevant) {
        this.totallyIrrelevant = totallyIrrelevant;
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
        Disease disease = (Disease) o;
        return Objects.equals(diseaseId, disease.diseaseId) &&
                Objects.equals(name, disease.name)&&
                Objects.equals(partlyIrrelevant, disease.partlyIrrelevant) &&
                Objects.equals(totallyIrrelevant, disease.totallyIrrelevant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diseaseId, name);
    }

    @OneToMany(mappedBy = "diseaseByDiseaseId")
    public List<AlbumDisease> getAlbumDiseasesByDiseaseId() {
        return albumDiseasesByDiseaseId;
    }

    public void setAlbumDiseasesByDiseaseId(List<AlbumDisease> albumDiseasesByDiseaseId) {
        this.albumDiseasesByDiseaseId = albumDiseasesByDiseaseId;
    }

    @OneToMany(mappedBy = "diseaseByDiseaseId")
    public List<DiseaseCode> getDiseaseCodesByDiseaseId() {
        return diseaseCodesByDiseaseId;
    }

    public void setDiseaseCodesByDiseaseId(List<DiseaseCode> diseaseCodesByDiseaseId) {
        this.diseaseCodesByDiseaseId = diseaseCodesByDiseaseId;
    }

    @OneToMany(mappedBy = "diseaseByDiseaseId")
    public List<DiseaseUrl> getDiseaseUrlsByDiseaseId() {
        return diseaseUrlsByDiseaseId;
    }

    public void setDiseaseUrlsByDiseaseId(List<DiseaseUrl> diseaseUrlsByDiseaseId) {
        this.diseaseUrlsByDiseaseId = diseaseUrlsByDiseaseId;
    }
}
