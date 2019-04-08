package edu.upm.midas.controller;
import edu.upm.midas.common.util.TimeProvider;
import edu.upm.midas.constants.Constants;
import edu.upm.midas.model.jpa.Album;
import edu.upm.midas.model.jpa.AlbumPK;
import edu.upm.midas.scheduling.ExtractionScheduling;
import edu.upm.midas.service.jpa.AlbumService;
import edu.upm.midas.service.GetDiseasesFromDBPedia;
import edu.upm.midas.service.Populate;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

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
    private ExtractionScheduling extractionScheduling;
    @Autowired
    private Populate populateService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private TimeProvider timeProvider;


    @RequestMapping(path = { "/retrieve" }, //wikipedia extraction
            method = RequestMethod.GET,
            params = {"source"})
    public String extract(@RequestParam(value = "source") @Valid @NotBlank @NotNull @NotEmpty String source) throws Exception {
        System.out.println("retrieve");
        getDiseasesFromDBPedia.getDiseasesFromDBPedia(source);
        return "Successful extraction and insertion in a DB!";
    }

    @RequestMapping(path = { "/populate" }, //wikipedia extraction
            method = RequestMethod.GET)
    public String populate() throws Exception {
        Album album = populateService.populate();
        if (album!=null) {
            System.out.println("Update list with the disease Safe List");
            populateService.populateAlbumWithDiseaseSafeListAndLastDiseaseAlbum(Constants.WIKIPEDIA_SOURCE, album);
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
            method = RequestMethod.GET,
            params = {"source"})
    public String test(@RequestParam(value = "source") @Valid @NotBlank @NotNull @NotEmpty String source) throws Exception {
//        populateService.writeJSONFile("hola GLG", "albumId", "myVersionGLG", source);
        AlbumPK albumPK = new AlbumPK();
        albumPK.setAlbumId("gsa77ipg4c2y");
        albumPK.setDate(timeProvider.convertSQLDateToUtilDate(timeProvider.stringToDate("2019-04-01")));
        Album album = albumService.findByIdNative(albumPK);
        populateService.populateAlbumWithDiseaseSafeListAndLastDiseaseAlbum(Constants.WIKIPEDIA_SOURCE, album);
        return "ESCRITO";
    }

    @RequestMapping(path = { "/automatic_extraction" }, //wikipedia extraction
            method = RequestMethod.GET)
    public void automaticExtraction() throws Exception {
        extractionScheduling.extractionEveryFirstDayOfTheMonth();
    }

}
