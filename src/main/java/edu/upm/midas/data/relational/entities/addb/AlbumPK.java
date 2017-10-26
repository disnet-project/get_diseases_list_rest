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
 * @className AlbumPK
 * @see
 */
public class AlbumPK implements Serializable {
    private String albumId;
    private Date date;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlbumPK albumPK = (AlbumPK) o;
        return Objects.equals(albumId, albumPK.albumId) &&
                Objects.equals(date, albumPK.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(albumId, date);
    }
}
