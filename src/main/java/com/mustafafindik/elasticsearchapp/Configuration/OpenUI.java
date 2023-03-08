package com.mustafafindik.elasticsearchapp.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenUI {

    @Bean
    public OpenAPI customOpenAPI (@Value("${application-description}") String description,
                                  @Value("${application-version}") String version){
        return new OpenAPI()
                .info(new Info()
                        .title("Elasticsearch API")
                        .version(version)
                        .description(description)
                        .license(new License()
                                .name("Elasticsearch API License")));
    }
}
