package edu.upm.midas.service;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.upm.midas.common.util.Common;
import edu.upm.midas.common.util.TimeProvider;
import edu.upm.midas.constants.Constants;
import edu.upm.midas.data.relational.entities.addb.Album;
import edu.upm.midas.data.relational.service.helper.AlbumHelper;
import edu.upm.midas.data.relational.service.helper.DiseaseHelper;
import edu.upm.midas.model.extract.Code;
import edu.upm.midas.model.extract.Disease;
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

    @Autowired
    private GetDiseasesFromDBPedia getDiseaseAlbumService;

    @Autowired
    private DiseaseHelper diseaseHelper;
    @Autowired
    private AlbumHelper albumHelper;
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
    public void populate() throws Exception{

        Map<Code, Disease> diseases = getDiseaseAlbumService.getDiseasesListFromDBPedia();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Disease> diseaseList = new ArrayList<>();

        if (diseases != null) {
            System.out.println("-------------------- POPULATE DATABASE --------------------");
            System.out.println("Populate start...");
            Set<Map.Entry<Code, Disease>> allDS = diseases.entrySet();
            Iterator<Map.Entry<Code, Disease>> it = allDS.iterator();
            Album album = albumHelper.insertIfExist(allDS.size());
            if (album != null) {
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
                writeJSONFile(gson.toJson(diseaseList), album.getAlbumId(), timeProviderService.getNowFormatyyyyMMdd());
                System.out.println("Total: " + v);
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



    }


    /**
     * Método que escri
     *
     * @param diseaseJsonBody
     * @param albumId
     * @param version
     * @throws IOException
     */
    public void writeJSONFile(String diseaseJsonBody, String albumId, String version) throws IOException {
        String fileName = version + "_" +albumId + TERMINATION;//adis = disease album
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
