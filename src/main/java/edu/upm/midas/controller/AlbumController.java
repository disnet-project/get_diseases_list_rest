package edu.upm.midas.controller;

import edu.upm.midas.authorization.token.component.JwtTokenUtil;
import edu.upm.midas.authorization.token.service.TokenAuthorization;
import edu.upm.midas.common.util.Common;
import edu.upm.midas.common.util.TimeProvider;
import edu.upm.midas.data.relational.service.AlbumService;
import edu.upm.midas.model.request.RequestFather;
import edu.upm.midas.model.response.Album;
import edu.upm.midas.model.response.ApiResponseError;
import edu.upm.midas.model.response.ResponseFather;
import edu.upm.midas.model.response.getLastAlbum.ResponseLA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "${my.service.rest.request.mapping.general.url}")
public class AlbumController {

    @Autowired
    private AlbumService albumService;
    @Autowired
    private TokenAuthorization tokenAuthorization;
    @Autowired
    private TimeProvider timeProvider;
    @Autowired
    private Common common;




    @RequestMapping(path = { "/last" }, //wikipedia extraction
            method = RequestMethod.GET)
    public ResponseLA getDiseaseLinkListGET(@RequestParam(value = "token", required = false) String token,
                                            HttpServletRequest httpRequest,
                                            Device device) throws Exception {
        ResponseFather responseFather = tokenAuthorization.validateService(token, httpRequest.getQueryString(), httpRequest.getRequestURL().toString(), device);
        ResponseLA response = new ResponseLA();
        List<ApiResponseError> errorsFound = new ArrayList<>();
        Album album = new Album();

        response.setAuthorized(responseFather.isAuthorized());
        response.setAuthorizationMessage(responseFather.getAuthorizationMessage());
        response.setToken(responseFather.getToken());

        response.setAlbum(album);

        if (responseFather.isAuthorized()){//Validar findLinksByIdAndSourceNameNative
            try {
                String start = timeProvider.getTimestampFormat();
                Album albumResponse = albumService.findByLastVersionNative();
                String end = timeProvider.getTimestampFormat();
                if (albumResponse != null){
                    response.setAlbum(albumResponse);
                    response.setResponseCode(HttpStatus.OK.toString());
                    response.setResponseMessage(HttpStatus.OK.getReasonPhrase());
                    common.saveQueryRuntime(responseFather.getInfoToken(), start, end);
                }else{
                    response.setResponseCode(HttpStatus.NOT_FOUND.toString());
                    response.setResponseMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
                    common.saveQueryRuntime(responseFather.getInfoToken(), start, end);
                }
            }catch (Exception e){
                response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
                response.setResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            }
        }else{
            response.setResponseCode(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.toString());
            response.setResponseMessage(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.getReasonPhrase());
        }
        response.setErrorsFound(errorsFound);

        return response;
        //return "Successful extraction and insertion in a DB!";
    }


    @RequestMapping(path = { "/last" }, //wikipedia extraction
            method = RequestMethod.POST)
    public ResponseLA getDiseaseLinkListPOST(@RequestBody @Valid RequestFather request,
                                               HttpServletRequest httpRequest,
                                               Device device) throws Exception {
        //System.out.println("path: "+httpRequest.getRequestURI());
        ResponseFather responseFather = tokenAuthorization.validateService(request.getToken(), RequestMethod.POST.toString(), httpRequest.getRequestURL().toString(), device);
        ResponseLA response = new ResponseLA();

        List<ApiResponseError> errorsFound = new ArrayList<>();
        Album album = new Album();

        response.setAuthorized(responseFather.isAuthorized());
        response.setAuthorizationMessage(responseFather.getAuthorizationMessage());
        response.setToken(responseFather.getToken());

        response.setAlbum(album);

        if (responseFather.isAuthorized()){//Validar findLinksByIdAndSourceNameNative
            try {
                String start = timeProvider.getTimestampFormat();
                Album albumResponse = albumService.findByLastVersionNative();
                String end = timeProvider.getTimestampFormat();
                if (albumResponse != null){
                    response.setAlbum(albumResponse);
                    response.setResponseCode(HttpStatus.OK.toString());
                    response.setResponseMessage(HttpStatus.OK.getReasonPhrase());
                    common.saveQueryRuntime(responseFather.getInfoToken(), start, end);
                }else{
                    response.setResponseCode(HttpStatus.NOT_FOUND.toString());
                    response.setResponseMessage(HttpStatus.NOT_FOUND.getReasonPhrase());
                    common.saveQueryRuntime(responseFather.getInfoToken(), start, end);
                }
            }catch (Exception e){
                response.setResponseCode(HttpStatus.INTERNAL_SERVER_ERROR.toString());
                response.setResponseMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
            }
        }else{
            response.setResponseCode(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.toString());
            response.setResponseMessage(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.getReasonPhrase());
        }
        response.setErrorsFound(errorsFound);


        return response;
        //return "Successful extraction and insertion in a DB!";
    }

}
