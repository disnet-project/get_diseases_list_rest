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
 * @className Source
 * @see
 */
@Entity
@Table(name = "source", catalog = "addb", schema = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Source.findAll", query = "SELECT s FROM Source s")
        , @NamedQuery(name = "Source.findById", query = "SELECT s FROM Source s WHERE s.sourceId = :sourceId")
        , @NamedQuery(name = "Source.findByName", query = "SELECT s FROM Source s WHERE s.name = :name")
})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Source.findByIdNative",
                query = "SELECT s.source_id, s.name "
                        + "FROM source s WHERE s.source_id = :sourceId"

        ),
        @NamedNativeQuery(
                name = "Source.findByNameNative",
                query = "SELECT s.source_id "
                        + "FROM source s WHERE s.name = :name"
        ),
        @NamedNativeQuery(
                name = "Source.findByIdNativeResultClass",
                query = "SELECT s.source_id, s.name "
                        + "FROM source s WHERE s.source_id = :sourceId",
                resultClass = Source.class
        ),
        @NamedNativeQuery(
                name = "Source.findAllNative",
                query = "SELECT s.source_id, s.name "
                        + "FROM source s "
        ),


        @NamedNativeQuery(
                name = "Source.insertNative",
                query = "INSERT INTO source (source_id, name) "
                        + "VALUES (:sourceId, :name) "
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "SourceMapping",
                entities = @EntityResult(
                        entityClass = Source.class,
                        fields = {
                                @FieldResult(name = "sourceId", column = "source_id"),
                                @FieldResult(name = "name", column = "name")
                        }
                )
        )
})

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="sourceId")
public class Source {
    private String sourceId;
    private String name;
    private List<DiseaseUrl> diseaseUrlsBySourceId;

    @Id
    @Column(name = "source_id", nullable = false, length = 10)
    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 20)
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
        Source source = (Source) o;
        return Objects.equals(sourceId, source.sourceId) &&
                Objects.equals(name, source.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceId, name);
    }

    @OneToMany(mappedBy = "sourceBySourceId")
    public List<DiseaseUrl> getDiseaseUrlsBySourceId() {
        return diseaseUrlsBySourceId;
    }

    public void setDiseaseUrlsBySourceId(List<DiseaseUrl> diseaseUrlsBySourceId) {
        this.diseaseUrlsBySourceId = diseaseUrlsBySourceId;
    }
}
