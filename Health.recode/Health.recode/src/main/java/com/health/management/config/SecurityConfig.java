package com.health.management.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@EnableWebSecurity
@Configuration
public class SecurityConfig {

    @Autowired
    private  AuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Bean
    public UserDetailsService getUserDetailsService(){

        return new CustomUserDetailsServiceImpl() ;
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){

         return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(this.getUserDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{

        http.csrf().disable()
                .authorizeRequests()
               .requestMatchers("/","/register","/do_register","/login","css/**").permitAll()
                .requestMatchers("/patient/***").hasRole("Patient")
                .requestMatchers("/doctor/***").hasRole("Doctor")
                .anyRequest()
                .authenticated()
                 .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/dologin")
                .successHandler(customAuthenticationSuccessHandler)
                .and()
                .logout().permitAll();


        return http.build();
    }
}
