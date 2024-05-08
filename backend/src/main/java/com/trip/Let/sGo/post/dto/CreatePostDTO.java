package com.trip.Let.sGo.post.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreatePostDTO {
    @NotEmpty(message = "제목은 필수 항목입니다.")
    @Size(max=200)
    private String title;

    @NotEmpty(message = "내용은 필수 항목입니다.")
    private String content;

    @NotEmpty(message = "카테고리는 필수 항목입니다.")
    @Pattern(regexp = "FREE|PLAN", message = "카테고리는 'FREE' 또는 'PLAN'이어야 합니다.")
    private String category;
}