package com.shaninfosystem.SpecializedCare.service;

import com.shaninfosystem.SpecializedCare.entity.user.User;
import com.shaninfosystem.SpecializedCare.entity.user.UserRepository;
import com.shaninfosystem.SpecializedCare.model.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthentictionService
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;


    public AuthenticationResponse register(User request)
    {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        user.setUsername(request.getUsername());

        userRepository.save(user);

        String token = jwtService.generateToken(user);
        System.out.println(token);
        return new AuthenticationResponse(token);
    }

    public AuthenticationResponse authenticate(User request)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                )
        );

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();

        String token = jwtService.generateToken(user);

        return new AuthenticationResponse(token);
    }
}
