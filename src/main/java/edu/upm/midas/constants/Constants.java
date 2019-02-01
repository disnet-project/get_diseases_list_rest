package edu.upm.midas.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created by gerardo on 27/3/17.
 * @project ExtractionInformationWikipedia
 * @version ${<VERSION>}
 * @author Gerardo Lagunes G.
 * @className Constants
 * @see
 */
@Component
public class Constants {

    public static final String HTTP_HEADER = "https://";
    public static final String VERSION_PROJECT = "1.0";

    public static final String LOCALHOST = "localhost";

    public final static String ERR_NO_PARAMETER = "No parameter was sent";
    public final static String ERR_EMPTY_PARAMETER = "Empty parameter";

    public final static String OK = "OK";
    public final static String RESPONSE_INVALID_SOURCES = "Invalid source list";
    public final static String RESPONSE_SEMANTIC_TYPES = "Invalid semantic type list";

    public final static String WIKIPEDIA_SOURCE = "wikipedia";
    public final static String PUBMED_SOURCE = "pubmed";
    public final static String DBPEDIA_SOURCE = "dbpedia";
    public final static String DBPEDIALIVE_SOURCE = "dbpedialive";

    @Value("${my.header.param.token.name}")
    public String HEADER_PARAM_TOKEN_NAME;


    public static final String DOT_JSON = ".json";
    public static final String DOT_XML = ".xml";



    /** Ruta del archivo XML source */
    public static final String DISEASES_SPARQL_QUERY_FILE = "/sparql/getDiseases.sparql";//"sparql/getDiseases.sparql";
    public static final String DBPEDIA_SPARQL_ENDPOINT = "http://dbpedia.org/sparql";
    public static final String DBPEDIALIVE_SPARQL_ENDPOINT = "http://dbpedia-live.openlinksw.com/sparql";
    public static final String EXTRACTION_HISTORY_FOLDER = "tmp/extraction_history/";//"extraction_history/"; //real= /tmp/extraction_history/

    public static final String SEMANTIC_TYPES[] = { "IDC-9", "IDC-10", "DiseasesDB", "MeSH", "lbpr", "lbtr" };
    public static final String INVALID_CODES[] = { "*", "," , "—", "–", "{}", "-1", "-"};

    /** Ruta del archivo XML de configuracion */
    public static final String XML_CONFIG_FOLDER = "parameters/";
    public static final String XML_CONFIG_FILE = "sources";

    /** Constantes para leer XML */
    public static final String XML_ROOT_TAG = "source";
    public static final String XML_TAG_SECTIONS = "sections";
    public static final String XML_TAG_HIGHLIGTS = "highlights";
    public static final String XML_TAG_LINKS = "links";
    public static final String XML_TAG_NAME = "name";
    public static final String XML_TAG_LINK = "link";
    public static final String XML_ATT_CONSULT = "consult";
    public static final String XML_ATT_CONSULT_YES = "y";
    public static final String XML_ATT_CONSULT_NO = "n";
    public static final String XML_ATT_ID = "id";
    public static final String XML_ATT_TYPE = "type";
    public static final String XML_ATT_CLASS = "class";
    public static final String XML_ATT_NAME = "name";
    public static final String XML_ATT_DESC = "desc";


    /**
     * XML_HIGHLIGHTS HL Constantes para consultar
     */
    public static final String XML_HL_DISEASENAME = "diseasename";
    public static final String XML_HL_INFOBOX = "infobox";
    public static final String XML_HL_EXTERNAL_TEXT = "externalresoruce";
    public static final String XML_HL_PLAIN_LIST = "plainlist";
    public static final String XML_HL_HORIZONTAL_LIST = "horizontallist";
    public static final String XML_HL_TEXT_ALIGN_CENTER = "textaligncenter";


}
