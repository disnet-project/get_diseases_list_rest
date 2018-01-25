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

    @Value("${my.header.param.token.name}")
    public String HEADER_PARAM_TOKEN_NAME;

    /** Ruta del archivo XML source */
    public static final String DISEASES_SPARQL_QUERY_FILE = "/sparql/getDiseases.sparql";//"sparql/getDiseases.sparql";
    public static final String DBPEDIA_SPARQL_ENDPOINT = "http://dbpedia.org/sparql";
    public static final String EXTRACTION_HISTORY_FOLDER = "tmp/extraction_history/";//"extraction_history/"; //real= /tmp/extraction_history/

    public static final String SEMANTIC_TYPES[] = { "IDC-9", "IDC-10", "DiseasesDB", "MeSH", "lbpr", "lbtr" };
    public static final String INVALID_CODES[] = { "*", "," , "—", "–", "{}", "-1", "-"};


}
