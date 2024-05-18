package com.shaninfosystem.SpecializedCare.service;

import com.shaninfosystem.SpecializedCare.entity.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsImplementation implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User with id " + username + " not found"));
    }
}