package com.trip.Let.sGo.service;

import com.trip.Let.sGo.dto.CustomUserDetails;
import com.trip.Let.sGo.user.entity.UserEntity;
import com.trip.Let.sGo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUsername(username);

        if(user != null) {
            return new CustomUserDetails(user);
        }
        return null;
    }
}
