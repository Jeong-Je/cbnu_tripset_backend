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

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public PostDTO createPost(@Valid CreatePostDTO createPostDTO, BindingResult bindingResult, Principal principal) {
        if(bindingResult.hasErrors()) {

        }
        return this.postService.createPost(createPostDTO, principal.getName());
    }

    @GetMapping("/{id}")
    public PostDTO selectPost(@PathVariable("id") Integer postId) {
        return this.postService.selectPost(postId);
    }
}
