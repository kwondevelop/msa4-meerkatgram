package com.msa4meerkatgram.domain.post.services;

import com.msa4meerkatgram.domain.post.entities.Post;
import com.msa4meerkatgram.domain.post.mapper.PostMapper;
import com.msa4meerkatgram.domain.post.requests.PostCreateRequest;
import com.msa4meerkatgram.domain.post.requests.PostIndexRequest;
import com.msa4meerkatgram.domain.post.responses.PostIndexResponse;
import com.msa4meerkatgram.global.errors.custom.DeletedRecordException;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostMapper postMapper;

    public PostIndexResponse index(PostIndexRequest postIndexRequest) {
        int offset = (postIndexRequest.page() - 1) * postIndexRequest.limit();
            
        // 특정 페이지 게시글 조회
        List<Post> posts = postMapper.getPagination(postIndexRequest.limit(), offset);
        
        // 토탈 획득
        long total = postMapper.getTotal();
        boolean lastPage = offset + postIndexRequest.limit() >= total;
        
        // 컨트롤러 전달
        return PostIndexResponse.builder()
            .total(total)
            .lastPage(lastPage)
            .posts(posts)
            .build();
    }
    
    public Post show(long id) {
        Post post = postMapper.findByPk(id);
        
        if (post == null) {
            throw new DeletedRecordException("이미 삭제된 게시글입니다");
        }
        return post;
    }
    
    @Transactional
    public void createPost(PostCreateRequest request, Long userId) {
        Post post = Post.builder()
            .userId(userId)
            .postContent(request.getPostContent())
            .postImageUrl(request.getPostImageUrl())
            .build();
        
        postMapper.insert(post);
    }
}
