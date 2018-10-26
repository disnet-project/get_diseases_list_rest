package edu.upm.midas.common.util;

import edu.upm.midas.client_modules.authorization.token.component.JwtTokenUtil;
import edu.upm.midas.client_modules.authorization.token.service.TokenAuthorization;
import edu.upm.midas.constants.Constants;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by gerardo on 10/05/2017.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project ExtractionInformationDiseasesWikipedia
 * @className Validations
 * @see
 */
@Service
public class Common {

    @Autowired
    private TokenAuthorization tokenAuthorization;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private Common common;


    /**
     * @param token
     * @param start
     * @param end
     * @throws Exception
     */
    public void saveQueryRuntime(String token, String start, String end) throws Exception {
        try {
            //Aunque exista problema al insertar el runtime no hay problema con la ejecución de la consulta
            String queryId = jwtTokenUtil.getQueryIdJWTDecode(token);
            if (!common.isEmpty(queryId)) {
                HttpStatus response = tokenAuthorization.updateQueryRuntime(queryId, start, end);
                //System.out.println(response);
            }
        }catch (Exception e){}
    }

    public boolean isEmpty(String string) {
        if (string == null) {
            return true;
        }
        else {
            if (string.trim().equalsIgnoreCase("")) {
                return true;
            }
            else {
                return false;
            }

        }
    }

    public boolean isInvalidCode(String code){
        for (String i_code: Constants.INVALID_CODES){
            if (code.trim().equals(i_code)) return true;
        }
        return false;
    }

    public String cutString(String str) {
        return str = str.substring(0, str.length()-2);
    }


    /**
     * @param cutStart
     * @param cutFinal
     * @param str
     * @return
     */
    public String cutStringPerformance(int cutStart, int cutFinal, String str) {
        return str = str.substring(cutStart, str.length() - cutFinal);
    }


    public String replaceUnicodeToSpecialCharacters(String data){

        Pattern p = Pattern.compile("\\\\u(\\p{XDigit}{4})");
        Matcher m = p.matcher(data);
        StringBuffer buf = new StringBuffer(data.length());
        while (m.find()) {
            String ch = String.valueOf((char) Integer.parseInt(m.group(1), 16));
            m.appendReplacement(buf, Matcher.quoteReplacement(ch));
        }
        return m.appendTail(buf).toString();

    }

    public String replaceSpecialCharactersToUnicode(String text){
        //System.out.println(text);
        return StringEscapeUtils.escapeJava(text);
    }



    public void removeRepetedElementsList(List<String> elementsList){
        Set<String> linkedHashSet = new LinkedHashSet<String>();
        linkedHashSet.addAll(elementsList);
        elementsList.clear();
        elementsList.addAll(linkedHashSet);
    }



    public boolean itsFound(String originalStr, String findStr){
//        System.out.println("RECIBE itsFound: ORI:" + originalStr + " | FIND: " + findStr);
        return originalStr.trim().indexOf(findStr.trim()) != -1;// Retorna true si ha encontrado la subcadena en la cadena
    }


}
