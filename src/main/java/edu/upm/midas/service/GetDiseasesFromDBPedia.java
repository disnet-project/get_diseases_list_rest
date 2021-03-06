package edu.upm.midas.service;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.engine.http.QueryEngineHTTP;
import edu.upm.midas.constants.Constants;
import edu.upm.midas.enums.SourceEnum;
import edu.upm.midas.model.extraction.Code;
import edu.upm.midas.model.extraction.Disease;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;


import java.io.*;
import java.util.*;
import java.util.Map.Entry;

@Service
public class GetDiseasesFromDBPedia {

	private static final Logger logger = LoggerFactory.getLogger(GetDiseasesFromDBPedia.class);

	@Autowired
	private ResourceLoader resourceLoader;

	private final String DISEASES_SPARQL_QUERY_FILE = Constants.DISEASES_SPARQL_QUERY_FILE;
	private final String DISEASES_SECOND_SPARQL_QUERY_FILE = Constants.DISEASES_SECOND_SPARQL_QUERY_FILE;
	private final String DBPEDIA_SPARQL_ENDPOINT = Constants.DBPEDIA_SPARQL_ENDPOINT;
	private final String DBPEDIALIVE_SPARQL_ENDPOINT = Constants.DBPEDIALIVE_SPARQL_ENDPOINT;


	public void getDiseasesFromDBPedia(String source, String query) {
		init(source, query);
	}


