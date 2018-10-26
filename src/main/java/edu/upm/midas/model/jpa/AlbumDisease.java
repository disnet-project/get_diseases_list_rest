package edu.upm.midas.model.jpa;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;
import java.util.Objects;

/**
 * Created by gerardo on 26/10/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className AlbumDisease
 * @see
 */
@Entity
@Table(name = "album_disease", schema = "addb", catalog = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "AlbumDisease.findAll", query = "SELECT a FROM AlbumDisease a")
        , @NamedQuery(name = "AlbumDisease.findById", query = "SELECT a FROM AlbumDisease a WHERE a.albumId = :albumId AND a.date = :date AND a.diseaseId = :diseaseId")
        , @NamedQuery(name = "AlbumDisease.findByAlbumId", query = "SELECT a FROM AlbumDisease a WHERE a.albumId = :albumId")
        , @NamedQuery(name = "AlbumDisease.findByDate", query = "SELECT a FROM AlbumDisease a WHERE a.date = :date")
        , @NamedQuery(name = "AlbumDisease.findByDiseaseId", query = "SELECT a FROM AlbumDisease a WHERE a.diseaseId = :diseaseId")
})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "AlbumDisease.findByIdNative",
                query = "SELECT a.album_id, a.date, a.disease_id " +
                        "FROM album_disease a WHERE a.album_id = :albumId AND a.date = :version AND a.disease_id = :diseaseId "
        ),
        @NamedNativeQuery(
                name = "AlbumDisease.findByAlbumIdNative",
                query = "SELECT a.album_id, a.date, a.disease_id " +
                        "FROM album_disease a WHERE a.album_id = :albumId "
        ),
        @NamedNativeQuery(
                name = "AlbumDisease.findByVersionNative",
                query = "SELECT a.album_id, a.date, a.disease_id " +
                        "FROM album_disease a WHERE a.date = :version "
        ),
        @NamedNativeQuery(
                name = "AlbumDisease.findByDiseaseIdNative",
                query = "SELECT a.album_id, a.date, a.disease_id " +
                        "FROM album_disease a WHERE a.disease_id = :diseaseId "
        ),
        @NamedNativeQuery(
                name = "AlbumDisease.findLastAlbumVersionNative",
                query = "SELECT MAX(a.date) " +
                        "FROM album_disease a "
        ),


        @NamedNativeQuery(
                name = "AlbumDisease.findAllNative",
                query = "SELECT a.album_id, a.date, a.disease_id "
                        + "FROM album_disease a "
        )

        ,
        @NamedNativeQuery(
                name = "AlbumDisease.insertNative",
                query = "INSERT INTO album_disease (album_id, date, disease_id) " +
                        "VALUES (:albumId, :version, :diseaseId)"
        ),
        @NamedNativeQuery(
                name = "AlbumDisease.insertIgnoreNative",
                query = "INSERT IGNORE INTO album_disease (album_id, date, disease_id) " +
                        "VALUES (:albumId, :version, :diseaseId)"
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "AlbumDiseaseMapping",
                entities = @EntityResult(
                        entityClass = AlbumDisease.class,
                        fields = {
                                @FieldResult(name = "albumId", column = "album_id"),
                                @FieldResult(name = "date", column = "date"),
                                @FieldResult(name = "diseaseId", column = "disease_id")
                        }
                )
        )
})

@IdClass(AlbumDiseasePK.class)
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="AlbumDiseasePK")
public class AlbumDisease {
    private String albumId;
    private Date date;
    private String diseaseId;
    private Album album;
    private Disease diseaseByDiseaseId;

    @Id
    @Column(name = "album_id", nullable = false, length = 15)
    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    @Id
    @Column(name = "date", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Id
    @Column(name = "disease_id", nullable = false, length = 150)
    public String getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(String diseaseId) {
        this.diseaseId = diseaseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumDisease that = (AlbumDisease) o;
        return Objects.equals(albumId, that.albumId) &&
                Objects.equals(date, that.date) &&
                Objects.equals(diseaseId, that.diseaseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumId, date, diseaseId);
    }

    @ManyToOne
    @JoinColumns({@JoinColumn(name = "album_id", referencedColumnName = "album_id", nullable = false, insertable = false, updatable = false),
            @JoinColumn(name = "date", referencedColumnName = "date", nullable = false, insertable = false, updatable = false)})
    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    @ManyToOne
    @JoinColumn(name = "disease_id", referencedColumnName = "disease_id", nullable = false, insertable = false, updatable = false)
    public Disease getDiseaseByDiseaseId() {
        return diseaseByDiseaseId;
    }

    public void setDiseaseByDiseaseId(Disease diseaseByDiseaseId) {
        this.diseaseByDiseaseId = diseaseByDiseaseId;
    }
}
