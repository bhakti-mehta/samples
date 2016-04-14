package com.bhakti.casestudy.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * This is an initial representation of POJO from a YAML
 * This can represent details of JDBC drivers, connector settings etc
 *
 * @author Bhakti Mehta
 */
public class ServiceConfiguration  extends Configuration {
    @NotEmpty
    @JsonProperty
    private String template;

    @NotEmpty
    @JsonProperty
    private String defaultName = "Feeds";

    public String getTemplate() {
        return template;
    }

    public String getDefaultName() {
        return defaultName;
    }


}

