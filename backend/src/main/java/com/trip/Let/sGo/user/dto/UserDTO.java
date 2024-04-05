package com.trip.Let.sGo.user.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class UserDTO {
    private String username;

    private String Role;

    private String password;

    private LocalDateTime createDate;
}
