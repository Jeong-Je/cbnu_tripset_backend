package com.trip.Let.sGo.post.pagination;

import com.trip.Let.sGo.post.PostController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PagenationController {

    private PagenationController pagenationService;

    @PostMapping("/like")
    public PaginationResult paginateLikePost(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                             @RequestParam(value = "size", defaultValue = "10") Integer size,
                                             @RequestParam(value = "direction", defaultValue = "DESC") String direction,
                                             @RequestParam(value = "username", defaultValue = "all") String username,
                                             @RequestParam(value = "category", defaultValue = "all") String category){
        return this.pagenationService.paginateLikePost(page, size, direction, username, category);
    }
}
