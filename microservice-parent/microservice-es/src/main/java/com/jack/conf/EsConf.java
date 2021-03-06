package com.jack.conf;


import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author ：liyongjie
 * @ClassName ：EsConf
 * @date ： 2019-11-29 09:45
 * @modified By：
 */
@ConfigurationProperties(prefix = "es_config")
public class EsConf {

    private String url;

    private Integer defaultMaxTotalConnectionPerRoute;

    private Integer maxTotalConnection;

    private String index;

    private String type;

    private String indexTwo;

    private String typeTwo;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getDefaultMaxTotalConnectionPerRoute() {
        return defaultMaxTotalConnectionPerRoute;
    }

    public void setDefaultMaxTotalConnectionPerRoute(Integer defaultMaxTotalConnectionPerRoute) {
        this.defaultMaxTotalConnectionPerRoute = defaultMaxTotalConnectionPerRoute;
    }

    public Integer getMaxTotalConnection() {
        return maxTotalConnection;
    }

    public void setMaxTotalConnection(Integer maxTotalConnection) {
        this.maxTotalConnection = maxTotalConnection;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIndexTwo() {
        return indexTwo;
    }

    public void setIndexTwo(String indexTwo) {
        this.indexTwo = indexTwo;
    }

    public String getTypeTwo() {
        return typeTwo;
    }

    public void setTypeTwo(String typeTwo) {
        this.typeTwo = typeTwo;
    }

    @Override
    public String toString() {
        return "EsConf{" +
                "url='" + url + '\'' +
                ", defaultMaxTotalConnectionPerRoute=" + defaultMaxTotalConnectionPerRoute +
                ", maxTotalConnection=" + maxTotalConnection +
                ", index='" + index + '\'' +
                ", type='" + type + '\'' +
                ", indexTwo='" + indexTwo + '\'' +
                ", typeTwo='" + typeTwo + '\'' +
                '}';
    }
}
