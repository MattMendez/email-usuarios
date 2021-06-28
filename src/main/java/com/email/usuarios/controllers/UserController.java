package com.email.usuarios.controllers;

import com.email.usuarios.dtos.LoginForm;
import com.email.usuarios.dtos.RequestVerification;
import com.email.usuarios.dtos.SignUpForm;
import com.email.usuarios.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login-verify")
    public RequestVerification loginVerify(@RequestBody LoginForm loginForm){

        return userService.verify(loginForm);
    }

    @PostMapping("/user-exist")
    public RequestVerification loginVerify(@RequestBody String email){

        return userService.verifyexist(email);
    }

    @PostMapping("/sign-up")
    public RequestVerification signUp(@RequestBody SignUpForm signUpForm) {


        SignUpForm nuevaCuenta = SignUpForm.builder()
                .email(signUpForm.getEmail())
                .password(signUpForm.getPassword())
                .build();
        RequestVerification response = userService.registerNewUser(nuevaCuenta);

        return response;
    }

}
