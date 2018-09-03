package edu.upm.midas.controller;
import edu.upm.midas.common.util.TimeProvider;
import edu.upm.midas.constants.Constants;
import edu.upm.midas.data.relational.entities.addb.Album;
import edu.upm.midas.data.relational.entities.addb.AlbumPK;
import edu.upm.midas.data.relational.service.AlbumService;
import edu.upm.midas.service.GetDiseasesFromDBPedia;
import edu.upm.midas.service.Populate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

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
@RequestMapping(value = "${my.service.rest.request.mapping.general.url}")
public class ExtractionController {

    @Autowired
    private GetDiseasesFromDBPedia getDiseasesFromDBPedia;
    @Autowired
    private Populate populateService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private TimeProvider timeProvider;


    @RequestMapping(path = { "/retrieve" }, //wikipedia extraction
            method = RequestMethod.GET)
    public String extract() throws Exception {
        System.out.println("retrieve");
        getDiseasesFromDBPedia.getDiseasesFromDBPedia();
        return "Successful extraction and insertion in a DB!";
    }

    @RequestMapping(path = { "/populate" }, //wikipedia extraction
            method = RequestMethod.GET)
    public String populate() throws Exception {
        Album album = populateService.populate();
        if (album!=null) {
            System.out.println("Update list with the disease Safe List");
            populateService.populateAlbumWithDiseaseSafeList(Constants.WIKIPEDIA_SOURCE, album);
            populateService.updateDiseaseSafeList(Constants.WIKIPEDIA_SOURCE,  album);
            System.out.println("Update list with the disease Safe List... READY!");
        }
        return "Successful extraction and insertion in a DB!";
    }


    @RequestMapping(path = { "/populate/safe_list" }, //wikipedia extraction
            method = RequestMethod.GET)
    public String updateSafeList() throws Exception {
        AlbumPK albumPK = new AlbumPK();
        albumPK.setAlbumId("g0imwb1bdkbv");
        albumPK.setDate(timeProvider.convertSQLDateToUtilDate(timeProvider.stringToDate("2018-05-01")));
        Album album = albumService.findByIdNative(albumPK);
        System.out.println(album);
        if (album!=null) {
            populateService.updateDiseaseSafeList(Constants.WIKIPEDIA_SOURCE,  album);
            System.out.println("Update safe list... READY!");
        }
        return "Successful updating safe list";
    }

    @RequestMapping(path = { "/test" }, //wikipedia extraction
            method = RequestMethod.GET)
    public String test() throws Exception {
        populateService.writeJSONFile("hola GLG", "albumId", "myVersionGLG");
        return "ESCRITO";
    }

    @RequestMapping(path = { "/automatic_extraction" }, //wikipedia extraction
            method = RequestMethod.GET)
    public void automaticExtraction() throws Exception {
        try {
            Album album = null;
            System.out.println("Scheduled for the 15th of each month at midnight." + timeProvider.getNowFormatyyyyMMdd());
            album = populateService.populate();
            if (album!=null){
                System.out.println("Update list with the disease Safe List");
                populateService.populateAlbumWithDiseaseSafeList(Constants.WIKIPEDIA_SOURCE, album);
                System.out.println("Update list with the disease Safe List... READY!");
                System.out.println("Update disease Safe List");
                populateService.updateDiseaseSafeList(Constants.WIKIPEDIA_SOURCE,  album);
                System.out.println("Update disease Safe List... READY!");
            }
        }catch (Exception e){
            System.out.println("DISLISTERR (15thOfTheMonth): " + e.getMessage());
        }
    }

}
