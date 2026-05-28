package com.msa4meerkatgram.global.security.cookie;

import com.msa4meerkatgram.global.security.jwt.JwtConfig;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import java.util.Arrays;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CookieManager {

    private final RequestMappingHandlerAdapter requestMappingHandlerAdapter;
    private final JwtConfig jwtConfig;

    // Request Header에서 특정 쿠키를 획득 (Optional 반환)
    public Optional<Cookie> getCookie(HttpServletRequest request, String name) {
        // 쿠키 존재 여부 확인
        if(request.getCookies() == null) {
            return Optional.empty();
        }
        // name에 맞는 쿠키 획득
        return Arrays.stream(request.getCookies())
            .filter(cookie -> cookie.getName().equals(name))
            .findFirst();
    }
    
    // 쿠키 생성 메소드
    public void setCookie(HttpServletResponse response, String name, String value, int maxAge, String path) {
        // 해당 이름과 값으로 쿠키 인스턴스 생성
        Cookie cookie = new Cookie(name, value);
        // 쿠키를 사용할 path 설정
        cookie.setPath(path);
        // 쿠키의 유효 시간 설정
        cookie.setMaxAge(maxAge);
        // HTTPOnly 설정 (XSS 공격 방지)
        cookie.setHttpOnly(true);
        // 시큐어 설정 (MITM 공격 방지)
        cookie.setSecure(jwtConfig.secure());
    }
}

