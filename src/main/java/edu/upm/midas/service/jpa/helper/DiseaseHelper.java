package edu.upm.midas.service.jpa.helper;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.upm.midas.common.util.Common;
import edu.upm.midas.common.util.UniqueId;
import edu.upm.midas.model.jpa.*;
import edu.upm.midas.model.jpa.Code;
import edu.upm.midas.service.jpa.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

/**
 * Created by gerardo on 30/10/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className DiseaseHelper
 * @see
 */
@Service
public class DiseaseHelper {

    @Autowired
    private DiseaseService diseaseService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private SafeDiseaseService safeDiseaseService;
    @Autowired
    private UrlService urlService;
    @Autowired
    private SourceService sourceService;
    @Autowired
    private DiseaseUrlService diseaseUrlService;
    @Autowired
    private CodeService codeService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private DiseaseCodeService diseaseCodeService;

    @Autowired
    private UniqueId uniqueIdService;
    @Autowired
    private Common commonService;

    private static final Logger logger = LoggerFactory.getLogger(DiseaseHelper.class);
    @Autowired
    ObjectMapper objectMapper;


    public String insertIfExist(edu.upm.midas.model.extraction.Disease dis){
        Disease disease = diseaseService.findByNameNative( dis.getName() );
        if (disease != null) {
            System.out.println("    Found");
            insertUrls(dis, disease.getDiseaseId());
            insertCodes(dis, disease.getDiseaseId());
            return disease.getDiseaseId();
        }else {
            System.out.println("    Not Found");
            //Inicia inserción de la enfermedad
            String lastDiseaseId = getDiseaseId();
            System.out.println("LastId: "+ lastDiseaseId);
            diseaseService.insertNative(lastDiseaseId, dis.getName(), dis.getSourceId(), false, false);
            //Se insertan urls de la enfermedad
            insertUrls(dis, lastDiseaseId);
            insertCodes(dis, lastDiseaseId);
            return lastDiseaseId;
        }
    }


//    public String insertSafeDiseaseIfExist(edu.upm.midas.model.response.Disease disResp, String source){
//        edu.upm.midas.model.extraction.Disease dis = new edu.upm.midas.model.extraction.Disease(disResp.getUrl());
//        dis.setName(disResp.getName());
//        Disease disease = diseaseService.findByNameNative( dis.getName() );
//        if (disease != null) {
//            System.out.println("    Found");
//            insertUrls(dis, disease.getDiseaseId());
//            insertCodes(dis, disease.getDiseaseId());
//            return disease.getDiseaseId();
//        }else {
//            System.out.println("    Not Found");
//
//            //Inicia inserción de la enfermedad
//            String lastDiseaseId = getDiseaseId();
//            //System.out.println("LastId: "+ lastDiseaseId);
//            diseaseService.insertNative(lastDiseaseId, dis.getName());
//            //Se insertan urls de la enfermedad
//            insertUrls(dis, lastDiseaseId);
//            insertCodes(dis, lastDiseaseId);
//            return lastDiseaseId;
//        }
//    }


    public void insertUrls(edu.upm.midas.model.extraction.Disease disease, String diseaseId){
        String url = disease.getWikipediaPage();
        //Comprobar si existe la url de wikipedia
        if (!commonService.isEmpty(url)){//sourceId: 1 = wikipedia
            //System.out.println("wiki" + disease.getWikipediaPage());
            insertDiseaseUrl(1, "wikipedia", diseaseId, /*disease.getWikipediaPage()*/url, disease);
        }
        //Se inserta url de dbpedia
        url = disease.getURI();
        if (!commonService.isEmpty(url)){//2 = dbpedia
            //System.out.println("wiki" + disease.getURI());
            insertDiseaseUrl(2, "dbpedia", diseaseId, /*disease.getURI()*/url, disease);
        }
        //Se inserta url de freebase
        url = disease.getFreebaseURL();
        if (!commonService.isEmpty(url)){//3 = freebase
            //System.out.println("wiki" + disease.getFreebaseURL());
            insertDiseaseUrl(3, "freebase", diseaseId, /*disease.getFreebaseURL()*/url, disease);
        }
    }


    public void insertDiseaseUrl(int sourceId, String sourceName, String diseaseId, String url, edu.upm.midas.model.extraction.Disease disease){
        Url oUrl = null;
        Url oUrl_;
        //Se obtiene el id de la url a insertar
        String urlId = uniqueIdService.generateUrl(diseaseId, sourceId);
        //Se verifica que no existe el id
        oUrl = urlService.findByIdNative(urlId);
        oUrl_ = urlService.findByUrlNative(url);
        //Si no existe se inserta
        if (oUrl == null && oUrl_ == null){//System.out.println("ENTRA");
            //Inserta url
            urlService.insertNative(urlId, url);
            //Busca el id de la fuente wikipedia
            String sourceId_ = sourceService.findIdByNameNative(sourceName);
            //Forma la llave de disease_url para buscar
            DiseaseUrlPK diseaseUrlPK = new DiseaseUrlPK();
            diseaseUrlPK.setDiseaseId(diseaseId);
            diseaseUrlPK.setUrlId(urlId);
            diseaseUrlPK.setSourceId(sourceId_);
            //Buscar si existe disease_url para no insertarla
            DiseaseUrl diseaseUrl = diseaseUrlService.findById(diseaseUrlPK);
            if (diseaseUrl == null) {
                //Inserta disease_url
                diseaseUrlService.insertNative(diseaseId, urlId, sourceId_);
            }
        }else if (oUrl != null && oUrl_ == null){//System.out.println("FUERA ALTERNATIVE");
            urlId = verifyId(diseaseId, sourceId);
            //Inserta url
            urlService.insertNative(urlId, url);
            //Busca el id de la fuente wikipedia
            String sourceId_ = sourceService.findIdByNameNative(sourceName);
            //Forma la llave de disease_url para buscar
            DiseaseUrlPK diseaseUrlPK = new DiseaseUrlPK();
            diseaseUrlPK.setDiseaseId(diseaseId);
            diseaseUrlPK.setUrlId(urlId);
            diseaseUrlPK.setSourceId(sourceId_);
            //Buscar si existe disease_url para no insertarla
            DiseaseUrl diseaseUrl = diseaseUrlService.findById(diseaseUrlPK);
            if (diseaseUrl == null) {
                //Inserta disease_url
                diseaseUrlService.insertNative(diseaseId, urlId, sourceId_);
            }
        }
    }

