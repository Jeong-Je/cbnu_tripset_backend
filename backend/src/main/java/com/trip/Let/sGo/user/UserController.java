package com.trip.Let.sGo.user;

import com.trip.Let.sGo.exception.UsernameAlreadyExistsException;
import com.trip.Let.sGo.user.dto.UserDTO;
import com.trip.Let.sGo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;

    @PostMapping()
    public UserDTO userCreate(UserDTO userDTO) {
        String username = userDTO.getUsername();
        String password = userDTO.getPassword();

        Boolean isExist = userRepository.existsByUsername(username);
        if(isExist){
            // 중복 계정인 경우 예외 처리
            throw new UsernameAlreadyExistsException(username + "은(는) 이미 사용중인 아이디 입니다.");
        }

        return userService.createUser(username, password);
    }
}
