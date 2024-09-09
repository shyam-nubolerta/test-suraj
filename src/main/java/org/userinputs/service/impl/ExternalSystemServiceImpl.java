package org.userinputs.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.userinputs.entity.ExternalServiceEntity;
import org.userinputs.external.request.User;
import org.userinputs.mapper.ExternalServiceMapper;
import org.userinputs.model.UserDTO;
import org.userinputs.repository.ExternalServiceRepository;
import org.userinputs.service.ExternalSystemService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.userinputs.service.impl.AuthenticationServiceImpl.AUTH_TOKEN_HEADER_NAME;

@Service
@Slf4j
public class ExternalSystemServiceImpl implements ExternalSystemService {

    private final ExternalServiceRepository externalServiceRepository;

    public ExternalSystemServiceImpl(ExternalServiceRepository externalServiceRepository) {
        this.externalServiceRepository = externalServiceRepository;
    }

    @Override
    public void sendUserInfo(UserDTO userDTO) {

        ExternalServiceEntity extService = externalServiceRepository.findByServicename("userService");
         MultiValueMap<String, String> headers = new HttpHeaders();
         headers.add(AUTH_TOKEN_HEADER_NAME,"users");
        HttpEntity<User> httpEntity = new HttpEntity<>(ExternalServiceMapper.mapUser(userDTO),headers);
        RestTemplate restTemplate = new RestTemplate();
        try {
            restTemplate.exchange(extService.getUrl(), HttpMethod.POST, httpEntity, String.class);
        }catch (Exception e){
            log.error("error sending down stream {}",e.getMessage() );
        }
    }
}
