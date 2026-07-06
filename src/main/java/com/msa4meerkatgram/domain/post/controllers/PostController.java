package com.msa4meerkatgram.domain.post.controllers;

import com.msa4meerkatgram.domain.post.entities.PostMybatis;
import com.msa4meerkatgram.domain.post.requests.PostCreateRequest;
import com.msa4meerkatgram.domain.post.requests.PostIndexRequest;
import com.msa4meerkatgram.domain.post.responses.PostIndexResponse;
import com.msa4meerkatgram.domain.post.services.PostService;
import com.msa4meerkatgram.global.responses.GlobalResponse;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<GlobalResponse<PostIndexResponse>> index(PostIndexRequest postIndexRequest) {
        PostIndexResponse postIndexResponse = postService.index(postIndexRequest);

        return ResponseEntity.status(200).body(
            GlobalResponse.<PostIndexResponse>builder()
                .code("00")
                .message("정상처리")
                .data(postIndexResponse)
                .build()
        );
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<GlobalResponse<PostMybatis>> show (
        @Min(value = 1, message = "1 이상의 숫자만 허용합니다") @PathVariable long id
    ) {
        PostMybatis result = postService.show(id);

        return ResponseEntity.status(200).body(
            GlobalResponse.<PostMybatis>builder()
                .code("00")
                .message("게시글 상세 정상 처리")
                .data(result)
                .build()
        );
    }

    // 💡 새로 추가된 "게시물 작성" API
    @PostMapping("/posts")
    public ResponseEntity<GlobalResponse<String>> createPost(@RequestBody PostCreateRequest request) {

        Long userId = 22L;

        postService.createPost(request, userId);

        return ResponseEntity.status(200).body(
            GlobalResponse.<String>builder()
                .code("00")
                .message("게시물이 성공적으로 작성되었습니다.")
                .data(null)
                .build()
        );
    }
}