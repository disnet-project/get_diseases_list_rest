package edu.upm.midas.controller;

import com.google.gson.Gson;
import edu.upm.midas.client_modules.authorization.token.service.TokenAuthorization;
import edu.upm.midas.common.util.Common;
import edu.upm.midas.common.util.TimeProvider;
import edu.upm.midas.service.jpa.AlbumService;
import edu.upm.midas.model.request.RequestFather;
import edu.upm.midas.model.request.getAlbum.RequestAlbum;
import edu.upm.midas.model.response.Album;
import edu.upm.midas.model.response.ApiResponseError;
import edu.upm.midas.model.response.ResponseFather;
import edu.upm.midas.model.response.getLastAlbum.ResponseLA;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
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
    public ResponseLA getLastAlbumByGET(@RequestParam(value = "token", required = false) String token,
                                        HttpServletRequest httpRequest,
                                        Device device) throws Exception {
        //System.out.println(httpRequest.getMethod());
        ResponseFather responseFather = tokenAuthorization.validateService(token, httpRequest.getQueryString(), httpRequest.getMethod(), httpRequest.getRequestURL().toString(), device);
        ResponseLA response = new ResponseLA();
        List<ApiResponseError> errorsFound = new ArrayList<>();
        Album album = new Album();

        response.setAuthorized(responseFather.isAuthorized());
        response.setAuthorizationMessage(responseFather.getAuthorizationMessage());
        response.setToken(responseFather.getToken());

        //<editor-fold desc="PROCESO DE RESPUESTA">
        authorized(responseFather, response, album, false, null, "", errorsFound);
        //</editor-fold>

        return response;
        //return "Successful extraction and insertion in a DB!";
    }


    @RequestMapping(path = { "/last" }, //wikipedia extraction
            method = RequestMethod.POST)
    public ResponseLA getLastAlbumByPOST(@RequestBody @Valid RequestFather request,
                                        HttpServletRequest httpRequest,
                                        Device device) throws Exception {
        //System.out.println("path: "+httpRequest.getRequestURI());
        //System.out.println(httpRequest.getMethod() + " " + RequestMethod.POST.toString());
        Gson gson = new Gson();
        String requestJSON = gson.toJson(request);
        ResponseFather responseFather = tokenAuthorization.validateService(request.getToken(), requestJSON, httpRequest.getMethod(), httpRequest.getRequestURL().toString(), device);
        ResponseLA response = new ResponseLA();

        List<ApiResponseError> errorsFound = new ArrayList<>();
        Album album = new Album();

        response.setAuthorized(responseFather.isAuthorized());
        response.setAuthorizationMessage(responseFather.getAuthorizationMessage());
        response.setToken(responseFather.getToken());

        //<editor-fold desc="PROCESO DE RESPUESTA">
        authorized(responseFather, response, album, false, null, "", errorsFound);
        //</editor-fold>

        return response;
        //return "Successful extraction and insertion in a DB!";
    }


    /**
     * @param responseFather
     * @param response
     * @param errorsFound
     */
    public void authorized(ResponseFather responseFather, ResponseLA response, Album album, boolean one, Date snapshot, String source, List<ApiResponseError> errorsFound) throws Exception {
        if (responseFather.isAuthorized()){//Validar findLinksByIdAndSourceNameNative
            try {
                String start = timeProvider.getTimestampFormat();
                if (one){
                    album = albumService.findByVersionAndSourceNative(snapshot, source);
                }else {
                    album = albumService.findByLastVersionNative();
                }
                String end = timeProvider.getTimestampFormat();
                if (album != null){
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
        response.setAlbum(album);
        response.setErrorsFound(errorsFound);
    }


    @RequestMapping(path = { "/one" }, //wikipedia extraction
            method = RequestMethod.POST)
    public ResponseLA getAlbumByPOST(@RequestBody @Valid RequestAlbum request,
                                        HttpServletRequest httpRequest,
                                        Device device) throws Exception {
        //System.out.println("path: "+httpRequest.getRequestURI());
        //System.out.println(httpRequest.getMethod() + " " + RequestMethod.POST.toString());
        Gson gson = new Gson();
        String requestJSON = gson.toJson(request);
        ResponseFather responseFather = tokenAuthorization.validateService(request.getToken(), requestJSON, httpRequest.getMethod(), httpRequest.getRequestURL().toString(), device);
        ResponseLA response = new ResponseLA();

        List<ApiResponseError> errorsFound = new ArrayList<>();
        Album album = new Album();

        response.setAuthorized(responseFather.isAuthorized());
        response.setAuthorizationMessage(responseFather.getAuthorizationMessage());
        response.setToken(responseFather.getToken());

        //<editor-fold desc="PROCESO DE RESPUESTA">
        authorized(responseFather, response, album, true, timeProvider.stringToDate(request.getSnapshot()), request.getSource(), errorsFound);
        //</editor-fold>

        return response;
        //return "Successful extraction and insertion in a DB!";
    }


    @RequestMapping(path = { "/one" }, //wikipedia extraction
            method = RequestMethod.GET,
            params = {"source", "snapshot", "token"})
    public ResponseLA getAlbumByGET(@RequestParam(value = "source") @Valid @NotBlank @NotEmpty @NotNull String source,
                                    @RequestParam(value = "snapshot") @Valid @NotBlank @NotEmpty @NotNull  String snapshot,
                                    @RequestParam(value = "token") @Valid @NotBlank @NotEmpty @NotNull  String token,
                               HttpServletRequest httpRequest,
                               Device device) throws Exception {
        //System.out.println("path: "+httpRequest.getRequestURI());
        //System.out.println(httpRequest.getMethod() + " " + RequestMethod.POST.toString());
        ResponseFather responseFather = tokenAuthorization.validateService(token, httpRequest.getQueryString(), httpRequest.getMethod(), httpRequest.getRequestURL().toString(), device);
        ResponseLA response = new ResponseLA();

        List<ApiResponseError> errorsFound = new ArrayList<>();
        Album album = new Album();

        response.setAuthorized(responseFather.isAuthorized());
        response.setAuthorizationMessage(responseFather.getAuthorizationMessage());
        response.setToken(responseFather.getToken());

        //<editor-fold desc="PROCESO DE RESPUESTA">
        authorized(responseFather, response, album, true, timeProvider.stringToDate(snapshot), source, errorsFound);
        //</editor-fold>

        return response;
        //return "Successful extraction and insertion in a DB!";
    }


}
