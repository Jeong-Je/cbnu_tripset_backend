package com.trip.Let.sGo.comment;

import com.trip.Let.sGo.comment.dto.CommentDTO;
import com.trip.Let.sGo.comment.entity.CommentEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    // 새로운 댓글 생성
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    private CommentDTO createComment(@Valid()CommentDTO commentDTO, BindingResult bindingResult, Principal principal, @PathVariable("id") Integer postId) {
        return commentService.createComment(commentDTO, principal.getName(), postId);
    }

    // 댓글에 좋아요
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/like/{id}")
    private void likeComment(@PathVariable("id") Integer id, Principal principal) {
        this.commentService.likeComment(id, principal.getName());
    }
}
