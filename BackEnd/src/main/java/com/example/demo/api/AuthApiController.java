package com.example.demo.api;

import com.example.demo.dto.Response;
import com.example.demo.dto.SignInDto;
import com.example.demo.dto.SignUpDto;
import com.example.demo.dto.UserFindUserIdDto;
import com.example.demo.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    @Autowired
    private AuthService authService;

    //회원가입
    @PostMapping("/signup")
    public ResponseEntity<Response<String>> signUpUser(@RequestBody SignUpDto signupDto) {
        return authService.signUp(signupDto).toResponseEntity();
    }

    //로그인
    @PostMapping("/signin")
    public ResponseEntity<Response<String>> signInUser(@RequestBody SignInDto signinDto, HttpServletRequest request) {
        return authService.signIn(signinDto, request).toResponseEntity();
    }

    //로그아웃
    @PostMapping("/logout")
    public ResponseEntity<Response<String>> logOutUser(HttpServletRequest request) {
        return authService.logout(request).toResponseEntity();
    }

    @GetMapping("/info")
    public ResponseEntity<? extends Response<?>> getUserInfo(HttpServletRequest request) {
        return authService.findUserByUserId(request).toResponseEntity();
    }

    @GetMapping("/users")
    public ResponseEntity<Response<List<UserFindUserIdDto>>> getAllUsers() {
        return authService.findAllUsers().toResponseEntity();
    }
}
