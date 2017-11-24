package edu.upm.midas.controller;

import edu.upm.midas.authorization.token.service.TokenAuthorization;
import edu.upm.midas.common.util.TimeProvider;
import edu.upm.midas.data.relational.service.helper.AlbumHelper;
import edu.upm.midas.model.request.getDiseaseLinkList.RequestGDLL;
import edu.upm.midas.model.response.ResponseFather;
import edu.upm.midas.model.response.getDiseaseLinkList.ResponseGDLL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mobile.device.Device;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;

@RestController
@RequestMapping(value = "${my.service.rest.request.mapping.general.url}")
public class QueryController {

    @Autowired
    private AlbumHelper albumHelper;
    @Autowired
    private TimeProvider timeProviderService;
    @Autowired
    private TokenAuthorization tokenAuthorization;

    @RequestMapping(path = { "/get" }, //wikipedia extraction
            method = RequestMethod.GET)
    public ResponseGDLL getDiseaseLinkListGET(@RequestParam(value = "source") String source,
                                              @RequestParam(value = "album") String albumId,
                                              @RequestParam(value = "version") String version,
                                              @RequestParam(value = "token", required = false) String token,
                                              HttpServletRequest httpRequest,
                                              Device device) throws Exception {
        Date dataVersion = timeProviderService.getSdf().parse(version);
        ResponseFather responseFather = tokenAuthorization.validateService(token, httpRequest.getServletPath(), httpRequest.getServletPath(), device);
        ResponseGDLL response = new ResponseGDLL();
        if (responseFather.isAuthorized()){//Validar findLinksByIdAndSourceNameNative
            response.setAuthorized(responseFather.isAuthorized());
            response.setAuthorizationMessage(responseFather.getAuthorizationMessage());
            response.setToken(responseFather.getToken());
            response.setDiseases(albumHelper.findLinksByIdAndSourceNameNativeAndReplaceSpecialCharacters(albumId, dataVersion, source));
        }
        return response;
        //return "Successful extraction and insertion in a DB!";
    }


    @RequestMapping(path = { "/get" }, //wikipedia extraction
            method = RequestMethod.POST)
    public ResponseGDLL getDiseaseLinkListPOST(@RequestBody @Valid RequestGDLL requestGDLL,
                                               HttpServletRequest httpRequest,
                                               Device device) throws Exception {
        Date dataVersion = timeProviderService.getSdf().parse(requestGDLL.getVersion());
        ResponseFather responseFather = tokenAuthorization.validateService(requestGDLL.getToken(), httpRequest.getServletPath(), httpRequest.getServletPath(), device);
        ResponseGDLL response = new ResponseGDLL();
        if (responseFather.isAuthorized()){
            response.setAuthorized(responseFather.isAuthorized());
            response.setAuthorizationMessage(responseFather.getAuthorizationMessage());
            response.setToken(responseFather.getToken());
            response.setDiseases(albumHelper.findLinksByIdAndSourceNameNativeAndReplaceSpecialCharacters(requestGDLL.getAlbum(), dataVersion, requestGDLL.getSource()));
        }
        return response;
        //return "Successful extraction and insertion in a DB!";
    }

}
