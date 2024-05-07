package com.trip.Let.sGo.post.entity;

import com.trip.Let.sGo.comment.entity.CommentEntity;
import com.trip.Let.sGo.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class PostEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column
    private String category;

    @OneToMany(mappedBy = "postEntity", cascade = CascadeType.REMOVE)
    private List<CommentEntity> commentEntityList;

    @ManyToOne
    private UserEntity author;

    @Column(name = "like_count")
    private int likeCount;

    @ManyToMany
    Set<UserEntity> voter; // voter 속성값이 서로 중복되지 않도록 List보다는 Set이 효율적

    @CreatedDate
    private LocalDateTime createDate;
}
