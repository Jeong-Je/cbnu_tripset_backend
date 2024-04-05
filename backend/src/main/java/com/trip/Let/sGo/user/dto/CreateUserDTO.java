package com.trip.Let.sGo.user.dto;

import com.trip.Let.sGo.user.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CreateUserDTO {
    private String username;

    private String Role;

    private LocalDateTime createdDate;

    public CreateUserDTO(UserEntity userEntity){
        this.username = userEntity.getUsername();
        this.Role = userEntity.getRole();
        this.createdDate = userEntity.getCreateDate();
    }
}