	public Map<Code, Disease> getDiseasesListFromDBPedia(String source, String query) {
		Map<Code, Disease> diseases = null;
		try {
			/* El m�todo getListDiseasesDBPedia() es el encargado de hacer la query SPARQL
			 * que se encuentra en el fichero "sparql/getDiseases.sparql" y obtener todos los art�culos
			 * de Wikipedia categorizados como "Disease".
			 *
			 * El resto del c�digo es para recorrer el mapa y obtener la key (Objeto Code) y el value (Objeto Disease)
			 * y poder a trav�s del objeto Disease obtener la p�gina de Wikipedia. En realidad la consulta obtiene
			 * un mont�n adicional de informaci�n relacionada codificaciones (OMIM, icd, etc).
			 *
			 * Se puede consultar los objetos para ver sus datos.
			*/
			//Si la lista de enlaces es nula o no tiene elementos, espera una hora para realizar otra petición y así
			//hasta que se consiga una respuesta adecuada
			//Esto se realiza para garantizar que el proceso de población tenga una lista con la cual trabajar.
			while (true){
				diseases = getListDiseasesDBPedia(source, query);
				if (diseases!=null) {
					if (diseases.size()>0) break;
				}
				logger.info("Wait (1 hour = 3600000 mls) for another disease list request to " + source + ", because its response is null.");
				Thread.sleep(3600000);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			logger.error("Error to read the " + source + " - " + query + " response", e);
		}
		return diseases;
	}

	private void init(String source, String query) {
		try {
			/* El m�todo getListDiseasesDBPedia() es el encargado de hacer la query SPARQL
			 * que se encuentra en el fichero "sparql/getDiseases.sparql" y obtener todos los art�culos
			 * de Wikipedia categorizados como "Disease".
			 * 
			 * El resto del c�digo es para recorrer el mapa y obtener la key (Objeto Code) y el value (Objeto Disease)
			 * y poder a trav�s del objeto Disease obtener la p�gina de Wikipedia. En realidad la consulta obtiene
			 * un mont�n adicional de informaci�n relacionada codificaciones (OMIM, icd, etc).
			 * 
			 * Se puede consultar los objetos para ver sus datos.
			*/
			Map<Code, Disease> diseases = getListDiseasesDBPedia(source, query);
			Set<Entry<Code, Disease>> allDS = diseases.entrySet();
			Iterator<Entry<Code, Disease>> it = allDS.iterator();
			System.out.println("Disease list size: " + diseases.size());
			int v = 0;
			while (it.hasNext()) {
				Entry<Code, Disease> ent = it.next();
				Disease dis = ent.getValue();
				Code code = ent.getKey();
				//System.out.println("Disease:");
				//System.out.println(dis.toString());
				//System.out.println();
				v++;
				System.out.println(v + ".Dis==>|NAME:| " + dis.getName()
//						+ " |DiseasesDB_CODE:| " +  dis.getDiseasesDBCode()
//						+ " | eMedicine_CODE: | " + dis.geteMedicineCode()
//						+ " | ICD9_CODE: | " + dis.getICD9Code()
//						+ " | ICD10_CODE: | " + dis.getICD10Code()
//						+ " | Mesh_CODE: | " + dis.getMeshCode()
//						+ " | OMIM_CODE: | " + dis.getOMIMCode()
//						+ " | MedlinePlus_CODE: | " + dis.getMedlinePlusCode()
						+ " |WIKIPEDIA_PAGE:|" + dis.getWikipediaPage() + " KEY:" + code.keys.toString()
//						+ " | DBPEDIA_PAGE: | " + dis.getURI()
//						+ " | FREEBASE_PAGE | " + dis.getFreebaseURL()
				);


			}
			System.out.println("Total: " + v);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			logger.error("Error to read the " + source + " query response ", e);
		}
	}


	/**
	 * M�todo para obtener el n�mero de subclases de una clase determinada.
	 * 
	 * @param source
	 * @return Devuelve el n�mero de clases.
	 * @throws Exception
	 *             Puede lanzar una excepci�n.
	 */
	@SuppressWarnings("resource")
	private Map<Code, Disease> getListDiseasesDBPedia(String source, String queryType) throws Exception {
		Map<Code, Disease> diseases = new HashMap<Code, Disease>();
		Query query = null;
		QueryEngineHTTP qexec = null;
		String finalQuery = "";
		if (queryType.equalsIgnoreCase(Constants.MAIN_QUERY)) {
			finalQuery = loadQuery(DISEASES_SPARQL_QUERY_FILE);
		}else{
			finalQuery = loadQuery(DISEASES_SECOND_SPARQL_QUERY_FILE);
            //System.out.println("SEGUNDO: " + finalQuery);
		}
		query = QueryFactory.create(finalQuery);

		if (source.equals(Constants.DBPEDIA_SOURCE)) {
			qexec = new QueryEngineHTTP(DBPEDIA_SPARQL_ENDPOINT, query);
		}else {
			qexec = new QueryEngineHTTP(DBPEDIALIVE_SPARQL_ENDPOINT, query);
		}
		ResultSet results = qexec.execSelect();
		while (results.hasNext()) {
			QuerySolution qs = results.next();
			Resource dis = qs.getResource("?d");

			Disease disease = new Disease(dis.getURI(), SourceEnum.getEnumByDescription(source).getKey());
			Literal name = qs.getLiteral("?dn");
			disease.setName(name.getString());
			Resource wp = qs.getResource("?wikiPage");
			disease.setWikipediaPage(wp.getURI());
			Literal icd9 = qs.getLiteral("?icd9");
			if (icd9 != null)
				disease.setICD9Code(icd9.getString());
			Literal icd10 = qs.getLiteral("?icd10");
			if (icd10 != null)
				disease.setICD10Code(icd10.getString());
			Literal disDB = qs.getLiteral("?disDB");
			if (disDB != null)
				disease.setDiseasesDBCode(disDB.getString());
			Literal meshID = qs.getLiteral("?meshID");
			if (meshID != null)
				disease.setMeSHCode(meshID.getString());
			RDFNode mlp = qs.get("?mlp");
			if (mlp != null) {
				try {
					disease.setMedlinePlusCode(mlp.asLiteral().getInt());
				} catch (Exception e) {
				}
			}
			Literal omim = qs.getLiteral("?omim");
			if (omim != null)
				disease.setOMIMCode(omim.getString());
			Literal emed = qs.getLiteral("?emed");
			if (emed != null)
				disease.setEMedicineCode(emed.getString());
			Resource frbase = qs.getResource("?frbase");
			if (frbase != null)
				disease.setFreebaseURL(frbase.getURI());

			LinkedList<String> codes = new LinkedList<String>();
			if (icd9 != null) {
				codes.add(icd9.getString());
			}
			if (icd10 != null) {
				codes.add(icd10.getString());
			}
			if (disDB != null) {
				codes.add(disDB.getString());
			}
			if (meshID != null) {
				codes.add(meshID.getString());
			}
			if (omim != null) {
				codes.add(omim.getString());
			}
			if (emed != null) {
				codes.add(emed.getString());
			}

			diseases.put(new Code(codes), disease);
		}
		return diseases;
	}

	/**
	 * Method to load the query from a file.
	 * 
	 * @param f
	 *            Receives the file.
	 * @return Returns the query.
	 * @throws Exception
	 *             It can throws an exception.
	 */
	private String loadQuery(String f) throws Exception {
		String str = "";
		org.springframework.core.io.Resource fileResource =resourceLoader.getResource("classpath:" + f);
		InputStream in = getClass().getResourceAsStream(f);
		BufferedReader bL = new BufferedReader(new InputStreamReader(in));
		while (bL.ready()) {
			String rd = bL.readLine();
			str += rd + "\n";
		}
		bL.close();
		return str;
	}

}