    public String verifyId(String diseaseId, int sourceId){
        //Se obtiene el id de la url a insertar + 1
        Url oUrl;
        String urlId = "";
        do {
            urlId = uniqueIdService.generateUrl_alternative(diseaseId, sourceId);
            oUrl = urlService.findByIdNative(urlId);
        }while (oUrl != null);
        return urlId;
    }


    public void insertCodes(edu.upm.midas.model.extraction.Disease disease, String diseaseId){
        String codeId = disease.getDiseasesDBCode();
        //Comprobar si existe
        if (!commonService.isEmpty(codeId) && !commonService.isInvalidCode(codeId)){
            insertCodesResource("DiseasesDB", codeId, diseaseId, disease);
        }
        codeId = disease.geteMedicineCode();
        if (!commonService.isEmpty(codeId) && !commonService.isInvalidCode(codeId)){
            insertCodesResource("eMedicine", codeId, diseaseId, disease);
        }
        codeId = disease.getICD9Code();
        if (!commonService.isEmpty(codeId) && !commonService.isInvalidCode(codeId)){
            insertCodesResource("IDC-9", codeId, diseaseId, disease);
        }
        codeId = disease.getICD10Code();
        if (!commonService.isEmpty(codeId) && !commonService.isInvalidCode(codeId)){
            insertCodesResource("IDC-10", codeId, diseaseId, disease);
        }
        codeId = disease.getMeshCode();
        if (!commonService.isEmpty(codeId) && !commonService.isInvalidCode(codeId)){
            insertCodesResource("MeSH", codeId, diseaseId, disease);
        }
        codeId = disease.getOMIMCode();
        if (!commonService.isEmpty(codeId) && !commonService.isInvalidCode(codeId)){
            insertCodesResource("OMIM", codeId, diseaseId, disease);
        }
        codeId = disease.getMedlinePlusCode()+"";
        if (!commonService.isEmpty(codeId) && !commonService.isInvalidCode(codeId)){
            insertCodesResource("MedlinePlus", codeId, diseaseId, disease);
        }
    }


    public void insertCodesResource(String resourceName, String codeId, String diseaseId, edu.upm.midas.model.extraction.Disease disease){
        int resourceId = resourceService.findIdByNameQuery(resourceName);

        CodePK codePK = new CodePK();
        codePK.setCodeId(codeId);
        codePK.setResourceId(resourceId);
        Code code = codeService.findByIdNative(codePK);
        if (code == null){
            codeService.insertNative(codeId, resourceId);
            insertDiseaseCode(codePK, diseaseId);
        }else{
            //insertar sus disease_code, si los tiene
            insertDiseaseCode(codePK, diseaseId);
        }
    }


    public void insertDiseaseCode(CodePK codePK, String diseaseId){
        //insertar sus disease_code, si los tiene
        DiseaseCodePK diseaseCodePK = new DiseaseCodePK();
        diseaseCodePK.setCodeId(codePK.getCodeId());
        diseaseCodePK.setDiseaseId(diseaseId);
        diseaseCodePK.setResourceId(codePK.getResourceId());
        DiseaseCode diseaseCode = diseaseCodeService.findByIdNative(diseaseCodePK);
        if (diseaseCode == null){
            diseaseCodeService.insertNative(diseaseCodePK.getDiseaseId(), diseaseCodePK.getCodeId(), diseaseCodePK.getResourceId());
        }
    }


    public String deleteHttp(String url){
        return commonService.cutStringPerformance(7, 0, url);
    }


    public String getDiseaseId(){
        String id = diseaseService.findLastIdNative();
        if (!commonService.isEmpty(id)){
            int last = Integer.parseInt( commonService.cutStringPerformance(3, 0, id ) );
            return uniqueIdService.generateDisease( last +1 );
        }else{
            return uniqueIdService.generateDisease(1);
        }
    }


    public List<edu.upm.midas.model.response.Disease> getDiseasesFromDiseaseSafeListAndLastDiseaseAlbum(Date version, String sourceName){
        Date penultimateAlbumDate = albumService.getPenultimateDiseaseAlbumDateBySourceNative(false, version, sourceName);

        return diseaseService.getAllDiseasesFromDiseaseSafeListAndLastDiseaseAlbumBySource(version, penultimateAlbumDate, sourceName);

    }


}
