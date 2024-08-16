package com.example.demo.repository;

import com.example.demo.dto.UserFindUserIdDto;
import com.example.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findUserByEmail(String email);
    Optional<UserFindUserIdDto> findUserByUserId(Long userId);
    List<UserFindUserIdDto> findAllProjectedBy();
}