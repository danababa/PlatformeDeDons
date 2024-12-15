package com.uca.m2.pdd.Controller;

import com.uca.m2.pdd.util.JwtTokenProvider;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {


    @GetMapping("/users/register")
    public String showRegisterPage() {
        return "register";
    }
    @GetMapping
    public String dashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = (authentication != null) ? authentication.getName() : "Guest";

        model.addAttribute("username", username);
        return "dashboard";
    }
}
