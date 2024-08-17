package com.health.management.controller;

import com.health.management.entity.User;
import com.health.management.helper.Message;
import com.health.management.sevice.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String showHome()
    {
        return "home";
    }

    @GetMapping("/register")
    public String showrRgister() {
        return "registertion";
    }

    @PostMapping("/do_register")
    public String registerUser(@Valid @ModelAttribute("user") User user,HttpSession session) {
        try {

            User user1=userService.save(user);
            session.setAttribute("message", new Message("Successfully Registered !!", "alert-success"));
            return "registertion";

        } catch (Exception e) {
            e.printStackTrace();
            session.setAttribute("message", new Message("EmailId is already exist !!" , "alert-danger"));
            return "registertion";
        }


    }

    @GetMapping("/login")
    public String login() {
        System.out.println("login page executed");
        return "login";
    }
}
