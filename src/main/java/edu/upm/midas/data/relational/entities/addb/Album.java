package edu.upm.midas.data.relational.entities.addb;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
                query = "SELECT a.album_id, a.date, a.number_diseases "
                        + "FROM album a WHERE a.album_id = :albumId AND a.date = :version "

        ),
        @NamedNativeQuery(
                name = "Album.findByVersionNative",
                query = "SELECT a.album_id, a.date, a.number_diseases "
                        + "FROM album a WHERE a.date = :version"

        ),
        @NamedNativeQuery(
                name = "Album.findLastVersionNative",
                query = "SELECT MAX(a.date) "
                        + "FROM album a "

        ),
        @NamedNativeQuery(
                name = "Album.findByLastVersionNative",
                query = "SELECT a.album_id, a.date, a.number_diseases " +
                        "FROM album a WHERE a.date = ( " +
                        "SELECT MAX(b.date) " +
                        "FROM album b ) "

        ),
        @NamedNativeQuery(
                name = "Album.findByVersionGraterThanNative",
                query = "SELECT a.album_id, a.date, a.number_diseases "
                        + "FROM album a WHERE a.date > :version"

        ),
        @NamedNativeQuery(
                name = "Album.findByVersionSmallerThanNative",
                query = "SELECT a.album_id, a.date, a.number_diseases "
                        + "FROM album a WHERE a.date < :version"

        ),
        @NamedNativeQuery(
                name = "Album.findByNumberDiseasesNative",
                query = "SELECT a.album_id, a.date, a.number_diseases "
                        + "FROM album a WHERE a.number_diseases = :numberDiseases"

        )
        ,
        @NamedNativeQuery(
                name = "Album.findByNumberDiseasesGreaterThanNative",
                query = "SELECT a.album_id, a.date, a.number_diseases "
                        + "FROM album a WHERE a.number_diseases > :numberDiseases"

        )
        ,
        @NamedNativeQuery(
                name = "Album.findByNumberDiseasesSmallerThanNative",
                query = "SELECT a.album_id, a.date, a.number_diseases "
                        + "FROM album a WHERE a.number_diseases < :numberDiseases"

        ),
        @NamedNativeQuery(
                name = "Album.maxSizeNative",
                query = "SELECT MAX(a.number_diseases) "
                        + "FROM album a "

        ),


        @NamedNativeQuery(
                name = "Album.findByMaxSizeNative",
                query = "SELECT a.album_id, a.date, a.number_diseases "
                        + "FROM album a WHERE a.number_diseases = ("
                        + "SELECT MAX(b.number_diseases) "
                        + "FROM album b ) "

        ),



        @NamedNativeQuery(
                name = "Album.findLinksOnWikipediaById",
                query = "SELECT a.album_id, a.date, d.disease_id, d.name 'diseaseName', s.source_id, s.name, u.url " +
                        "FROM album a " +
                        "INNER JOIN album_disease ad ON ad.album_id = a.album_id AND ad.date = a.date " +
                        "INNER JOIN disease d ON d.disease_id = ad.disease_id " +
                        "INNER JOIN disease_url du ON du.disease_id = d.disease_id " +
                        "INNER JOIN source s ON s.source_id = du.source_id " +
                        "INNER JOIN url u ON u.url_id = du.url_id " +
                        "WHERE a.album_id = :albumId " +
                        "AND a.date = :version " +
                        "AND s.name = 'wikipedia' "

        ),@NamedNativeQuery(
        name = "Album.findLinksByIdAndSourceNameNative",
        query = "SELECT a.album_id, a.date, d.disease_id, d.name 'diseaseName', s.source_id, s.name, u.url " +
                "FROM album a " +
                "INNER JOIN album_disease ad ON ad.album_id = a.album_id AND ad.date = a.date " +
                "INNER JOIN disease d ON d.disease_id = ad.disease_id " +
                "INNER JOIN disease_url du ON du.disease_id = d.disease_id " +
                "INNER JOIN source s ON s.source_id = du.source_id " +
                "INNER JOIN url u ON u.url_id = du.url_id " +
                "WHERE a.album_id = :albumId " +
                "AND a.date = :version " +
                "AND s.name = :sourceName "

        ),
        //Retornar enlaces que no tengan códigos válidos 5032
        // ,es decir, quitar todos aquellos que están muy lejos de ser artículos de enfermedades
        // esta no es la solución
        @NamedNativeQuery(
        name = "Album.findLinksWithoutCodesByIdAndSourceNameNative",
        query = "SELECT DISTINCT dis_s.disease_id, dis_s.name, u_s.url-- , c_s.code_id, r_s.name \n" +
                "FROM album_disease ad_s \n" +
                "INNER JOIN disease dis_s on dis_s.disease_id = ad_s.disease_id " +
                "INNER JOIN disease_url du_s on du_s.disease_id = dis_s.disease_id " +
                "INNER JOIN url u_s on u_s.url_id = du_s.url_id " +
                "INNER JOIN source s_s on s_s.source_id = du_s.source_id " +
                "INNER JOIN disease_code dc_s on dc_s.disease_id = dis_s.disease_id " +
                "INNER JOIN code c_s on c_s.code_id = dc_s.code_id AND c_s.resource_id = dc_s.resource_id " +
                "INNER JOIN resource r_s on r_s.resource_id = c_s.resource_id " +
                "WHERE ad_s.album_id = :albumId " +
                "AND s_s.name = :sourceName " +
                "-- and c_s.code_id != '-1' and c_s.code_id != '—' and c_s.code_id != '–' and c_s.code_id != '{}' and c_s.code_id != '' "

        ),



        @NamedNativeQuery(
                name = "Album.findAllNative",
                query = "SELECT a.album_id, a.date, a.number_diseases "
                        + "FROM album a "
        )

        ,
        @NamedNativeQuery(
                name = "Album.insertByAlbumIdAndVersionNative",
                query = "INSERT INTO album (album_id, date) " +
                        "VALUES (:albumId, :version)"
        ),
        @NamedNativeQuery(
                name = "Album.insertNative",
                query = "INSERT INTO album (album_id, date, number_diseases) " +
                        "VALUES (:albumId, :version, :numberDiseases)"
        ),
        @NamedNativeQuery(
                name = "Album.updateNumberDiseasesByIdNative",
                query = "UPDATE album " +
                        "SET number_diseases = (" +
                        "SELECT COUNT(*) " +
                        "FROM album_disease b " +
                        "WHERE b.album_id = :albumId AND b.date = :version) " +
                        "WHERE album_id = :albumId AND date = :version "
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
