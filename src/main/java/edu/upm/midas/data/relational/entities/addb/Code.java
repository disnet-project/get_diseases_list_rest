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
 * @className Code
 * @see
 */
@Entity
@Table(name = "code", schema = "addb", catalog = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Code.findAll", query = "SELECT c FROM Code c")
        , @NamedQuery(name = "Code.findById", query = "SELECT c FROM Code c WHERE c.codeId = :codeId AND c.resourceId = :resourceId")
        , @NamedQuery(name = "Code.findByCodeId", query = "SELECT c FROM Code c WHERE c.codeId = :codeId")
        , @NamedQuery(name = "Code.findByResourceId", query = "SELECT c FROM Code c WHERE c.resourceId = :resourceId")
})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Code.findByIdNativeMapping",
                query = "SELECT c.code_id, c.resource_id "
                        + "FROM code c WHERE c.code_id = :codeId AND c.resource_id = :resourceId",
                resultSetMapping="CodeMapping"

        ),
        @NamedNativeQuery(
                name = "Code.findByIdNativeResultClass",
                query = "SELECT c.code_id, c.resource_id "
                        + "FROM code c WHERE c.code_id = :codeId AND c.resource_id = :resourceId",
                resultClass = Code.class
        ),
        @NamedNativeQuery(
                name = "Code.findByIdNative",
                query = "SELECT c.code_id, c.resource_id "
                        + "FROM code c WHERE c.code_id = :codeId AND c.resource_id = :resourceId"
        ),


        @NamedNativeQuery(
                name = "Code.insertNative",
                query = "INSERT INTO code (code_id, resource_id) "
                        + "VALUES (:codeId, :resourceId)"
        ),
        @NamedNativeQuery(
                name = "DiseaseCode.insertNative",
                query = "INSERT IGNORE INTO disease_code (disease_id, code_id, resource_id) "
                        + "VALUES (:diseaseId, :codeId, :resourceId)"
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "CodeMapping",
                entities = @EntityResult(
                        entityClass = Code.class,
                        fields = {
                                @FieldResult(name = "codeId", column = "code_id"),
                                @FieldResult(name = "resourceId", column = "resource_id")
                        }
                )
        )
})

@IdClass(CodePK.class)
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="CodePK")
public class Code {
    private String codeId;
    private Integer resourceId;
    private Resource resourceByResourceId;
    private List<DiseaseCode> diseaseCodes;

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
        Code code = (Code) o;
        return Objects.equals(codeId, code.codeId) &&
                Objects.equals(resourceId, code.resourceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeId, resourceId);
    }

    @ManyToOne
    @JoinColumn(name = "resource_id", referencedColumnName = "resource_id", nullable = false, insertable = false, updatable = false)
    public Resource getResourceByResourceId() {
        return resourceByResourceId;
    }

    public void setResourceByResourceId(Resource resourceByResourceId) {
        this.resourceByResourceId = resourceByResourceId;
    }

    @OneToMany(mappedBy = "code")
    public List<DiseaseCode> getDiseaseCodes() {
        return diseaseCodes;
    }

    public void setDiseaseCodes(List<DiseaseCode> diseaseCodes) {
        this.diseaseCodes = diseaseCodes;
    }
}
