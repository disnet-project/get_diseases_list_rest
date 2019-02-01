package edu.upm.midas.model.extraction;

public class Disease {

	private String name;
	private String URI;
	private String wikipediaPage;
	private String ICD9Code;
	private String ICD10Code;
	private String diseasesDBCode;
	private String meshCode;
	private int medlinePlusCode = -1;
	private String OMIMCode;
	private String eMedicineCode;
	private String freebaseURL;
	private String sourceId;

	public Disease(String uri) {
		this.URI = uri;
	}

	public Disease(String uri, String sourceId) {
		this.URI = uri;
		this.sourceId = sourceId;
	}

	public void setEMedicineCode(String e) {
		this.eMedicineCode = e;
	}
	public void setOMIMCode(String o) {
		this.OMIMCode = o;
	}
	public void setMedlinePlusCode(int m) {
		this.medlinePlusCode = m;
	}
	public void setMeSHCode(String m) {
		this.meshCode = m;
	}
	public void setDiseasesDBCode(String d) {
		this.diseasesDBCode = d;
	}
	public void setICD10Code(String i) {
		this.ICD10Code = i;
	}
	public void setICD9Code(String i) {
		this.ICD9Code = i;
	}
	public void setWikipediaPage(String wp) {
		this.wikipediaPage = wp;
	}

	public String toString() {
		String ret = "";
		ret += "\tURI: " + this.URI + "\n";
		ret += "\tName: " + this.name + "\n";
		ret += "\tWikipedia Page: " + this.wikipediaPage + "\n";
		ret += "\tICD9 Code: " + this.ICD9Code + "\n";
		ret += "\tICD10 Code: " + this.ICD10Code + "\n";
		ret += "\tDiseases DB Code: " + this.diseasesDBCode + "\n";
		ret += "\tMeSH ID: " + this.meshCode + "\n";
		ret += "\tMedlinePlus Code: " + this.medlinePlusCode + "\n";
		ret += "\tOMIM Code: " + this.OMIMCode + "\n";
		ret += "\teMedicine Code: " + this.eMedicineCode + "\n";
		ret += "\tFreebase URL: " + this.freebaseURL + "\n";
		return ret;
	}
	public String getURI() {
		return this.URI;
	}
	public String getName() {
		return name;
	}
	public String getWikipediaPage() {
		return wikipediaPage;
	}
	public String getICD9Code() {
		return ICD9Code;
	}
	public String getICD10Code() {
		return ICD10Code;
	}
	public String getDiseasesDBCode() {
		return diseasesDBCode;
	}
	public String getMeshCode() {
		return meshCode;
	}
	public int getMedlinePlusCode() {
		return medlinePlusCode;
	}
	public String getOMIMCode() {
		return OMIMCode;
	}
	public String geteMedicineCode() {
		return eMedicineCode;
	}
	public String getFreebaseURL() {
		return freebaseURL;
	}

	public void setName(String n) {
		this.name = n;
	}

	public void setFreebaseURL(String fbu) {
		this.freebaseURL = fbu;
	}

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public String getDiseaseConcept() {
		if (this.freebaseURL != null) {
			String conc = this.freebaseURL.replaceAll("http://rdf.freebase.com/ns/", "");
			System.out.println(conc);
			return conc;
		}
		return null;
	}
	
	
}
