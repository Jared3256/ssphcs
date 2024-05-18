package com.shaninfosystem.SpecializedCare.controller;

import com.shaninfosystem.SpecializedCare.entity.user.User;
import com.shaninfosystem.SpecializedCare.model.AuthenticationResponse;
import com.shaninfosystem.SpecializedCare.service.AuthentictionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class AuthenticationController
{
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);
    @Autowired
    private AuthentictionService authentictionService;

    @GetMapping("/test")
    public ResponseEntity<String> test()
    {
        return ResponseEntity.ok("This is a test");
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody User request)
    {
        logger.info(request.toString());
        return ResponseEntity.ok(authentictionService.register(request));
    }

    @PostMapping("/register1")
    public ResponseEntity<User> register1(@RequestBody User request)
    {
        logger.info(request.toString());
        return ResponseEntity.ok(request);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody User request)
    {
        logger.info(request.toString());
        return ResponseEntity.ok(authentictionService.authenticate(request));
    }
}
