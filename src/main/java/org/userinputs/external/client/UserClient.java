package org.userinputs.external.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.userinputs.external.request.User;

@FeignClient(value = "user-service",dismiss404=true)
public interface UserClient {
    @PostMapping( value = "/api/v2/external/submituser")
    ResponseEntity<User> postUsers(@RequestBody @Validated User user);
}
