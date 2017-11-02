package edu.upm.midas.controller;

import edu.upm.midas.common.util.TimeProvider;
import edu.upm.midas.data.relational.service.AlbumService;
import edu.upm.midas.model.response.Disease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class QueryController {

    @Autowired
    private AlbumService albumService;
    @Autowired
    private TimeProvider timeProviderService;

    @RequestMapping(path = { "/disease_album/get" }, //wikipedia extraction
            method = RequestMethod.GET)
    public List<Disease> getLinkDiseases(@RequestParam(value = "source") String source,
                                         @RequestParam(value = "album") String albumId,
                                         @RequestParam(value = "version") String version,
                                         @RequestParam(value = "token") String token,
                                         HttpServletRequest httpRequest,
                                         Device device) throws Exception {
        Date dataVersion = timeProviderService.getSdf().parse(version);
        return albumService.findLinksByIdAndSourceNameNative(albumId, dataVersion, source);
        //return "Successful extraction and insertion in a DB!";
    }
}
