package com.trip.Let.sGo.post.dto;

import com.trip.Let.sGo.comment.dto.CommentDTO;
import com.trip.Let.sGo.comment.entity.CommentEntity;
import com.trip.Let.sGo.post.entity.PostEntity;
import com.trip.Let.sGo.user.entity.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class CreatePostDTO {
    @NotEmpty(message = "제목은 필수 항목입니다.")
    @Size(max=200)
    private String title;

    @NotEmpty(message = "내용은 필수 항목입니다.")
    private String content;
}