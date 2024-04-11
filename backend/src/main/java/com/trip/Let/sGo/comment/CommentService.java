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

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public CommentDTO createComment(CommentDTO commentDTO, String author, Integer postId) {
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

    // comment 수정
    public CommentDTO modifyComment(CommentDTO commentDTO, String username, Integer commentId) {
        UserEntity user = this.userRepository.findByUsername(username); // 현재 사용하고 있는 user의 이름 (string)
        CommentEntity comment = this.commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("Invalid post id: " + commentId));

        comment.setComment(commentDTO.getComment());

        this.commentRepository.save(comment);

        CommentDTO newComment = new CommentDTO();
        newComment.setComment(comment.getComment());

        return newComment;

    }
    // comment 삭제
    public void deleteComment(Integer commentId, String username) {
        UserEntity user = this.userRepository.findByUsername(username); // 현재 사용하고 있는 user의 이름 (string)
        CommentEntity comment = this.commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("Invalid post id: " + commentId));

        // 현재 사용자의 username과 게시물의 작성자(username)가 일치하는지 확인
        if (!user.getUsername().equals(username)) { // 유저 권한 확인
            throw new IllegalArgumentException("You are not authorized to delete this post.");
        }

        this.commentRepository.delete(comment);
    }

    public void likeComment(Integer commentId, String username) {
        CommentEntity comment = this.commentRepository.getReferenceById(commentId);
        UserEntity user = this.userRepository.findByUsername(username);

        // 좋아요 명단에 넣기
        comment.getVoter().add(user);

        // 좋아요 개수 1 증가
        comment.setLikeCount(comment.getLikeCount() + 1);

        this.commentRepository.save(comment);
    }
}