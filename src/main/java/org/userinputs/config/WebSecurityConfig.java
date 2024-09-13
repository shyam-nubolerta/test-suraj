package org.userinputs.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.userinputs.service.impl.AuthenticationServiceImpl;

import java.util.Arrays;
import java.util.Collections;


@Configuration
public class WebSecurityConfig {

    @Value("${spring.profiles.active:}")
    private String activeProfile;

    @Bean
    public SecurityFilterChain securityFilterChainBean(HttpSecurity httpSecurity) throws Exception{
          httpSecurity
                  .csrf(csrf -> {if(!StringUtils.equalsIgnoreCase(activeProfile,"test")){csrf.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());csrf.csrfTokenRequestHandler(new CsrfTokenRequestAttributeHandler());}
                  else{csrf.disable();}})
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers("/h2-console/**","/swagger-ui/**","/api-docs/**","/swagger-ui.html","/api/**","/actuator/**","/v2/**").permitAll())
                .cors(cors->cors.configurationSource(corsConfigurationSource())).formLogin(login-> login.disable());
        return httpSecurity.build();
    }

   @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        configuration.setAllowedHeaders(Arrays.asList("X-Requested-With", "Origin", "Content-Type", "Accept", "Authorization", AuthenticationServiceImpl.AUTH_TOKEN_HEADER_NAME));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
