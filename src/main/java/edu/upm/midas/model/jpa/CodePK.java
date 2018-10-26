package edu.upm.midas.model.jpa;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by gerardo on 26/10/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className CodePK
 * @see
 */
public class CodePK implements Serializable {
    private String codeId;
    private Integer resourceId;

    @Column(name = "code_id", nullable = false, length = 150)
    @Id
    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    @Column(name = "resource_id", nullable = false)
    @Id
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
        CodePK codePK = (CodePK) o;
        return Objects.equals(codeId, codePK.codeId) &&
                Objects.equals(resourceId, codePK.resourceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codeId, resourceId);
    }
}
