package edu.upm.midas.controller;
import edu.upm.midas.service.GetDiseasesFromDBPedia;
import edu.upm.midas.service.Populate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by gerardo on 30/10/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className ExtractionController
 * @see
 */
@RestController
@RequestMapping("/api")
public class ExtractionController {

    @Autowired
    private GetDiseasesFromDBPedia getDiseasesFromDBPedia;
    @Autowired
    private Populate populateService;


    @RequestMapping(path = { "/disease_album/extract" }, //wikipedia extraction
            method = RequestMethod.GET)
    public String extract() throws Exception {
        getDiseasesFromDBPedia.getDiseasesFromDBPedia();
        return "Successful extraction and insertion in a DB!";
    }

    @RequestMapping(path = { "/disease_album/populate" }, //wikipedia extraction
            method = RequestMethod.GET)
    public String populate() throws Exception {
        populateService.populate();
        return "Successful extraction and insertion in a DB!";
    }

}
