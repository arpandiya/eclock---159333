package com.eclock.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class AuthController {



    @GetMapping("/login")
    @PostMapping("/login")
    public String loginPage(
            @RequestParam(value="error", required=false) String error,
            @RequestParam(value="logout", required=false) String logout,
            @RequestParam(value="register", required=false) String register,
            Model model
    ) {
        String errorMessage = null;
        if(error != null) {
            errorMessage = "Your username and password is invalid.";
        } else if(logout != null) {
            errorMessage = "You have been logged out.";
        } else if(register != null) {
            errorMessage = "You have been registered successfully.";
        }
        if(errorMessage != null) {
            model.addAttribute("errorMessage", errorMessage);
        }

        return "login.html";
    }


    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest httpRequest, HttpServletResponse httpResponse) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(auth != null) {
            new SecurityContextLogoutHandler().logout(httpRequest, httpResponse, auth);
        }
        return "redirect:/login?logout=true";
    }
}
