package edu.upm.midas.enums;

import org.apache.commons.lang.StringUtils;

/**
 * Created by gerardo on 6/4/17.
 *
 * @author Gerardo Lagunes G. ${EMAIL}
 * @version ${<VERSION>}
 * @project ExtractionInformationWikipedia
 * @className StatusHttpEnum
 * @see
 */
public enum SourceEnum {

    DBPEDIA("SO02", "dbpedia"),
    DBPEDIALIVE("SO04", "dbpedialive");

    private String key;
    private String description;

    private SourceEnum(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public static SourceEnum getEnumByKey(String clave) {
        if (StringUtils.isNotBlank(clave)) {
            for (SourceEnum source : SourceEnum.values()) {
                if (clave.equals(source.getKey()))
                    return source;
            }
        }
        return null;
    }

    public static SourceEnum getEnumByDescription(String description) {
        //System.out.println("source_description: " + description);
        if (StringUtils.isNotBlank(description)) {
            for (SourceEnum source : SourceEnum.values()) {
                if (description.equalsIgnoreCase(source.getDescription()))
                    return source;
            }
        }
        return null;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
