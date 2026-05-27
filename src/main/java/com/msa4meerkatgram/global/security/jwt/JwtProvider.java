package com.msa4meerkatgram.global.security.jwt;

import com.msa4meerkatgram.domain.user.entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {
    private final JwtConfig jwtConfig;
    private final SecretKey secretKey; 
    
    public JwtProvider(JwtConfig jwtConfig) {
        this.jwtConfig = jwtConfig;
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtConfig.secret()));
    }
    
    private String generateToken(User user, long ttl) {
        Date now = new Date();
        
        return Jwts.builder()
            
            // header 세팅
            .header()
            
            // 토큰 유형 설정
            .type(jwtConfig.type())
            
            // 추가 연결
            .and()
            
            // subject : 유저를 특정하는 id 세팅에 주로 사용
            .subject(String.valueOf(user.getId()))
            
            // 토큰 발급자
            .issuer(jwtConfig.issuer())
            
            // 도큰 발급 시간
            .issuedAt(now)
            
            // 토큰 만료 시간
            .expiration(new Date(now.getTime() + ttl))
            
            // private claim 설정
            .claim("role", user.getRole())
            
            // 시그니처 작성
            .signWith(secretKey)
            
            .compact();
    }
    
    public String generateAccessToken(User user) {
        return this.generateToken(user, jwtConfig.accessTokenExpiry());
    }

    public String generateRefreshToken(User user) {
        return this.generateToken(user, jwtConfig.refreshTokenExpiry());
    }
}

