package com.trip.Let.sGo.post;

import com.trip.Let.sGo.exception.DataNotFoundException;
import com.trip.Let.sGo.exception.ForbiddenAccessException;
import com.trip.Let.sGo.post.dto.CreatePostDTO;
import com.trip.Let.sGo.post.dto.PaginationPostDTO;
import com.trip.Let.sGo.post.dto.PostDTO;
import com.trip.Let.sGo.post.entity.PostEntity;
import com.trip.Let.sGo.post.pagination.PaginationResult;
import com.trip.Let.sGo.user.entity.UserEntity;
import com.trip.Let.sGo.post.repository.PostRepository;
import com.trip.Let.sGo.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Value("${aws_server_ip}")
    private String base_url;

    public PostDTO createPost(CreatePostDTO createPostDTO, String username) {
        UserEntity user = this.userRepository.findByUsername(username);
        PostEntity post = new PostEntity();

        post.setAuthor(user);
        post.setTitle(createPostDTO.getTitle());
        post.setContent(createPostDTO.getContent());
        post.setCategory(createPostDTO.getCategory());
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
        existingPost.setCategory(createPostDTO.getCategory());
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

    public PaginationResult paginatePost(Integer page, Integer size, String direction,
                                         String username, String category, Boolean like) {
        Pageable pageable = direction.equals("ASC")
                ? PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createDate"))
                : PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createDate"));

        //username 게시글 찾기
//        Page<PostEntity> postPage;
//        if (username.equals("all")) {
//            postPage = this.postRepository.findAll(pageable);
//        } else {
//            UserEntity user = this.userRepository.findByUsername(username);
//            if (user == null) {
//                throw new DataNotFoundException("User not found with username: " + username);
//            }
//            postPage = this.postRepository.findByAuthor(user, pageable);
//        }

        Page<PostEntity> postPage;

        if (category.equals("FREE")) {
            postPage = this.postRepository.findByCategory("FREE", pageable);
        } else if (category.equals("PLAN")) {
            postPage = this.postRepository.findByCategory("PLAN", pageable);
        } else {
            postPage = this.postRepository.findAll(pageable);
        }

        //게시글이 없을때
        if (postPage == null || postPage.isEmpty()) {
            throw new DataNotFoundException("게시글을 조회하지 못했습니다");
        }

        // 다음 페이지 정보 (무한 스크롤에서 사용할 계획)
        String nextPageLink = String.format("http://%s/post?page=%d&size=%d&direction=%s&category=%s", base_url,
                pageable.next().getPageNumber(), pageable.next().getPageSize(), direction, category, like);

        return new PaginationResult(postPage.map(PaginationPostDTO::new).getContent(), nextPageLink);
    }

    public List<PostDTO> getILikePosts(String username) {
        UserEntity user = this.userRepository.findByUsername(username);
        Integer voterId = user.getId(); // 좋아요 누른 사용자 id
        List<PostDTO> iLikePosts = this.postRepository.findAllByVoterId(voterId).stream().map((post) -> new PostDTO(post)).collect(Collectors.toList());
        return iLikePosts;
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
    public ResponseEntity<String> likePost(Integer postId, String username) {
        PostEntity post = findPostOrThrowById(postId);
        UserEntity user = this.userRepository.findByUsername(username);

        // 이미 좋아요를 눌렀는지 확인
        if (!post.getVoter().contains(user)) {
            // 좋아요 명단에 넣기
            post.getVoter().add(user);
//            user.setLikedPosts((Set<PostEntity>) post); //userEntity에도 추가
            // 좋아요 개수 1 증가
            post.setLikeCount(post.getLikeCount() + 1);

            this.postRepository.save(post);

            // 성공적인 응답 반환
            String responseJson = "{\"message\": \"좋아요가 성공적으로 추가되었습니다.\"}";
            return ResponseEntity.ok(responseJson);
        } else {
            // 이미 좋아요를 누른 경우에는 Conflict 상태 코드 반환
            String responseJson = "{\"message\": \"이미 좋아요를 눌렀습니다.\"}";
            return ResponseEntity.status(HttpStatus.CONFLICT).body(responseJson);
        }
    }
}
