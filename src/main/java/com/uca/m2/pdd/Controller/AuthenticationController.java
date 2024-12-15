package com.uca.m2.pdd.Controller;

import com.uca.m2.pdd.Model.dto.UserDto;
import com.uca.m2.pdd.Service.AuthenticationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthenticationController {


    private final AuthenticationService authenticationService;
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // login.html in templates folder
    }

    // POST: Handle login and set JWT in a cookie
    @PostMapping("/login")
    public String login(@RequestBody UserDto userDto, HttpServletResponse response, Model model) {
        String token = authenticationService.authenticateUser(userDto.getUsername(), userDto.getPassword());

        if (token == null) {
            model.addAttribute("error", "Invalid credentials");
            return "login"; // Reload login page with error
        }

        // Create HTTP-only, secure cookie for JWT
        Cookie jwtCookie = new Cookie("token", token);
        jwtCookie.setHttpOnly(true); // Prevent XSS
        jwtCookie.setSecure(true);   // Only over HTTPS
        jwtCookie.setPath("/");      // Accessible across the app
        jwtCookie.setMaxAge(3600);   // 1-hour expiry

        response.addCookie(jwtCookie); // Add the cookie to the response

        return "redirect:/dashboard"; // Redirect to dashboard
    }

    // GET: Handle logout and delete the JWT cookie
    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie jwtCookie = new Cookie("token", null);
        jwtCookie.setHttpOnly(true);
        jwtCookie.setSecure(true);
        jwtCookie.setPath("/");
        jwtCookie.setMaxAge(0); // Expire immediately

        response.addCookie(jwtCookie); // Remove the cookie
        return "redirect:/auth/login";
    }
}

