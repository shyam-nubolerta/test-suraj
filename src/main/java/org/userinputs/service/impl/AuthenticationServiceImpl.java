package org.userinputs.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.userinputs.auth.ApiKeyAuthentication;
import org.userinputs.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {

    public static final String AUTH_TOKEN_HEADER_NAME = "X-API-KEY";

    @Value("${userInputs.authToken}")
    private String authToken;


    public Authentication getAuthentication(HttpServletRequest request) {
        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
        if (apiKey == null || !apiKey.equals(authToken)) {
            throw new BadCredentialsException("Invalid API Key");
        }

        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
    }
}
