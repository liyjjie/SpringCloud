package com.jack.es;


import com.google.gson.GsonBuilder;
import com.jack.conf.EsConf;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：liyongjie
 * @ClassName ：EsClientFactory
 * @date ： 2019-11-29 09:46
 * @modified By：
 */
@Component
public class EsClientFactory {

    private JestClient jestClient;

    @Autowired
    private EsConf esConf;

    public void connect() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig.Builder(esConf.getUrl())
                .multiThreaded(true)
                .defaultMaxTotalConnectionPerRoute(esConf.getDefaultMaxTotalConnectionPerRoute())
                .maxTotalConnection(esConf.getMaxTotalConnection())
                .connTimeout(10000)
                .readTimeout(10000)
                .defaultCredentials("elastic", "nCqrS4gO")
                .gson(new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create())
                .build());
        jestClient = factory.getObject();
    }

    public JestClient getJestClient() {
        return jestClient;
    }
}
