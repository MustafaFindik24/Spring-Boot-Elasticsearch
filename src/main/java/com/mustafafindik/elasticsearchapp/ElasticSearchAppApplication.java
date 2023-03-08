package com.mustafafindik.elasticsearchapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@EnableElasticsearchRepositories
public class ElasticSearchAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElasticSearchAppApplication.class, args);
    }

}
