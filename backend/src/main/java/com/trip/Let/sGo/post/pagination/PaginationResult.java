package com.trip.Let.sGo.post.pagination;

import com.trip.Let.sGo.post.dto.PostDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaginationResult {
    private List<PostDTO> posts;
    private String nextLink;

    public PaginationResult(List<PostDTO> posts, String nextLink) {
        this.posts = posts;
        this.nextLink = nextLink;
    }
}
