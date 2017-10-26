package edu.upm.midas.data.relational.entities.addb;
import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

/**
 * Created by gerardo on 26/10/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className AlbumDiseasePK
 * @see
 */
public class AlbumDiseasePK implements Serializable {
    private String albumId;
    private Date date;
    private String diseaseId;

    @Column(name = "album_id", nullable = false, length = 15)
    @Id
    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    @Column(name = "date", nullable = false)
    @Id
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "disease_id", nullable = false, length = 150)
    @Id
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
        AlbumDiseasePK that = (AlbumDiseasePK) o;
        return Objects.equals(albumId, that.albumId) &&
                Objects.equals(date, that.date) &&
                Objects.equals(diseaseId, that.diseaseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumId, date, diseaseId);
    }
}
