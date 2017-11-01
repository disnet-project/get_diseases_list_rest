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
import org.springframework.stereotype.Service;

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
    @Autowired
    private Common commonService;

    public void populate() throws Exception{

        Map<Code, Disease> diseases = getDiseaseAlbumService.getDiseasesListFromDBPedia();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        List<Disease> diseaseList = new ArrayList<>();

        System.out.println("-------------------- POPULATE DATABASE --------------------");
        System.out.println("Populate start...");
        Set<Map.Entry<Code, Disease>> allDS = diseases.entrySet();
        Iterator<Map.Entry<Code, Disease>> it = allDS.iterator();
        Album album = albumHelper.insertIfExist(allDS.size());
        if (!commonService.isEmpty(album.getAlbumId())){
            int v = 0;
            while (it.hasNext()) {
                Map.Entry<Code, Disease> ent = it.next();
                Disease disease = ent.getValue();
                diseaseList.add(disease);
                System.out.println(v + ". Insert disease: " + disease.getName());
                String diseaseId = diseaseHelper.insertIfExist(disease);
                albumHelper.insertDiseases(album, diseaseId);
                v++;
            }
            //System.out.println(gson.toJson(diseaseList));
            writeJSONFile(gson.toJson(diseaseList), album.getAlbumId(), timeProviderService.getNowFormatyyyyMMdd());
            System.out.println("Total: " + v);
            System.out.println("End polulation.");
        }else{
            System.out.println("ERR.albumId empty!");
        }


    }


    public void writeJSONFile(String diseaseJsonBody, String albumId, String version) throws IOException {
        String fileName = version + "_" +albumId + ".adis";
        String path = Constants.EXTRACTION_HISTORY_FOLDER + fileName;
        File file = new File(path);
        BufferedWriter bW;

        if (!file.exists()){
            bW = new BufferedWriter(new FileWriter(file));
            bW.write(diseaseJsonBody);
            bW.close();
        }
    }

}
