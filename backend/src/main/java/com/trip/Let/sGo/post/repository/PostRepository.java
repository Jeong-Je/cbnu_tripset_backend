package com.trip.Let.sGo.post.repository;

import com.trip.Let.sGo.post.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Integer> {
}
