package com.trip.Let.sGo.post;

import com.trip.Let.sGo.comment.dto.CommentDTO;
import com.trip.Let.sGo.exception.DataNotFoundException;
import com.trip.Let.sGo.post.dto.CreatePostDTO;
import com.trip.Let.sGo.post.dto.PostDTO;
import com.trip.Let.sGo.post.entity.PostEntity;
import com.trip.Let.sGo.user.entity.UserEntity;
import com.trip.Let.sGo.post.repository.PostRepository;
import com.trip.Let.sGo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostDTO createPost(CreatePostDTO createPostDTO, String username) {
        UserEntity user = this.userRepository.findByUsername(username);
        PostEntity post = new PostEntity();

        post.setAuthor(user);
        post.setTitle(createPostDTO.getTitle());
        post.setContent(createPostDTO.getContent());
        postRepository.save(post);

        PostDTO newPost = new PostDTO(post);

        return newPost;
    }

    //글 수정
    public PostDTO updatePost(Integer postId, CreatePostDTO createPostDTO, String username) {
        System.out.println("update");   //test

        PostEntity existingPost = this.postRepository.findById(postId)  // 글 id 찾기
                .orElseThrow(() -> new IllegalArgumentException("Invalid post id: " + postId));

        UserEntity user = this.userRepository.findByUsername(username);
        if (!existingPost.getAuthor().equals(user)) { // 권한 확인
            throw new IllegalArgumentException("You are not authorized to update this post.");
        }

        existingPost.setTitle(createPostDTO.getTitle());
        existingPost.setContent(createPostDTO.getContent());
        postRepository.save(existingPost);

        return new PostDTO(existingPost);
    }

    //글 삭제
    public void deletePost(Integer postId, String username) {

        PostEntity existingPost = this.postRepository.findById(postId)  // 글 id 찾기
                .orElseThrow(() -> new IllegalArgumentException("Invalid post id: " + postId));

        // 게시물의 작성자(author) 정보 가져오기
        UserEntity author = existingPost.getAuthor();

        // 현재 사용자의 username과 게시물의 작성자(username)가 일치하는지 확인
        if (!author.getUsername().equals(username)) { // 유저 권한 확인
            throw new IllegalArgumentException("You are not authorized to delete this post.");
        }

        this.postRepository.delete(existingPost);
    }

    public PostDTO getPost(Integer postId) {
        Optional<PostEntity> postOptional = this.postRepository.findById(postId);
        if(!postOptional.isPresent()){
            throw new DataNotFoundException("DB에서 " + postId + "번 게시글을 조회하지 못했습니다.");
        }

        PostEntity post = postOptional.get();
        PostDTO postDTO = new PostDTO(post);

        return postDTO;

    }

    public void likePost(Integer postId, String username) {
        PostEntity post = this.postRepository.getReferenceById(postId);
        UserEntity user = this.userRepository.findByUsername(username);

        post.getVoter().add(user);
        this.postRepository.save(post);
    }
}
