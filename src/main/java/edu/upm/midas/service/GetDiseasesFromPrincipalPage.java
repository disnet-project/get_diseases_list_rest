package edu.upm.midas.service;
import edu.upm.midas.common.util.Common;
import edu.upm.midas.common.util.TimeProvider;
import edu.upm.midas.component.extraction.ConnectDocument;
import edu.upm.midas.component.extraction.LoadSource;
import edu.upm.midas.model.extraction.Connection_;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gerardo on 28/06/2018.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className GetDiseasesFromPrincipalPage
 * @see
 */
@Service
public class GetDiseasesFromPrincipalPage {

    @Autowired
    private LoadSource loadSource;
    @Autowired
    private ConnectDocument connectDocument;

    @Autowired
    private Common common;
    @Autowired
    private TimeProvider timeProvider;


    public void extractFromWikipediaListOfDiseases() {
        //<editor-fold desc="VARIABLES DE INICO">
        Connection_ connection_;
        Document document;
        //</editor-fold>





    }


}
