package com.uca.m2.pdd.Controller;


import com.uca.m2.pdd.Model.dto.UserDto;
import com.uca.m2.pdd.Service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService usersService;

    /**
     * Create a new user
     * @param userDto user body
     * @return new user
     */
    @PostMapping("/register")
    public ResponseEntity<UserDto> createUser(@RequestBody @Valid UserDto userDto) {
        return ResponseEntity.ok().body(usersService.createUser(userDto));
    }


    /**
     * Get user by id
     * @param id user id
     * @return user
     */
 /* @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable UUID id) {
        return ResponseEntity.ok().body(usersService.getUserById(id));
    }*/

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
