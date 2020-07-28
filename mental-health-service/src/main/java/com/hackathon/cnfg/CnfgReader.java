

package com.hackathon.cnfg;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties
//@PropertySource(value = "file:${config.location}IGSMyFltArptCabnFlwnVst.properties")
public class CnfgReader {
    @Autowired
    private Environment env;

    @Value("#{'${allowedOrigins}'.split(',')}")
    private List<String> allowedOrigins = new ArrayList<>();

    public List<String> getAllowedOrigins() {
        return allowedOrigins;
    }

    public String getPropValue(String prpy) {
        return env.getProperty(prpy);
    }

    public void setAllowedOrigins(List<String> allowedOrigins) {
        this.allowedOrigins = allowedOrigins;
    }

}
