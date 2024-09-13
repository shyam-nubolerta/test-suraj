package org.userinputs.controller.impl;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.userinputs.controller.CsrfController;


@RestController
@OpenAPIDefinition
@RequestMapping(path = "/")
public class CsrfControllerImpl implements CsrfController {

    public ResponseEntity<CsrfToken> getToken(final HttpServletRequest request) {
        return ResponseEntity.ok().body(CookieCsrfTokenRepository.withHttpOnlyFalse().generateToken(request));
    }
}
