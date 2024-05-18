package com.shaninfosystem.SpecializedCare.config;

import com.shaninfosystem.SpecializedCare.filter.JwtTokenFilter;
import com.shaninfosystem.SpecializedCare.service.UserDetailsImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig
{
    @Autowired
    private UserDetailsImplementation userDetails;

    @Autowired
    private JwtTokenFilter jwtTokenFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security) throws Exception
    {
        return security
                .cors(co -> co.disable())
                .csrf(cs -> cs.disable())
                .authorizeHttpRequests(req ->
                        req
                                .requestMatchers("/login/**", "/register/**", "/test1/**", "/register1/**")
                                .permitAll()
                                .requestMatchers("/admin/**")
                                .hasAnyAuthority("ADMIN")
                                .anyRequest()
                                .authenticated())
                .userDetailsService(userDetails)
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(11);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception
    {
        return configuration.getAuthenticationManager();
    }
}
