package com.trip.Let.sGo.comment;

import com.trip.Let.sGo.comment.dto.CommentDTO;
import com.trip.Let.sGo.comment.entity.CommentEntity;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;

    // 새로운 댓글 생성
    @PreAuthorize("isAuthenticated()")
    @PostMapping("create/{id}")
    private CommentDTO createComment(@Valid()CommentDTO commentDTO, BindingResult bindingResult, Principal principal, @PathVariable("id") Integer postId) {
        return commentService.createComment(commentDTO, principal.getName(), postId);
    }

    // 댓글 수정 (comment modify)
    @PreAuthorize("isAuthenticated()")
    @PutMapping ("modify/{id}")
    private CommentDTO modifyComment(@Valid CommentDTO commentDTO, BindingResult bindingResult, Principal principal, @PathVariable("id") Integer Id){
        System.out.println("test");
        if(bindingResult.hasErrors()) {
            // 유효성 검사 오류 처리
        }
        return commentService.modifyComment(commentDTO, principal.getName(), Id);
    }

    // 댓글 삭제
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("delete/{id}")
    public void deleteComment(@PathVariable("id") Integer commentId, Principal principal) {

        this.commentService.deleteComment(commentId, principal.getName());
    }

    // 댓글에 좋아요
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/like/{id}")
    private ResponseEntity likeComment(@PathVariable("id") Integer id, Principal principal) {
        return this.commentService.likeComment(id, principal.getName());
    }

}
