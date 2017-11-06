package edu.upm.midas.controller;

import edu.upm.midas.authorization.token.service.TokenAuthorization;
import edu.upm.midas.common.util.TimeProvider;
import edu.upm.midas.data.relational.service.AlbumService;
import edu.upm.midas.model.request.RequestFather;
import edu.upm.midas.model.request.getDiseaseLinkList.RequestGDLL;
import edu.upm.midas.model.response.Album;
import edu.upm.midas.model.response.ResponseFather;
import edu.upm.midas.model.response.getDiseaseLinkList.ResponseGDLL;
import edu.upm.midas.model.response.getLastAlbum.ResponseLA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping(value = "${my.service.rest.request.mapping.general.url}")
public class AlbumController {

    @Autowired
    private AlbumService albumService;
    @Autowired
    private TimeProvider timeProviderService;
    @Autowired
    private TokenAuthorization tokenAuthorization;

    @RequestMapping(path = { "/last" }, //wikipedia extraction
            method = RequestMethod.GET)
    public ResponseLA getDiseaseLinkListGET(@RequestParam(value = "token", required = false) String token,
                                            HttpServletRequest httpRequest,
                                            Device device) throws Exception {
        ResponseFather responseFather = tokenAuthorization.validateService(token, httpRequest.getServletPath(), httpRequest.getServletPath(), device);
        ResponseLA response = new ResponseLA();
        if (responseFather.isAuthorization()){//Validar findLinksByIdAndSourceNameNative
            response.setAuthorization(responseFather.isAuthorization());
            response.setAuthorizationMessage(responseFather.getAuthorizationMessage());
            response.setToken(responseFather.getToken());
            edu.upm.midas.data.relational.entities.addb.Album album = albumService.findByLastVersionNative();
            Album albumResponse = new Album();
            albumResponse.setAlbumId(album.getAlbumId());
            albumResponse.setDate(album.getDate());
            albumResponse.setNumberDiseases(album.getNumberDiseases());
            response.setAlbum(albumResponse);
        }
        return response;
        //return "Successful extraction and insertion in a DB!";
    }


    @RequestMapping(path = { "/last" }, //wikipedia extraction
            method = RequestMethod.POST)
    public ResponseLA getDiseaseLinkListPOST(@RequestBody @Valid RequestFather request,
                                               HttpServletRequest httpRequest,
                                               Device device) throws Exception {
        ResponseFather responseFather = tokenAuthorization.validateService(request.getToken(), httpRequest.getServletPath(), httpRequest.getServletPath(), device);
        ResponseLA response = new ResponseLA();
        if (responseFather.isAuthorization()){
            response.setAuthorization(responseFather.isAuthorization());
            response.setAuthorizationMessage(responseFather.getAuthorizationMessage());
            response.setToken(responseFather.getToken());
            edu.upm.midas.data.relational.entities.addb.Album album = albumService.findByLastVersionNative();
            Album albumResponse = new Album();
            albumResponse.setAlbumId(album.getAlbumId());
            albumResponse.setDate(album.getDate());
            albumResponse.setNumberDiseases(album.getNumberDiseases());
            response.setAlbum(albumResponse);
        }
        return response;
        //return "Successful extraction and insertion in a DB!";
    }

}