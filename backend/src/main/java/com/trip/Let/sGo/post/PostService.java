package com.trip.Let.sGo.post;

import com.trip.Let.sGo.comment.dto.CommentDTO;
import com.trip.Let.sGo.post.dto.CreatePostDTO;
import com.trip.Let.sGo.post.dto.PostDTO;
import com.trip.Let.sGo.post.entity.PostEntity;
import com.trip.Let.sGo.user.entity.UserEntity;
import com.trip.Let.sGo.post.repository.PostRepository;
import com.trip.Let.sGo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public PostDTO selectPost(Integer postId) {
        PostEntity post = this.postRepository.getReferenceById(postId);
        PostDTO postDTO = new PostDTO(post);

        return postDTO;
    }
}
