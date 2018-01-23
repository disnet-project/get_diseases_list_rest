package edu.upm.midas.authorization.token.service;

import edu.upm.midas.authorization.model.UpdateQueryRuntimeRequest;
import edu.upm.midas.authorization.model.ValidationResponse;
import edu.upm.midas.authorization.service.AuthResourceService;
import edu.upm.midas.authorization.token.component.JwtTokenUtil;
import edu.upm.midas.model.response.ResponseFather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Service;

/**
 * Created by gerardo on 26/09/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project tvp_rest
 * @className TokenAuthorization
 * @see
 */
@Service
public class TokenAuthorization {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private AuthResourceService authResourceService;

    @Value("${my.service.authorization.url}")
    private String authorization_url;
    @Value("${my.service.authorization.path}")
    private String authorization_path;


    public ResponseFather validateService(String userToken, String request, String method, String url, Device device){
        ResponseFather response = new ResponseFather();
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //System.out.println(userToken+" | "+ request+" | "+ url +" | "+ device);
        String token = jwtTokenUtil.generateToken( userToken, request, method, url, device );

        System.out.println( "Call Authorization API... (" + authorization_url + authorization_path + ")");
        ValidationResponse validationResponse = authResourceService.validationServiceByToken( token );
        response.setAuthorized( validationResponse.isAuthorized() );
        response.setAuthorizationMessage( validationResponse.getMessage() );
        response.setToken( userToken );
        response.setInfoToken( validationResponse.getToken() );

        return response;
    }


    public HttpStatus updateQueryRuntime(String queryId, String startDatetime, String endDatetime){
        UpdateQueryRuntimeRequest request = new UpdateQueryRuntimeRequest();
        System.out.println( "Call Authorization API for update query runtime... " +" "+queryId +" "+ startDatetime +" "+ endDatetime );
        request.setQueryId(queryId);
        request.setStartDatetime(startDatetime);
        request.setEndDatetime(endDatetime);
        return authResourceService.updateQueryRunTime(request);
    }



}
