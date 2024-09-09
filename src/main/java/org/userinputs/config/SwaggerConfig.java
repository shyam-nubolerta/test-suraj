/*
 * Copyright (c) Bank Julius Baer & Co. Ltd., 2020.
 * All Rights Reserved.
 *
 */
package org.userinputs.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(@Value("${spring.application.name}") String appName,
                                 @Value("${spring.application.version}") String appVersion,
                                 @Value("${spring.application.description}") String appDescription) {
        return new OpenAPI()
                .info(new Info()
                        .title(appName)
                        .version(appVersion)
                        .description(appDescription)
                        .termsOfService(""));
    }




}