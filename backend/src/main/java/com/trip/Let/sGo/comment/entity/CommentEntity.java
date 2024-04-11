package com.trip.Let.sGo.comment.entity;

import com.trip.Let.sGo.post.entity.PostEntity;
import com.trip.Let.sGo.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @ManyToOne
    private UserEntity author;

    @Column(name = "like_count")
    private int likeCount;

    @ManyToOne
    private PostEntity postEntity;

    @ManyToMany
    Set<UserEntity> voter; // voter 속성값이 서로 중복되지 않도록 List보다는 Set이 효율적

    @CreatedDate
    private LocalDateTime createDate;
}
