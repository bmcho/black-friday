package com.bmcho.memberservice.service;

import com.bmcho.memberservice.entity.UserEntity;
import com.bmcho.memberservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity registerUser(String loginId, String userName) {
        var user = new UserEntity(loginId, userName);
        return userRepository.save(user);
    }

    public UserEntity modifyUser(Long userId, String userName) {
        var user = userRepository.findById(userId).orElseThrow();
        user.userName = userName;

        return userRepository.save(user);
    }

    public UserEntity getUser(String loginId) {
        return userRepository.findByLoginId(loginId).orElseThrow();
    }
}
