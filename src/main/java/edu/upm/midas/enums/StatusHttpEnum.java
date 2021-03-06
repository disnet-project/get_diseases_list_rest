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
public enum StatusHttpEnum {

    OK("200", "OK"),
    MULTIPLE_CHICES("300", "Multiple Choices"),
    MOVED_PERMANENTLY("301", "Moved Permanently"),
    USE_PROXY("305", "Use Proxy"),
    BAD_REQUEST("400", "Bad Request"),
    FORBIDDEN("403", "Forbidden"),
    NOT_FOUND("404", "Not Found"),
    INTERNAL_SERVER_ERROR("500", "Internal Server Error"),
    BAD_GATEWAY("502", "Bad Gateway"),
    SERV_UNAVAILABLE("503", "Service Unavailable");

    private String key;
    private String description;

    private StatusHttpEnum(String key, String description) {
        this.key = key;
        this.description = description;
    }

    public static StatusHttpEnum getEnum(String clave) {
        if (StringUtils.isNotBlank(clave)) {
            for (StatusHttpEnum estatus : StatusHttpEnum.values()) {
                if (clave.equals(estatus.getKey()))
                    return estatus;
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
