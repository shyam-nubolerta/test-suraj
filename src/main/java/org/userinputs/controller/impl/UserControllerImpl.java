package org.userinputs.controller.impl;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.userinputs.controller.UserController;
import org.userinputs.exception.UpdateFailedException;
import org.userinputs.exception.UpdateRequestMalFormedException;
import org.userinputs.exception.UserNotFoundException;
import org.userinputs.model.UserDTO;
import org.userinputs.service.ExternalSystemService;
import org.userinputs.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@OpenAPIDefinition(tags = @Tag(name = "User"))
@RequestMapping(path = "/api/v1/user")
@Slf4j
public class UserControllerImpl implements UserController {

    private final UserService userService;
    private final ExternalSystemService externalSystemService;

    public UserControllerImpl(UserService userService, ExternalSystemService externalSystemService) {
        this.userService = userService;
        this.externalSystemService = externalSystemService;
    }

    @Override
    public ResponseEntity<Map<String,String>> getRequiredFileds(String authToken,String userId) throws  UserNotFoundException, InstantiationException, IllegalAccessException {
        return ResponseEntity.ok(userService.getMissingFileds(userId,"usersDetails"));
    }

    @Override
    public ResponseEntity<List<UserDTO>> getUserDetails(String authToken ) {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Override
    public ResponseEntity<UserDTO>  updateUserDetails(String authToken,
                                           Map<String,String> updateDetails) throws UserNotFoundException, UpdateFailedException, UpdateRequestMalFormedException, InstantiationException, IllegalAccessException {
        UserDTO userDTO = userService.updateUserDetails( updateDetails);
        externalSystemService.sendUserInfo(userDTO);
        return  ResponseEntity.ok(userDTO);

    }
}
