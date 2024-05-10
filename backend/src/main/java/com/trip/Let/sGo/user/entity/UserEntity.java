package com.trip.Let.sGo.user.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.trip.Let.sGo.post.entity.PostEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    private String username;

    @JsonIgnore
    private String password;

    private String role;

    @CreatedDate
    private LocalDateTime createDate;

//    @ManyToMany
//    private Set<PostEntity> likedPosts;
}