package com.example.demo.service;

import com.example.demo.dto.Response;
import com.example.demo.dto.SignInDto;
import com.example.demo.dto.SignUpDto;
import com.example.demo.dto.UserFindUserIdDto;
import com.example.demo.entity.Users;
import com.example.demo.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Response<String> signUp(SignUpDto signUpDto) {
        Users user = new Users();
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        userRepository.save(user);
        return new Response<>("회원가입 성공적", HttpStatus.OK);
    }

    public Response<String> signIn(SignInDto signinDto, HttpServletRequest request) {
        Users userByEmail = userRepository.findUserByEmail(signinDto.getEmail());
        if (userByEmail == null) {
            return new Response<>("존재하지 않는 사용자임", HttpStatus.NOT_FOUND);
        }
        if (passwordEncoder.matches(signinDto.getPassword(), userByEmail.getPassword())) {
            request.getSession().setAttribute("userId", userByEmail.getUserId());
            return new Response<>("로그인 성공적", HttpStatus.OK);
        } else {
            return new Response<>("비밀번호가 일치하지 않음", HttpStatus.UNAUTHORIZED);
        }
    }

    public Response<String> logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return new Response<>("로그아웃 성공적", HttpStatus.OK);
    }

    public Response<?> findUserByUserId(HttpServletRequest request) {
        Long userId = (Long) request.getSession().getAttribute("userId");
        if (userId == null) {
            return new Response<>("로그인 중이 아님", HttpStatus.NOT_FOUND);
        }
        Optional<UserFindUserIdDto> findUser = userRepository.findUserByUserId(userId);
        if (findUser.isPresent()) {
            return new Response<>(findUser.get(), HttpStatus.OK);
        } else {
            return new Response<>("사용자를 찾을 수 없습니다.", HttpStatus.NOT_FOUND);
        }
    }

    public Response<List<UserFindUserIdDto>> findAllUsers() {
        List<UserFindUserIdDto> findUsers = userRepository.findAllProjectedBy();
        return new Response<>(findUsers, HttpStatus.OK);
    }
}