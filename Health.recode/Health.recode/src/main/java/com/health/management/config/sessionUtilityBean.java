package com.health.management.config;

import jakarta.servlet.http.HttpSession;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class sessionUtilityBean {

    @Bean
    public void removeMessageFromSession() {
        try {
            System.out.println("removing message from session");
            HttpSession session=((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
            session.removeAttribute("message");
        }catch (Exception e) {
            System.out.println(e.getMessage());
            // TODO: handle exception
        }
    }

}
