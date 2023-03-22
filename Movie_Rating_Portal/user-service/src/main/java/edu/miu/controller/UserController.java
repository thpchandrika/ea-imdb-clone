package edu.miu.controller;

import edu.miu.dto.UserDto;
import edu.miu.dto.UserSaveDto;
import edu.miu.dto.UserVerifyDto;
import edu.miu.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    private UserService userService;
    private UserDetailsService userDetailsService;

    public UserController(UserService userService, UserDetailsService userDetailsService) {
        this.userService = userService;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/signup")
    UserDto saveUser(@RequestBody @Valid UserSaveDto userSaveDto) {
        return userService.saveUser(userSaveDto);
    }

    @PostMapping("/verify")
    Boolean verifyUserDetailsByUsername(@RequestBody @Valid UserVerifyDto userVerifyDto) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userVerifyDto.getEmail());
        return userDetails != null
                && userDetails.isAccountNonExpired()
                && userDetails.isAccountNonLocked()
                && userDetails.isEnabled()
                && userDetails.isCredentialsNonExpired();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        System.out.println("inside the delete mapping...");
        userService.deleteUser(id);
    }
}
