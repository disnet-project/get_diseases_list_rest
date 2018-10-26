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
 * @className DiseaseCode
 * @see
 */
@Entity
@Table(name = "disease_code", schema = "addb", catalog = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "DiseaseCode.findAll", query = "SELECT d FROM DiseaseCode d")
        , @NamedQuery(name = "DiseaseCode.findById", query = "SELECT d FROM DiseaseCode d WHERE d.diseaseId = :diseaseId AND d.codeId = :codeId AND d.resourceId = :codeId")
        , @NamedQuery(name = "DiseaseCode.findByDiseaseId", query = "SELECT d FROM DiseaseCode d WHERE d.diseaseId = :diseaseId")
        , @NamedQuery(name = "DiseaseCode.findByCodeId", query = "SELECT d FROM DiseaseCode d WHERE d.codeId = :codeId")
        , @NamedQuery(name = "DiseaseCode.findByResourceId", query = "SELECT d FROM DiseaseCode d WHERE d.resourceId = :resourceId")
})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "DiseaseCode.findByIdNative",
                query = "SELECT d.disease_id, d.code_id, d.resource_id "
                        + "FROM disease_code d WHERE d.disease_id = :diseaseId AND d.code_id = :codeId AND d.resource_id = :resourceId "

        ),


        @NamedNativeQuery(
                name = "DiseaseCode.insertNative",
                query = "INSERT INTO disease_code (disease_id, code_id, resource_id) "
                        + "VALUES (:diseaseId, :codeId, :resourceId)"
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "DiseaseCodeMapping",
                entities = @EntityResult(
                        entityClass = DiseaseCode.class,
                        fields = {
                                @FieldResult(name = "diseaseId", column = "disease_id"),
                                @FieldResult(name = "codeId", column = "code_id"),
                                @FieldResult(name = "resourceId", column = "resource_id")
                        }
                )
        )
})

@IdClass(DiseaseCodePK.class)
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="DiseaseCodePK")
public class DiseaseCode {
    private String diseaseId;
    private String codeId;
    private Integer resourceId;
    private Disease diseaseByDiseaseId;
    private Code code;

    @Id
    @Column(name = "disease_id", nullable = false, length = 150)
    public String getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(String diseaseId) {
        this.diseaseId = diseaseId;
    }

    @Id
    @Column(name = "code_id", nullable = false, length = 150)
    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    @Id
    @Column(name = "resource_id", nullable = false)
    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiseaseCode that = (DiseaseCode) o;
        return Objects.equals(diseaseId, that.diseaseId) &&
                Objects.equals(codeId, that.codeId) &&
                Objects.equals(resourceId, that.resourceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diseaseId, codeId, resourceId);
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
    @JoinColumns({@JoinColumn(name = "code_id", referencedColumnName = "code_id", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "resource_id", referencedColumnName = "resource_id", nullable = false, insertable = false, updatable = false)})
    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }
}
