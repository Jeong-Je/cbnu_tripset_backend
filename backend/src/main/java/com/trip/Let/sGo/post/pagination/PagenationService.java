package com.trip.Let.sGo.post.pagination;

import com.trip.Let.sGo.exception.DataNotFoundException;
import com.trip.Let.sGo.post.dto.PaginationPostDTO;
import com.trip.Let.sGo.post.entity.PostEntity;
import com.trip.Let.sGo.post.repository.PostRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class PagenationService {
    private PostRepository postRepository;

//    public PaginationResult paginateLikePost(Integer page, Integer size, String direction, String username, String category){
//        Pageable pageable = direction.equals("ASC")
//                ? PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "createDate"))
//                : PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createDate"));
//
//
//
//        //username 게시글 찾기
////        Page<PostEntity> postPage;
////        if (username.equals("all")) {
////            postPage = this.postRepository.findAll(pageable);
////        } else {
////            UserEntity user = this.userRepository.findByUsername(username);
////            if (user == null) {
////                throw new DataNotFoundException("User not found with username: " + username);
////            }
////            postPage = this.postRepository.findByAuthor(user, pageable);
////        }
//
//        Page<PostEntity> postPage;
//        if(category.equals("FREE")) {
//            postPage = this.postRepository.findByCategory("FREE", pageable);
//        } else if (category.equals("PLAN")) {
//            postPage = this.postRepository.findByCategory("PLAN", pageable);
//        } else {
//            postPage = this.postRepository.findAll(pageable);
//        }
//
//        //게시글이 없을때
//        if(postPage == null){
//            throw new DataNotFoundException("게시글을 조회하지 못했습니다");
//        }
//
//        // 다음 페이지 정보 (무한 스크롤에서 사용할 계획)
//        //String nextPageLink = String.format("http://%s/post?page=%d&size=%d&direction=%s&category=%s", base_url, pageable.next().getPageNumber(), pageable.next().getPageSize(), direction, category);
//
//        //return new PaginationResult(postPage.map(PaginationPostDTO::new).getContent(), nextPageLink);
//    }
}
