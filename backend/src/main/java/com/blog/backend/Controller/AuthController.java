package com.blog.backend.Controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.backend.Service.AuthService;
import com.blog.backend.dto.AuthRequest;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService){
        this.authService = authService;
    }
    
    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonNode> login(@RequestBody AuthRequest loginRequest){
        return authService.login(loginRequest);
    }
    
    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonNode> signup(@RequestBody AuthRequest authRequest){
        return authService.signup(authRequest)
                ? ResponseEntity.ok(responseMessage(authRequest.getUserName()))
                : ResponseEntity.unprocessableEntity().body(responseMessage(authRequest.getUserName()));
    }

    private ObjectNode responseMessage(String userName){
        return new ObjectMapper().createObjectNode().put("username", userName);
    }
}
