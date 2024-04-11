package com.trip.Let.sGo.post;

import com.trip.Let.sGo.exception.DataNotFoundException;
import com.trip.Let.sGo.exception.ForbiddenAccessException;
import com.trip.Let.sGo.post.dto.CreatePostDTO;
import com.trip.Let.sGo.post.dto.PostDTO;
import com.trip.Let.sGo.post.entity.PostEntity;
import com.trip.Let.sGo.user.entity.UserEntity;
import com.trip.Let.sGo.post.repository.PostRepository;
import com.trip.Let.sGo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


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
        PostEntity existingPost = findPostOrThrowById(postId);

        // 게시물의 작성자(author) 정보 가져오기
        UserEntity author = existingPost.getAuthor();
        String user = author.getUsername();

        // 현재 사용자의 username과 게시물의 작성자(getUsername)가 일치하는지 확인
        if (!user.equals(username)) { // 유저 권한 확인
            throw new ForbiddenAccessException("작성자 "+user+" 외에는 수정할 수 없습니다.");
        }

        existingPost.setTitle(createPostDTO.getTitle());
        existingPost.setContent(createPostDTO.getContent());
        postRepository.save(existingPost);

        return new PostDTO(existingPost);
    }

    //글 삭제
    public void deletePost(Integer postId, String username) {
        PostEntity existingPost = findPostOrThrowById(postId);

        // 게시물의 작성자(author) 정보 가져오기
        UserEntity author = existingPost.getAuthor();
        String user = author.getUsername();

        if (!user.equals((username))) {
            throw new ForbiddenAccessException("작성자 "+user+" 외에는 삭제할 수 없습니다.");
        }

        this.postRepository.delete(existingPost);
    }

    public PostDTO getPost(Integer postId) {
        PostEntity post = findPostOrThrowById(postId);
        PostDTO postDTO = new PostDTO(post);

        return postDTO;

    }

    public List<PostDTO> paginatePost(Integer page, Integer size, String direction) {
        Pageable pageable;
        if(direction.equals("ASC")) {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createDate"));
        } else {
            pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createDate"));
        }
        Page<PostDTO> list = this.postRepository.findAll(pageable).map((post) -> new PostDTO(post));

        // 다음 페이지 정보 (무한 스크롤에서 사용할 계획)
        System.out.println(pageable.next());

        return list.getContent();
    }

    //게시글 조회 오류
    public PostEntity findPostOrThrowById(Integer postId){
        Optional<PostEntity> postOptional = this.postRepository.findById(postId);
        if(!postOptional.isPresent()){
            throw new DataNotFoundException("DB에서 " + postId + "번 게시글을 조회하지 못했습니다.");
        }

        return postOptional.get();
    }

    //좋아요기능
    public void likePost(Integer postId, String username) {
        PostEntity post = findPostOrThrowById(postId);
        UserEntity user = this.userRepository.findByUsername(username);

        // 좋아요 명단에 넣기
        post.getVoter().add(user);

        // 좋아요 개수 1 증가
        post.setLikeCount(post.getLikeCount() + 1);

        this.postRepository.save(post);
    }
}
