package edu.upm.midas.service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.upm.midas.common.util.Common;
import edu.upm.midas.common.util.TimeProvider;
import edu.upm.midas.constants.Constants;
import edu.upm.midas.service.jpa.AlbumService;
import edu.upm.midas.service.jpa.SafeDiseaseService;
import edu.upm.midas.service.jpa.SafeDiseaseUrlService;
import edu.upm.midas.service.jpa.SafeUrlService;
import edu.upm.midas.service.jpa.helper.AlbumHelper;
import edu.upm.midas.service.jpa.helper.DiseaseHelper;
import edu.upm.midas.model.extraction.Code;
import edu.upm.midas.model.extraction.Disease;
import edu.upm.midas.model.jpa.Album;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.*;

/**
 * Created by gerardo on 30/10/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className Populate
 * @see
 */
@Service
public class Populate {

    private static final Logger logger = LoggerFactory.getLogger(Populate.class);

    @Autowired
    private GetDiseasesFromDBPedia getDiseaseAlbumService;

    @Autowired
    private DiseaseHelper diseaseHelper;
    @Autowired
    private SafeDiseaseService safeDiseaseService;
    @Autowired
    private SafeUrlService safeUrlService;
    @Autowired
    private SafeDiseaseUrlService safeDiseaseUrlService;
    @Autowired
    private AlbumHelper albumHelper;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private TimeProvider timeProviderService;
    @Value("${my.service.extraction_history.dot_termination}")
    private String TERMINATION;
    @Autowired
    private Common commonService;/*COMMENT*/


    /**
     * @throws Exception
     */
    @Transactional
    public Album populate() throws Exception{

        Album album = null;
        Map<Code, Disease> dbpediaDiseases = getDiseaseAlbumService.getDiseasesListFromDBPedia(Constants.DBPEDIA_SOURCE);
        Map<Code, Disease> dbpedialiveDiseases = getDiseaseAlbumService.getDiseasesListFromDBPedia(Constants.DBPEDIALIVE_SOURCE);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Disease> diseaseList = new ArrayList<>();

        if (dbpediaDiseases != null) {
            System.out.println("-------------------- POPULATE DATABASE --------------------");
            System.out.println("Populate start (regular DBpedia)...");
            Set<Map.Entry<Code, Disease>> allDS = dbpediaDiseases.entrySet();
            Iterator<Map.Entry<Code, Disease>> it = allDS.iterator();
            album = albumHelper.insertIfExist(allDS.size());
            if (album != null) {

                //<editor-fold desc="POPULATE DBPEDIA">
//                insert(dbpediaDiseases, diseaseList, album, Constants.DBPEDIA_SOURCE);

                int v = 0;
                while (it.hasNext()) {
                    Map.Entry<Code, Disease> ent = it.next();
                    Disease disease = ent.getValue();
                    Code code = ent.getKey();
                    diseaseList.add(disease);
                    //if (disease.getName().equals("Köhler disease")){
                    System.out.println(v + ". Insert disease: " + disease.getName() + " - " + disease.getWikipediaPage());
                    String diseaseId = diseaseHelper.insertIfExist(disease);
                    albumHelper.insertDiseases(album, diseaseId);
                    v++;
                }
                albumHelper.update(album);
                //System.out.println(gson.toJson(diseaseList));
                writeJSONFile(gson.toJson(diseaseList), album.getAlbumId(), timeProviderService.getNowFormatyyyyMMdd(), Constants.DBPEDIA_SOURCE);
                System.out.println("Total: " + v);
                //</editor-fold>
                //<editor-fold desc="POPULATE DBPEDIALIVE">
//                insert(dbpedialiveDiseases, diseaseList, album, Constants.DBPEDIALIVE_SOURCE);
                System.out.println("Populate start (regular DBpedia-Live)...");
                allDS = dbpedialiveDiseases.entrySet();
                it = allDS.iterator();
                v = 0;
                while (it.hasNext()) {
                    Map.Entry<Code, Disease> ent = it.next();
                    Disease disease = ent.getValue();
                    Code code = ent.getKey();
                    diseaseList.add(disease);
                    //if (disease.getName().equals("Köhler disease")){
                    System.out.println(v + ". Insert disease (dbpedialive): " + disease.getName() + " - " + disease.getWikipediaPage());
                    String diseaseId = diseaseHelper.insertIfExist(disease);
                    albumHelper.insertDiseases(album, diseaseId);
                    v++;
                }
                albumHelper.update(album);
                //System.out.println(gson.toJson(diseaseList));
                writeJSONFile(gson.toJson(diseaseList), album.getAlbumId(), timeProviderService.getNowFormatyyyyMMdd(), Constants.DBPEDIALIVE_SOURCE);
                System.out.println("Total: " + v);
                //</editor-fold>
                System.out.println("End polulation.");
            } else {
                System.out.println("ERR.album empty!");
            }
            //Consultar la lista segura, hacer una comparación entre la lista segura y la recien insertada
            // e insertar aquellas enfermedades que no estan cerrar el album
        }
//        if (diseases != null) {
//            System.out.println("-------------------- POPULATE DATABASE --------------------");
//            System.out.println("Populate start...");
//            Set<Map.Entry<Code, Disease>> allDS = diseases.entrySet();
//            Iterator<Map.Entry<Code, Disease>> it = allDS.iterator();
//                int v = 0;
//                while (it.hasNext()) {
//                    Map.Entry<Code, Disease> ent = it.next();
//                    Disease disease = ent.getValue();
//                    Code code = ent.getKey();
//                    diseaseList.add(disease);
//                    System.out.println(v + ". Insert disease: " + disease.getName() + " - " + disease.getWikipediaPage());
//                    v++;
//                }
//                //System.out.println(gson.toJson(diseaseList));
//                System.out.println("Total: " + v);
//                System.out.println("End polulation.");
//            } else {
//                System.out.println("ERR.album empty!");
//            }
        return album;
    }


