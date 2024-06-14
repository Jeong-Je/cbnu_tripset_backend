package com.trip.Let.sGo.post.pagination;

import com.trip.Let.sGo.post.dto.PaginationPostDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaginationResult {
    private List<PaginationPostDTO> posts;
    private String nextLink;

    public PaginationResult(List<PaginationPostDTO> posts, String nextLink) {
        this.posts = posts;
        this.nextLink = nextLink;
    }
}
