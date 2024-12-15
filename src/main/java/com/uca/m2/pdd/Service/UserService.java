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
     * @param userDto user body
     * @return new user
     */
    public UserDto createUser(UserDto userDto) {
        if (usersRepository.findUserByUsername(userDto.getUsername()).isPresent()) {
            throw new ConflictException("User with this username already exists");
        }
        if (usersRepository.findUserByEmail(userDto.getEmail()).isPresent()) {
            throw new ConflictException("User with this email already exists");
        }
        if (usersRepository.findUserByNumeroTelephone(userDto.getNumeroTelephone()).isPresent()) {
            throw new ConflictException("User with this phone number already exists");
        }
        Users user = UserMapper.toUserEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        Users savedUser = usersRepository.save(user);
        return UserMapper.toUserDto(savedUser);
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
