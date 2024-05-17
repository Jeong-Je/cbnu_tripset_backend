package com.trip.Let.sGo.post;


import com.trip.Let.sGo.exception.BadRequestException;
import com.trip.Let.sGo.post.dto.CreatePostDTO;
import com.trip.Let.sGo.post.dto.PostDTO;
import com.trip.Let.sGo.post.entity.PostEntity;
import com.trip.Let.sGo.post.pagination.PaginationResult;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
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
            // 유효성 검사 오류 처리
            List<String> errorMessages = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }

            throw new BadRequestException(errorMessages.toString());
        }
        return this.postService.createPost(createPostDTO, principal.getName());
    }

    //글수정
    @PreAuthorize("isAuthenticated()")  // jwt 토큰검사
    @PutMapping("update/{id}")
    public PostDTO updatePost(@PathVariable("id") Integer postId, @Valid CreatePostDTO createPostDTO, BindingResult bindingResult, Principal principal) {
        if(bindingResult.hasErrors()) {
            // 유효성 검사 오류 처리
            List<String> errorMessages = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }

            throw new BadRequestException(errorMessages.toString());
        }
        return this.postService.updatePost(postId, createPostDTO, principal.getName());
    }

    //글삭제
    @PreAuthorize("isAuthenticated()")  // jwt 토큰검사
    @DeleteMapping("delete/{id}")
    public void deletePost(@PathVariable("id") Integer postId, Principal principal) {

        this.postService.deletePost(postId, principal.getName());
    }

    // 게시글 페이지네이션
    @PreAuthorize("isAuthenticated()")
    @GetMapping()
    public PaginationResult paginatePost(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                         @RequestParam(value = "size", defaultValue = "10") Integer size,
                                         @RequestParam(value = "direction", defaultValue = "DESC") String direction,
                                         @RequestParam(value = "username", defaultValue = "all") String username,
                                         @RequestParam(value = "category", defaultValue = "all") String category,
                                         @RequestParam(value = "like", defaultValue = "false") Boolean like) {
        return this.postService.paginatePost(page, size, direction, username, category, like);
    }

    // 특정 게시글 불러오기
    @GetMapping("/{id}")
    public PostDTO getPost(@PathVariable("id") Integer postId) {
        return this.postService.getPost(postId);
    }

    // 게시글에 좋아요 기능
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/like/{id}")
    public ResponseEntity likePost(@PathVariable("id") Integer postId, Principal principal) {
        return this.postService.likePost(postId, principal.getName());
    }

    // 내가 좋아요한 게시글 불러오기
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/iLike")
    public List<PostDTO> getILike(Principal principal){
        return this.postService.getILikePosts(principal.getName());
    }
}
