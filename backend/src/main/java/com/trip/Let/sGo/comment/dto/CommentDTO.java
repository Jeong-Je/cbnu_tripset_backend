package com.trip.Let.sGo.comment.dto;

import com.trip.Let.sGo.user.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class CommentDTO {
    private Integer id;

    private String comment;

    private UserEntity author;

    private Integer likeCount;

    private LocalDateTime createDate;
}
