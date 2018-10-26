package edu.upm.midas.service.jpa.helper;
import edu.upm.midas.common.util.Common;
import edu.upm.midas.common.util.UniqueId;
import edu.upm.midas.service.jpa.CodeService;
import edu.upm.midas.service.jpa.DiseaseCodeService;
import edu.upm.midas.service.jpa.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gerardo on 31/10/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project get_diseases_list_rest
 * @className CodeHelper
 * @see
 */
@Service
public class CodeHelper {

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



    public String insertIfExist(edu.upm.midas.model.extraction.Disease dis) {

        return "";

    }
}
