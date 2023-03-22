package edu.miu.controller;

import edu.miu.dto.LoginCredentialDto;
import edu.miu.exception.InvalidCredentialException;
import edu.miu.util.JwtTokenUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class LoginController {

    private AuthenticationManager authenticationManager;
    private JwtTokenUtil jwtTokenUtil;

    public LoginController(AuthenticationManager authenticationManager,JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil=jwtTokenUtil;
    }

    @PostMapping("/login")
    ResponseEntity<? extends Object> login(@RequestBody @Valid LoginCredentialDto credentialDto){
        try{
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(credentialDto.getEmail(),credentialDto.getPassword()));
            return ResponseEntity.ok().body(jwtTokenUtil.generateAccessToken(authenticate));
        } catch (BadCredentialsException ex) {
            throw new InvalidCredentialException("Provided Credential in invalid!");
        }
    }
}
