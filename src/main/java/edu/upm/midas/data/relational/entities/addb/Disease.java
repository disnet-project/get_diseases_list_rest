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
                name = "Disease.insertNative",
                query = "INSERT INTO disease (disease_id, name) "
                        + "VALUES (:diseaseId, :name)"
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "DiseaseMapping",
                entities = @EntityResult(
                        entityClass = Disease.class,
                        fields = {
                                @FieldResult(name = "diseaseId", column = "disease_id"),
                                @FieldResult(name = "name", column = "name")
                        }
                )
        )
})

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="diseaseId")
public class Disease {
    private String diseaseId;
    private String name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Disease disease = (Disease) o;
        return Objects.equals(diseaseId, disease.diseaseId) &&
                Objects.equals(name, disease.name);
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
