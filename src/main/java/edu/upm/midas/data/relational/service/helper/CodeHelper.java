package edu.upm.midas.data.relational.service.helper;
import edu.upm.midas.common.util.Common;
import edu.upm.midas.common.util.UniqueId;
import edu.upm.midas.data.relational.service.CodeService;
import edu.upm.midas.data.relational.service.DiseaseCodeService;
import edu.upm.midas.data.relational.service.ResourceService;
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



    public String insertIfExist(edu.upm.midas.model.extract.Disease dis) {

        return "";

    }
}