    public void insert(Map<Code, Disease> diseaseMap, List<Disease> generalDiseaseList, Album album, String source){
        List<Disease> diseaseList = new ArrayList<>();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        Set<Map.Entry<Code, Disease>> allDS = diseaseMap.entrySet();
        Iterator<Map.Entry<Code, Disease>> it = allDS.iterator();
        int v = 0;
        while (it.hasNext()) {
            Map.Entry<Code, Disease> ent = it.next();
            Disease disease = ent.getValue();
            Code code = ent.getKey();
            diseaseList.add(disease);
            generalDiseaseList.add(disease);
            //if (disease.getName().equals("Köhler disease")){
            System.out.println(v + ". Insert disease (" + source + "): " + disease.getName() + " - " + disease.getWikipediaPage());
            String diseaseId = diseaseHelper.insertIfExist(disease);
            albumHelper.insertDiseases(album, diseaseId);
            v++;
        }
        albumHelper.update(album);
        //System.out.println(gson.toJson(diseaseList));
        try {
            System.out.println("Save JSON album disease list from: " + source);
            writeJSONFile(gson.toJson(diseaseList), album.getAlbumId(), timeProviderService.getNowFormatyyyyMMdd(), source);
        }catch (Exception e){

        }
        System.out.println("Total: " + v);
    }


    @Transactional
    public void updateDiseaseSafeList(String sourceName, Album album) throws Exception{
        List<edu.upm.midas.model.response.Disease> albumDiseases = albumService.findLinksByIdAndSourceNameNative(album.getAlbumId(), album.getDate(), sourceName);
        for (edu.upm.midas.model.response.Disease disease: albumDiseases) {
            safeDiseaseService.insertNative(disease.getDiseaseId(), disease.getName());
            safeUrlService.insertNative(disease.getUrlId(), disease.getUrl());
            safeDiseaseUrlService.insertNative(disease.getDiseaseId(), disease.getUrlId(), disease.getSourceId());
            System.out.println("Disease inserted or updated: " + disease.getDiseaseId() +" | " + disease.getName());
        }
    }


    @Transactional
    public void populateAlbumWithDiseaseSafeList(String sourceName, Album album) throws Exception{

        List<edu.upm.midas.model.response.Disease> diseaseSafeList = safeDiseaseService.findAllDiseasesBySourceName(sourceName);

        for (edu.upm.midas.model.response.Disease disease: diseaseSafeList) {
            //safeDiseaseService.
            if (albumHelper.insertIgnoreDiseases(album, disease.getDiseaseId()) > 0)
                System.out.println("Inserted " + disease.getDiseaseId() + " | " + disease.getUrl());
            else
                System.out.println("Ignore " + disease.getDiseaseId() + " | " + disease.getUrl());
        }
        albumHelper.update(album);

    }




    /**
     * Método que escri
     *
     * @param diseaseJsonBody
     * @param albumId
     * @param version
     * @throws IOException
     */
    public void writeJSONFile(String diseaseJsonBody, String albumId, String version, String source) throws IOException {
        String fileName = version + "_" + source + "_" + albumId + TERMINATION;//adis = disease album
        String path = Constants.EXTRACTION_HISTORY_FOLDER + fileName;
        InputStream in = getClass().getResourceAsStream(path);
        //BufferedReader bL = new BufferedReader(new InputStreamReader(in));
        File file = new File(path);
        BufferedWriter bW;

        if (!file.exists()){
            bW = new BufferedWriter(new FileWriter(file));
            bW.write(diseaseJsonBody);
            bW.close();
        }
    }

}
