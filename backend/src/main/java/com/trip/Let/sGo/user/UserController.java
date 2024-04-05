package com.trip.Let.sGo.user;

import com.trip.Let.sGo.user.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping()
    public UserDTO userCreate(UserDTO userDTO) {
        return userService.createUser(userDTO);
}
}
