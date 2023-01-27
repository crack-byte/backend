package com.tripshare.config;

import lombok.Getter;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class ElasticSearchConfig {
    @Value("elasticsearch.host")
    private String host;
    @Value("elasticsearch.port")
    private int port;

    @Bean
    RestClient restClient() {
        return RestClient.builder(
            new HttpHost(this.host, this.port)
        ).build();
    }


}
