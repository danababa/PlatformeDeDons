package com.uca.m2.pdd.Service;

import com.uca.m2.pdd.Mapper.UserMapper;
import com.uca.m2.pdd.Model.dto.UserDto;
import com.uca.m2.pdd.Model.entity.Users;
import com.uca.m2.pdd.Repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UsersRepository usersRepository;

    private UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    /**
     * Create a new user
     * @param userDto user body
     * @return new user
     */
    public UserDto createUser(UserDto userDto) {

        Users user = UserMapper.toUserEntity(userDto);
        if (usersRepository.existsById(user.getId())) {
            throw new IllegalArgumentException("User already exists");
        }
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
     * Update user
     * @param userDto user body
     * @return updated user
     */
    public UserDto updateUser(UserDto userDto, UUID id) {
        Users user = UserMapper.toUserEntity(userDto);
        if (!usersRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found");
        }
        Users savedUser = usersRepository.save(user);
        return UserMapper.toUserDto(savedUser);
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
