package com.uca.m2.pdd.Service;

import com.uca.m2.pdd.Exception.BadRequestException;
import com.uca.m2.pdd.Exception.ConflictException;
import com.uca.m2.pdd.Mapper.UserMapper;
import com.uca.m2.pdd.Model.dto.UserDto;
import com.uca.m2.pdd.Model.entity.Users;
import com.uca.m2.pdd.Repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UsersRepository usersRepository;

    private final UserMapper userMapper;


    private UserService(UsersRepository usersRepository, UserMapper userMapper) {
        this.usersRepository = usersRepository;
        this.userMapper = userMapper;
    }

    /**
     * Create a new user
     * @param userDto user body
     * @return new user
     */
    public UserDto createUser(UserDto userDto) {
        if(usersRepository.findUserByUsername(userDto.getUsername()).isPresent()){
            throw new ConflictException("User with this username already exists");
        }
        Users user = UserMapper.toUserEntity(userDto);
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

   /**
     * Login user
     * @param username user username
     * @param password user password
     * @return true if login is successful
     */
    /*public boolean login(String username, String password) {
        Users user = usersRepository.findUserByUsername(username).orElseThrow(() -> new BadRequestException("User not found"));
        return passwordEncoder.matches(password, user.getPassword());
    }*/
}
