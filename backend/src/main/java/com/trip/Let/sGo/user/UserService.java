package com.trip.Let.sGo.user;

import com.trip.Let.sGo.exception.UsernameAlreadyExistsException;
import com.trip.Let.sGo.user.dto.CreateUserDTO;
import com.trip.Let.sGo.user.dto.UserDTO;
import com.trip.Let.sGo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.trip.Let.sGo.user.entity.UserEntity;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserDTO createUser(String username, String password) {
        UserEntity user = new UserEntity();

        user.setUsername(username);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRole("ROLE_ADMIN");

        userRepository.save(user);

        UserDTO newUser = new UserDTO();
        newUser.setUsername(username);
        newUser.setRole(user.getRole());
        newUser.setCreateDate(user.getCreateDate());
        return newUser;
    }
}
