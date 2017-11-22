package edu.upm.midas.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.Literal;
import com.hp.hpl.jena.rdf.model.RDFNode;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.sparql.engine.http.QueryEngineHTTP;
import edu.upm.midas.constants.Constants;
import edu.upm.midas.model.extract.Code;
import edu.upm.midas.model.extract.Disease;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Service;


import java.io.*;
import java.net.URLClassLoader;
import java.util.*;
import java.util.Map.Entry;

@Service
public class GetDiseasesFromDBPedia {

	@Autowired
	private ResourceLoader resourceLoader;

	private final String DISEASES_SPARQL_QUERY_FILE = Constants.DISEASES_SPARQL_QUERY_FILE;
	private final String DBPEDIA_SPARQL_ENDPOINT = Constants.DBPEDIA_SPARQL_ENDPOINT;


	public void getDiseasesFromDBPedia() {
		init();
	}


	public Map<Code, Disease> getDiseasesListFromDBPedia() {
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
			diseases = getListDiseasesDBPedia();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return diseases;
	}

	private void init() {
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
			Map<Code, Disease> diseases = getListDiseasesDBPedia();
			Set<Entry<Code, Disease>> allDS = diseases.entrySet();
			Iterator<Entry<Code, Disease>> it = allDS.iterator();
			int v = 0;
			while (it.hasNext()) {
				Entry<Code, Disease> ent = it.next();
				Disease dis = ent.getValue();
				//System.out.println("Disease:");
				//System.out.println(dis.toString());
				//System.out.println();

				System.out.println(v + ".Dis==>|NAME:| " + dis.getName()
//						+ " |DiseasesDB_CODE:| " +  dis.getDiseasesDBCode()
//						+ " | eMedicine_CODE: | " + dis.geteMedicineCode()
//						+ " | ICD9_CODE: | " + dis.getICD9Code()
//						+ " | ICD10_CODE: | " + dis.getICD10Code()
//						+ " | Mesh_CODE: | " + dis.getMeshCode()
//						+ " | OMIM_CODE: | " + dis.getOMIMCode()
//						+ " | MedlinePlus_CODE: | " + dis.getMedlinePlusCode()
						+ " |WIKIPEDIA_PAGE:|" + dis.getWikipediaPage()
//						+ " | DBPEDIA_PAGE: | " + dis.getURI()
//						+ " | FREEBASE_PAGE | " + dis.getFreebaseURL()
				);

				v++;
			}
			System.out.println("Total: " + v);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * M�todo para obtener el n�mero de subclases de una clase determinada.
	 * 
	 * @param uriClass
	 *            Recibe su URI.
	 * @param endpoint
	 *            Recibe el endpoint.
	 * @return Devuelve el n�mero de clases.
	 * @throws Exception
	 *             Puede lanzar una excepci�n.
	 */
	@SuppressWarnings("resource")
	private Map<Code, Disease> getListDiseasesDBPedia() throws Exception {
		Map<Code, Disease> diseases = new HashMap<Code, Disease>();
		Query query = null;
		QueryEngineHTTP qexec = null;
		String finalQuery = loadQuery(DISEASES_SPARQL_QUERY_FILE);
		query = QueryFactory.create(finalQuery);
		qexec = new QueryEngineHTTP(DBPEDIA_SPARQL_ENDPOINT, query);
		ResultSet results = qexec.execSelect();
		while (results.hasNext()) {
			QuerySolution qs = results.next();
			Resource dis = qs.getResource("?d");
			Disease disease = new Disease(dis.getURI());
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
