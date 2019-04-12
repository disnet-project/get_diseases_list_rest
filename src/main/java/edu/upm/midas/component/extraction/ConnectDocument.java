package edu.upm.midas.component.extraction;

import edu.upm.midas.common.util.Common;
import edu.upm.midas.constants.Constants;
import edu.upm.midas.model.extraction.Connection_;
import edu.upm.midas.enums.StatusHttpEnum;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by gerardo on 29/3/17.
 * @project ExtractionInformationWikipedia
 * @version ${<VERSION>}
 * @author Gerardo Lagunes G.
 * @className ConnectDisease
 * @see
 */
@Component
public class ConnectDocument {

    @Autowired
    private Common common;

    /**
     * Con esta método compruebo el Status code de la respuesta que recibo al hacer la petición
     * EJM:
     * 		200 OK			300 Multiple Choices
     * 		301 Moved Permanently	305 Use Proxy
     * 		400 Bad Request		403 Forbidden
     * 		404 Not Found		500 Internal Server Error
     * 		502 Bad Gateway		503 Service Unavailable
     * @paramFromObject getLink()
     * @return Status Code
     *//*REGRESAR UN OBJETO CONNECTOBJECTRESPONSE*/
    public Connection_ connect(String link) throws Exception {
        //Response oResponse; ya no se usa
        Connection_ connection_ = new Connection_();
        connection_.setLink( link );
        //http://en.wikipedia.org/wiki/
        //System.out.println(URLEncoder.encode(common.cutStringPerformance(30, 0,Constants.HTTP_HEADER +link), "UTF-8"));

        try {//Constants.HTTP_HEADER +
            Connection connection = Jsoup.connect( connection_.getLink().replace("http", "https") ).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0").referrer("http://www.google.com").timeout(20*1000);
            // Connection connection = Jsoup.connect( connection_.getLink().replace("http", "https") )
            //oResponse = connection.execute(); || Jsoup.connect(Constants.HTTP_HEADER + connection_.getLink()
            connection_.setoDoc( connection.get()/*getHtmlDocument(connection)*/ );
            connection_.setStatus( connection.execute().statusMessage() );
            connection_.setStatusCode( connection.execute().statusCode()+"" );
        } catch (Exception e) {
            System.out.println("Exception to connect with the page: (" + connection_.getLink() + ") " + e.getMessage() + " " +e.getStackTrace());
            connection_ = tryConnect(connection_);
            if (connection_.getoDoc() == null) {
                connection_.setStatus(StatusHttpEnum.NOT_FOUND.getDescription());
                connection_.setStatusCode(StatusHttpEnum.NOT_FOUND.getKey());
                connection_.setoDoc(null);
            }
        }

        return connection_;
    }


    public Connection_ tryConnect(Connection_ connection_) throws UnsupportedEncodingException {
        //http://en.wikipedia.org/wiki/
        String convertLink = Constants.HTTPS_HEADER + "en.wikipedia.org/wiki/" + URLEncoder.encode(common.cutStringPerformance(29, 0, connection_.getLink()), "UTF-8");
        System.out.println(connection_.getLink()+ " - " +convertLink);

        try {//Constants.HTTP_HEADER +
            Connection connection = Jsoup.connect( convertLink.replace("http", "https") ).userAgent("Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:25.0) Gecko/20100101 Firefox/25.0").referrer("http://www.google.com").timeout(20*1000);
            // Connection connection = Jsoup.connect( connection_.getLink().replace("http", "https") )
            //oResponse = connection.execute(); || Jsoup.connect(Constants.HTTP_HEADER + connection_.getLink()
            connection_.setoDoc( connection.get()/*getHtmlDocument(connection)*/ );
            connection_.setStatus( connection.execute().statusMessage() );
            connection_.setStatusCode( connection.execute().statusCode()+"" );
        } catch (Exception e) {
            System.out.println("Exception to connect with the page: (" + connection_.getLink() + ") " + e.getMessage() + " " +e.getStackTrace());
            connection_.setStatus( StatusHttpEnum.NOT_FOUND.getDescription() );
            connection_.setStatusCode( StatusHttpEnum.NOT_FOUND.getKey() );
            connection_.setoDoc(null);
        }
        return connection_;
    }

}
