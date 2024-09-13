package org.userinputs.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.userinputs.entity.UserEntity;
import org.userinputs.model.UserDTO;
import org.userinputs.repository.UserRepository;
import org.userinputs.service.impl.AuthenticationServiceImpl;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class UserControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
      void testGetAlllUsers() throws Exception {

        URL url = new URL("http://localhost:" + port + "/api/v1/user/getUserDetails");
        HttpHeaders headers = new HttpHeaders();
        headers.add(AuthenticationServiceImpl.AUTH_TOKEN_HEADER_NAME,"users");
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(url.toURI(), HttpMethod.GET,requestEntity, String.class);
        assertNotNull(response);

        TypeReference<List<UserDTO>> typeRef
                = new TypeReference<>() {};
        ObjectMapper mapper = new ObjectMapper();
        List<UserDTO> users = mapper.readValue(response.getBody(), typeRef);
        assertNotNull(users);

    }


    @Test
     void testGetUserMissingFiledsForlUsers() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName("test");
        userRepository.save(userEntity);
        URL url = new URL("http://localhost:" + port + "/api/v1/user/userMissingField/"+userEntity.getId());
        HttpHeaders headers = new HttpHeaders();
        headers.add(AuthenticationServiceImpl.AUTH_TOKEN_HEADER_NAME,"users");
        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);
        ResponseEntity<String> response = restTemplate.exchange(url.toURI(), HttpMethod.GET,requestEntity, String.class);
        assertNotNull(response);
        assertEquals(HttpStatus.OK,response.getStatusCode());

        TypeReference<Map<String,String>> typeRef
                = new TypeReference<>() {};
        ObjectMapper mapper = new ObjectMapper();
        Map<String,String> missingFiled = mapper.readValue(response.getBody(), typeRef);
        assertNotNull(missingFiled);
        Map<String,String> updateFiled = new HashMap<>();
        for(String key :missingFiled.keySet()){
            if(key.toLowerCase().contains("date")){
                updateFiled.put(key,"2024-11-11");
            }else {
                updateFiled.put(key,"test");
            }

            URL url1 = new URL("http://localhost:" + port + "/api/v1/user/update");

            HttpEntity<Map<String,String>> requestEntityUpdate = new HttpEntity<>(updateFiled,headers);

            ResponseEntity<String> responseUpdate = restTemplate.exchange(url1.toURI(), HttpMethod.POST,requestEntityUpdate, String.class);

            ObjectMapper mapper1 = new ObjectMapper();
            assertEquals(HttpStatus.OK,responseUpdate.getStatusCode());
            UserDTO user = mapper1.readValue(responseUpdate.getBody(),UserDTO.class);
            assertNotNull(user);
        }

    }



}
