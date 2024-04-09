package com.trip.Let.sGo.post;


import com.trip.Let.sGo.post.dto.CreatePostDTO;
import com.trip.Let.sGo.post.dto.PostDTO;
import com.trip.Let.sGo.post.entity.PostEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    // 글 작성
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public PostDTO createPost(@Valid CreatePostDTO createPostDTO, BindingResult bindingResult, Principal principal) {
        if(bindingResult.hasErrors()) {

        }
        return this.postService.createPost(createPostDTO, principal.getName());
    }

    //글수정
    @PreAuthorize("isAuthenticated()")  // jwt 토큰검사
    @PutMapping("update/{id}")
    public PostDTO updatePost(@PathVariable("id") Integer postId, @Valid CreatePostDTO createPostDTO, BindingResult bindingResult, Principal principal) {
        System.out.println("update");   //test

        if(bindingResult.hasErrors()) {
            // 유효성 검사 오류 처리
        }
        return this.postService.updatePost(postId, createPostDTO, principal.getName());
    }

    //글삭제
    @PreAuthorize("isAuthenticated()")  // jwt 토큰검사
    @DeleteMapping("delete/{id}")
    public void deletePost(@PathVariable("id") Integer postId, Principal principal) {

        this.postService.deletePost(postId, principal.getName());
    }

    // 특정 게시글 불러오기
    @GetMapping("/{id}")
    public PostDTO selectPost(@PathVariable("id") Integer postId) {
        return this.postService.getPost(postId);
    }

    // 게시글에 좋아요 기능
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/like/{id}")
    public void likePost(@PathVariable("id") Integer postId, Principal principal) {
        this.postService.likePost(postId, principal.getName());
    }
}
