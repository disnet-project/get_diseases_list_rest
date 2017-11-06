package edu.upm.midas.model.response.getDiseaseLinkList;

import edu.upm.midas.model.response.Disease;
import edu.upm.midas.model.response.ResponseFather;

import java.util.List;

public class ResponseGDLL extends ResponseFather{

    private List<Disease> diseases;


    public List<Disease> getDiseases() {
        return diseases;
    }

    public void setDiseases(List<Disease> diseases) {
        this.diseases = diseases;
    }
}
