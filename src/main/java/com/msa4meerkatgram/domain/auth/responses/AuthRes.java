package com.msa4meerkatgram.domain.auth.responses;

import com.msa4meerkatgram.domain.user.entities.User;
import com.msa4meerkatgram.domain.user.responses.UserRes;
import com.msa4meerkatgram.domain.user.responses.UserWithPostCountRes;
import com.msa4meerkatgram.global.annotations.openapi.ApiNotValidErrorResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

@Schema(description = "로그인 리스폰스")
public record AuthRes(
    UserWithPostCountRes user
    ,String accessToken
) {
    public static AuthRes from(User user, long countPost, String accessToken) {
        return new AuthRes(
                UserWithPostCountRes.from(user, countPost),
                accessToken
        );
    }
}
