package com.eclock.controller;

import com.eclock.model.UserEntity;
import com.eclock.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {


    private UserRepository userRepository;

    public DashboardController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/dashboard")
    public String viewDashboard(Model model, Authentication authentication, HttpSession session) {
        UserEntity user = userRepository.findByUsername(authentication.getName());
        if(user == null)
        {
            return "redirect:/";
        } else {

            model.addAttribute("user", user);
            session.setAttribute("currentAuth", user);
        }



        return "dashboard/dashboard";
    }
}
