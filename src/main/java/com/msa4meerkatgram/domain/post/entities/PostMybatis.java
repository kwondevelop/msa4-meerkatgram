package com.msa4meerkatgram.domain.post.entities;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostMybatis {
    private Long id;
    private Long userId;
    private String content;
    private String image;
    private String createdAt;
    private String updatedAt;
    private String deletedAt;
    private String postContent;
    private String postImageUrl;
}
