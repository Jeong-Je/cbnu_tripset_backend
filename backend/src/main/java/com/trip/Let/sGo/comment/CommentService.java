package com.trip.Let.sGo.comment;

import com.trip.Let.sGo.comment.dto.CommentDTO;
import com.trip.Let.sGo.comment.entity.CommentEntity;
import com.trip.Let.sGo.post.entity.PostEntity;
import com.trip.Let.sGo.post.repository.PostRepository;
import com.trip.Let.sGo.user.entity.UserEntity;
import com.trip.Let.sGo.user.repository.UserRepository;
import com.trip.Let.sGo.comment.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentDTO createComment(CommentDTO commentDTO, String author, Integer postId){
        UserEntity user = this.userRepository.findByUsername(author);
        PostEntity post = this.postRepository.getReferenceById(postId);

        CommentEntity comment = new CommentEntity();

        comment.setAuthor(user);
        comment.setComment(commentDTO.getComment());
        comment.setPostEntity(post);

        this.commentRepository.save(comment);

        CommentDTO newComment = new CommentDTO();
        newComment.setAuthor(comment.getAuthor());
        newComment.setComment(comment.getComment());
        newComment.setId(comment.getId());
        newComment.setCreateDate(comment.getCreateDate());

        return newComment;
    }

    public void likeComment(Integer commentId, String username) {
        CommentEntity comment = this.commentRepository.getReferenceById(commentId);
        UserEntity user = this.userRepository.findByUsername(username);

        comment.getVoter().add(user);
        this.commentRepository.save(comment);
    }
}
