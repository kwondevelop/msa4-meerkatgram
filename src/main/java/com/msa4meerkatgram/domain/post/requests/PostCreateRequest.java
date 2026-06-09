package com.msa4meerkatgram.domain.post.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostCreateRequest {
    private String postContent;
    private String postImageUrl;
}
