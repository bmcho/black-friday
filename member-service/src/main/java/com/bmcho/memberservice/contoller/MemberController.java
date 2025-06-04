package com.bmcho.memberservice.contoller;

import com.bmcho.memberservice.dto.ModifyUserDto;
import com.bmcho.memberservice.dto.RegisterUserDto;
import com.bmcho.memberservice.entity.UserEntity;
import com.bmcho.memberservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class MemberController {

    private final UserService userService;

    @PostMapping("/member/users/registration")
    public UserEntity registerUser(@RequestBody RegisterUserDto dto) {
        return userService.registerUser(dto.loginId, dto.userName);
    }

    @PutMapping("/member/users/{userId}/modify")
    public UserEntity modifyUser(@PathVariable Long userId, @RequestBody ModifyUserDto dto) {
        return userService.modifyUser(userId, dto.userName);
    }

    @PostMapping("/member/users/{loginId}/login")
    public UserEntity login(@PathVariable String loginId) {
        return userService.getUser(loginId);
    }

}
