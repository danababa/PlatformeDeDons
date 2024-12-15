package com.uca.m2.pdd.Controller;


import com.uca.m2.pdd.Model.dto.UserDto;
import com.uca.m2.pdd.Service.AuthenticationService;
import com.uca.m2.pdd.Service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Controller
@RequestMapping("/users")
public class UserController {


    private final UserService usersService;
    private final AuthenticationService authenticationService;
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    /**
     * Register user
     * @param username
     * @param password
     * @param nom
     * @param prenom
     * @param email
     * @param numeroTelephone
     * @param longitude
     * @param latitude
     * @param session
     * @param model
     * @return redirect to home page
     */
    @PostMapping(value = "/register")
    public String registerUser(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam("nom") String nom,
                               @RequestParam("prenom") String prenom,
                               @RequestParam("email") String email,
                               @RequestParam("numeroTelephone") String numeroTelephone,
                               @RequestParam("longitude") double longitude,
                               @RequestParam("latitude") double latitude,
                               HttpSession session,
                               Model model) {

        // Validate inputs (manual validation)
        if (username.isBlank() || password.isBlank() || nom.isBlank() || prenom.isBlank() || email.isBlank()) {
            model.addAttribute("error", "All fields are required.");
            return "register";
        }

        try {
            // Register user
            usersService.registerUser(username, password, nom, prenom, email, numeroTelephone, longitude, latitude);

            // Generate JWT token and store it in the session
            String token = authenticationService.authenticateUser(username, password);
            session.setAttribute("jwtToken", token);

            return "redirect:/dashboard"; // Redirect to home page
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register"; // Reload form with error message
        }
    }


    /**
     * Get all users
     * @return list of users
     */
    @GetMapping("/all")
    public ResponseEntity<List<UserDto>>getAllUsers(){
        return ResponseEntity.ok().body(usersService.getAllUsers());
    }

    /**
     * Update user
     * @param id user id
     * @param userDto user body
     * @return updated user
     */
    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable UUID id, @RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok().body(usersService.updateUser(userDto, id));
    }

    /**
     * Delete user
     * @param id user id
     * @return deleted user
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> deleteUser(@PathVariable UUID id) {
        usersService.deleteUser(id);
        return ResponseEntity.ok().build();
    }
}
