package org.userinputs.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.userinputs.exception.UpdateFailedException;
import org.userinputs.exception.UpdateRequestMalFormedException;
import org.userinputs.exception.UserNotFoundException;
import org.userinputs.model.UserDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.userinputs.service.impl.AuthenticationServiceImpl;

import java.io.IOException;
import java.util.Map;


@RestController
public interface UserController {

    @Operation(summary = "Get Required Fields")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Exception Occurred"),
            @ApiResponse(responseCode = "401", description = "Unauthorised")})
    @GetMapping(value = "/userMissingField/{userId}",  produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UserDTO> getRequiredFileds(
            @RequestHeader(AuthenticationServiceImpl.AUTH_TOKEN_HEADER_NAME) @Parameter(name = AuthenticationServiceImpl.AUTH_TOKEN_HEADER_NAME ,description = "authentication Token",required = true)  String authToken,
            @PathVariable  String userID) throws IOException, UserNotFoundException, InstantiationException, IllegalAccessException;

    @Operation(summary = "Get User Details")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Exception Occurred"),
            @ApiResponse(responseCode = "401", description = "Unauthorised")})
    @GetMapping(value = "/getUserDetails",  produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity getUserDetails(@RequestHeader(AuthenticationServiceImpl.AUTH_TOKEN_HEADER_NAME) @Parameter(name = AuthenticationServiceImpl.AUTH_TOKEN_HEADER_NAME ,description = "authentication Token",required = true)  String authToken);

    @Operation(summary = "Update User Details")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "Exception Occurred"),
            @ApiResponse(responseCode = "401", description = "Unauthorised")})
    @PostMapping(value = "/update",  produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UserDTO> updateUserDetails(@RequestHeader(AuthenticationServiceImpl.AUTH_TOKEN_HEADER_NAME) @Parameter(name = AuthenticationServiceImpl.AUTH_TOKEN_HEADER_NAME ,description = "authentication Token",required = true)  String authToken,
    @RequestBody Map<String,String> body) throws UserNotFoundException, UpdateFailedException, UpdateRequestMalFormedException, InstantiationException, IllegalAccessException;

}
