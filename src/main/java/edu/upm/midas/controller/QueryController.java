package edu.upm.midas.controller;

import com.google.gson.Gson;
import edu.upm.midas.authorization.token.service.TokenAuthorization;
import edu.upm.midas.common.util.Common;
import edu.upm.midas.common.util.TimeProvider;
import edu.upm.midas.data.relational.service.helper.AlbumHelper;
import edu.upm.midas.model.request.getDiseaseLinkList.RequestGDLL;
import edu.upm.midas.model.response.ApiResponseError;
import edu.upm.midas.model.response.Disease;
import edu.upm.midas.model.response.ResponseFather;
import edu.upm.midas.model.response.getDiseaseLinkList.ResponseGDLL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "${my.service.rest.request.mapping.general.url}")
public class QueryController {

    @Autowired
    private AlbumHelper albumHelper;
    @Autowired
    private TimeProvider timeProvider;
    @Autowired
    private TokenAuthorization tokenAuthorization;
    @Autowired
    private Common common;

    @RequestMapping(path = { "/get" }, //wikipedia extraction
            method = RequestMethod.GET)
    public ResponseGDLL getDiseaseLinkListGET(@RequestParam(value = "source") String source,
                                              @RequestParam(value = "album") String albumId,
                                              @RequestParam(value = "snapshot") String snapshot,
                                              @RequestParam(value = "token", required = false) String token,
                                              HttpServletRequest httpRequest,
                                              Device device) throws Exception {
        Date dataVersion = timeProvider.getSdf().parse(snapshot);
        ResponseFather responseFather = tokenAuthorization.validateService(token, httpRequest.getQueryString(), httpRequest.getMethod(), httpRequest.getRequestURL().toString(), device);
        ResponseGDLL response = new ResponseGDLL();
        List<ApiResponseError> errorsFound = new ArrayList<>();
        List<Disease> diseases = new ArrayList<>();

        response.setAuthorized(responseFather.isAuthorized());
        response.setAuthorizationMessage(responseFather.getAuthorizationMessage());
        response.setToken(responseFather.getToken());

        response.setDiseases(diseases);

        if (responseFather.isAuthorized()){//Validar findLinksByIdAndSourceNameNative
            try {
                String start = timeProvider.getTimestampFormat();
                diseases = albumHelper.findLinksByIdAndSourceNameNativeAndReplaceSpecialCharacters(errorsFound, albumId, dataVersion, source);
                String end = timeProvider.getTimestampFormat();
                if (diseases.size() > 0){
                    response.setDiseases(diseases);
                    response.setUseDiseaseSafeList(true);//Siempre será true desde el 2018-04-02
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
        response.setDiseaseCount(diseases.size());
        response.setErrorsFound(errorsFound);

        return response;
        //return "Successful extraction and insertion in a DB!";
    }


    @RequestMapping(path = { "/get" }, //wikipedia extraction
            method = RequestMethod.POST)
    public ResponseGDLL getDiseaseLinkListPOST(@RequestBody @Valid RequestGDLL requestGDLL,
                                               HttpServletRequest httpRequest,
                                               Device device) throws Exception {
        Gson gson = new Gson();
        String requestJSON = gson.toJson(requestGDLL);
        Date dataVersion = timeProvider.getSdf().parse(requestGDLL.getSnapshot());//httpRequest.getQueryString() = request
        ResponseFather responseFather = tokenAuthorization.validateService(requestGDLL.getToken(), requestJSON, httpRequest.getMethod(), httpRequest.getRequestURL().toString(), device);
        ResponseGDLL response = new ResponseGDLL();
        List<ApiResponseError> errorsFound = new ArrayList<>();
        List<Disease> diseases = new ArrayList<>();

        response.setAuthorized(responseFather.isAuthorized());
        response.setAuthorizationMessage(responseFather.getAuthorizationMessage());
        response.setToken(responseFather.getToken());

        response.setDiseases(diseases);

        if (responseFather.isAuthorized()){//Validar findLinksByIdAndSourceNameNative
            try {
                String start = timeProvider.getTimestampFormat();
                diseases = albumHelper.findLinksByIdAndSourceNameNativeAndReplaceSpecialCharacters(errorsFound, requestGDLL.getAlbum(), dataVersion, requestGDLL.getSource());
                String end = timeProvider.getTimestampFormat();
                if (diseases.size() > 0){
                    response.setDiseases(diseases);
                    response.setUseDiseaseSafeList(true);//Siempre será true desde el 2018-04-02
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
        response.setDiseaseCount(diseases.size());
        response.setErrorsFound(errorsFound);
        return response;
        //return "Successful extraction and insertion in a DB!";
    }

}
