package com.trip.Let.sGo;

import com.trip.Let.sGo.post.entity.PostEntity;
import com.trip.Let.sGo.post.repository.PostRepository;
import com.trip.Let.sGo.user.entity.UserEntity;
import com.trip.Let.sGo.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ApplicationTests {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PostRepository postRepository;

	@Test
	void contextLoads() {
//		UserEntity user = this.userRepository.findByUsername("admin");
//
//		for(int i=1;i<=100;i++){
//			PostEntity post = new PostEntity();
//			post.setAuthor(user);
//			post.setContent(String.format("[%03d] > 계획 게시판 임시 게시글 내용", i));
//			post.setTitle(String.format("[%03d] > 계획 게시판 임시 게시글 제목", i));
//			post.setCategory("PLAN");
//
//			postRepository.save(post);
//		}
	}

}
