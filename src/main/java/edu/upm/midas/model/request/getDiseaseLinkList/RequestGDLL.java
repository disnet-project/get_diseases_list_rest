package edu.upm.midas.model.request.getDiseaseLinkList;

import edu.upm.midas.constants.Constants;
import edu.upm.midas.model.request.RequestFather;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.crypto.Data;
import java.util.Date;

public class RequestGDLL extends RequestFather {

    @Valid
    @NotNull(message = Constants.ERR_NO_PARAMETER)
    @Size(min = 1, message = Constants.ERR_EMPTY_PARAMETER)
    private String source;
    @Valid
    @NotNull(message = Constants.ERR_NO_PARAMETER)
    @Size(min = 1, message = Constants.ERR_EMPTY_PARAMETER)
    private String album;
    @Valid
    @NotNull(message = Constants.ERR_NO_PARAMETER)
    @Size(min = 1, max = 10, message = Constants.ERR_EMPTY_PARAMETER)
    private String version;



    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
