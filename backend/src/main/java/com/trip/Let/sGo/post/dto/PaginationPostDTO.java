package com.trip.Let.sGo.post.dto;

import com.trip.Let.sGo.comment.dto.CommentDTO;
import com.trip.Let.sGo.post.entity.PostEntity;
import com.trip.Let.sGo.user.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PaginationPostDTO {
    private Integer id;

    private UserEntity author;

    private String title;

    private String content;

    private String category;

    private Integer commentCount;

    private Integer likeCount;

    private LocalDateTime createDate;

    public PaginationPostDTO(PostEntity post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.category = post.getCategory();
        this.createDate = post.getCreateDate();
        this.author = post.getAuthor();
        this.likeCount = post.getLikeCount();
        this.commentCount = post.getCommentEntityList().size();
    }
}
