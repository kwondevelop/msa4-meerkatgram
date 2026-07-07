package com.msa4meerkatgram.domain.post.responses;

import lombok.Builder;

import java.util.List;

@Builder
public record PostIndexResponse(
    long total,
    boolean lastPage,
    List<PostWithUserRes> posts
) {
}
