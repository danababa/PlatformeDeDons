package com.uca.m2.pdd.Service;

import com.uca.m2.pdd.Exception.ConflictException;
import com.uca.m2.pdd.Mapper.UserMapper;
import com.uca.m2.pdd.Model.dto.UserDto;
import com.uca.m2.pdd.Model.entity.Users;
import com.uca.m2.pdd.Repository.UsersRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    private UserService(UsersRepository usersRepository, PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    /**
     * Create a new user
     * @return new user
     */
    public void registerUser(String username, String password, String nom, String prenom,
                             String email, String numeroTelephone, double longitude, double latitude) {

        usersRepository.findUserByUsername(username).ifPresent(user -> {
            throw new ConflictException("User already exists");
        });

        Users user = new Users();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        user.setNom(nom);
        user.setPrenom(prenom);
        user.setEmail(email);
        user.setNumeroTelephone(numeroTelephone);
        user.setLongitude(longitude);
        user.setLatitude(latitude);

        usersRepository.save(user);
    }

    /**
     * Get user by id
     * @param id user id
     * @return user
     */
    public UserDto getUserById(UUID id) {
        Users user = usersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("User not found"));
        return UserMapper.toUserDto(user);
    }
    /**
     * Get all users
     * @return list of users
     */
    public List<UserDto> getAllUsers() {
        return usersRepository.findAll().stream().map(UserMapper::toUserDto).toList();
    }
    /**
     * Update user
     * @param userDto user body
     * @return updated user
     */
    //TODO method
    public UserDto updateUser(UserDto userDto, UUID id) {
        Users existingUser = usersRepository.findById(id).orElseThrow(() -> new ConflictException("User not found"));
        userMapper.updateUserFromDto(userDto, existingUser);
        return UserMapper.toUserDto(usersRepository.save(existingUser));
    }

    /**
     * Delete user by id
     * @param id user id
     */
    public void deleteUser(UUID id) {
        if (!usersRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }
        usersRepository.deleteById(id);
    }

}
