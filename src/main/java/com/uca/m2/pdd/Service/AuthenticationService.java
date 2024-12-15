package com.uca.m2.pdd.Service;

import com.uca.m2.pdd.Model.entity.Users;
import com.uca.m2.pdd.Repository.UsersRepository;
import com.uca.m2.pdd.util.JwtTokenProvider;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private UsersRepository usersRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UsersRepository usersRepository, JwtTokenProvider jwtTokenProvider, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional
    public String authenticateUser(String username, String password) {

        if (username == null) {
            throw new RuntimeException("Invalid username or password!");
        }

        if (password == null) {
            throw new RuntimeException("Invalid username or password!");
        }

        // Find the user by username
        Users user = usersRepository.findUserByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if the password matches
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid username or password!");
        }

        // Generate JWT token
        return jwtTokenProvider.generateToken(username);
    }
}
