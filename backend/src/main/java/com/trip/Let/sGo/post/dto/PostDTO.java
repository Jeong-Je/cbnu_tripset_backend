package com.trip.Let.sGo.post.dto;

import com.trip.Let.sGo.comment.dto.CommentDTO;
import com.trip.Let.sGo.comment.entity.CommentEntity;
import com.trip.Let.sGo.post.entity.PostEntity;
import com.trip.Let.sGo.user.dto.UserDTO;
import com.trip.Let.sGo.user.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class PostDTO {
    private Integer id;

    private String title;

    private String content;

    private String category;

    private Integer likeCount;

    private UserEntity author;

    private LocalDateTime createDate;

    private List<CommentDTO> commentDTOList;

    public PostDTO(PostEntity post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.category = post.getCategory();
        this.createDate = post.getCreateDate();
        this.author = post.getAuthor();
        this.likeCount = post.getLikeCount();
        if(post.getCommentEntityList() != null){
            this.commentDTOList = convertToCommentDTOList(post.getCommentEntityList());
        }
    }

    private List<CommentDTO> convertToCommentDTOList(List<CommentEntity> commentEntities) {
        List<CommentDTO> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntities) {
            CommentDTO commentDTO = new CommentDTO();

            commentDTO.setId(commentEntity.getId());
            commentDTO.setComment(commentEntity.getComment());
            commentDTO.setCreateDate(commentEntity.getCreateDate());
            commentDTO.setAuthor(commentEntity.getAuthor());
            commentDTO.setLikeCount(commentEntity.getLikeCount());

            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }
}
