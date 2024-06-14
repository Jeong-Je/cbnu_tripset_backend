package com.trip.Let.sGo.post.repository;

import com.trip.Let.sGo.post.dto.PostDTO;
import com.trip.Let.sGo.post.entity.PostEntity;
import com.trip.Let.sGo.user.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {

    Page<PostEntity> findByAuthor(UserEntity user, Pageable pageable);

    Page<PostEntity> findByCategory(String category, Pageable pageable);

    List<PostEntity> findAllByVoterId(Integer voterId);
}