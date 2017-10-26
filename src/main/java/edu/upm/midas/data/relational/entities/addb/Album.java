package edu.upm.midas.data.relational.entities.addb;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by gerardo on 26/10/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className Album
 * @see
 */
@Entity
@Table(name = "album", schema = "addb", catalog = "")
@XmlRootElement
@NamedQueries({
        @NamedQuery(name = "Album.findAll", query = "SELECT a FROM Album a")
        , @NamedQuery(name = "Album.findById", query = "SELECT a FROM Album a WHERE a.albumId = :albumId AND a.date = :date")
        , @NamedQuery(name = "Album.findByAlbumId", query = "SELECT a FROM Album a WHERE a.albumId = :albumId")
        , @NamedQuery(name = "Album.findByDate", query = "SELECT a FROM Album a WHERE a.date = :date")
        , @NamedQuery(name = "Album.findByNumberDiseases", query = "SELECT a FROM Album a WHERE a.numberDiseases = :numberDiseases")
})

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Album.findByIdNative",
                query = "SELECT a.album_id, a.date "
                        + "FROM album a WHERE a.album_id = : albumId AND a.date= :version"

        ),
        @NamedNativeQuery(
                name = "Album.findByVersionNative",
                query = "SELECT a.album_id, a.date "
                        + "FROM album a WHERE a.date= :version"

        ),
        @NamedNativeQuery(
                name = "Album.findByNumberDiseasesNative",
                query = "SELECT a.album_id, a.date "
                        + "FROM album a WHERE a.number_diseases= :numberDiseases"

        ),
        @NamedNativeQuery(
                name = "Album.MaxSizeNative",
                query = "SELECT MAX(a.number_diseases) "
                        + "FROM album a "

        ),


        @NamedNativeQuery(
                name = "Album.findByMaxSizeNative",
                query = "SELECT a.album_id, a.date, a.number_diseases "
                        + "FROM album a WHERE a.number_diseases = ("
                        + "SELECT MAX(b.number_diseases) "
                        + "FROM album b ) as c "

        ),


        @NamedNativeQuery(
                name = "Album.findAllNative",
                query = "SELECT a.album_id, a.date "
                        + "FROM album a "
        )

        ,
        @NamedNativeQuery(
                name = "Album.insertAlbumAndDateNative",
                query = "INSERT INTO album (album_id, date) " +
                        "VALUES (:albumId, :version)"
        ),
        @NamedNativeQuery(
                name = "Album.insertNative",
                query = "INSERT INTO album (album_id, date, number_diseases) " +
                        "VALUES (:albumId, :version, :numberDiseases)"
        )
})

@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "AlbumMapping",
                entities = @EntityResult(
                        entityClass = Album.class,
                        fields = {
                                @FieldResult(name = "albumId", column = "album_id"),
                                @FieldResult(name = "date", column = "date"),
                                @FieldResult(name = "number_diseases", column = "numberDiseases")
                        }
                )
        )
})

@IdClass(AlbumPK.class)
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="AlbumPK")
public class Album {
    private String albumId;
    private Date date;
    private Integer numberDiseases;
    private List<AlbumDisease> albumDiseases;

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

    @Column(name = "number_diseases", nullable = false)
    public Integer getNumberDiseases() {
        return numberDiseases;
    }

    public void setNumberDiseases(Integer numberDiseases) {
        this.numberDiseases = numberDiseases;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Album album = (Album) o;
        return Objects.equals(albumId, album.albumId) &&
                Objects.equals(date, album.date) &&
                Objects.equals(numberDiseases, album.numberDiseases);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumId, date, numberDiseases);
    }

    @OneToMany(mappedBy = "album")
    public List<AlbumDisease> getAlbumDiseases() {
        return albumDiseases;
    }

    public void setAlbumDiseases(List<AlbumDisease> albumDiseases) {
        this.albumDiseases = albumDiseases;
    }
}
