package com.health.management.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        boolean hasDoctorRole = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_Doctor"));
        boolean hasPatientRole = authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_Patient"));

        if (hasDoctorRole) {
            response.sendRedirect("/doctor/patientAppointment");
        } else if (hasPatientRole) {
            response.sendRedirect("/patient/appointment");
        } else {
            //response.sendRedirect("/");

        }
    }

}

