package com.trip.Let.sGo.comment.repository;

import com.trip.Let.sGo.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity, Integer> {
}
