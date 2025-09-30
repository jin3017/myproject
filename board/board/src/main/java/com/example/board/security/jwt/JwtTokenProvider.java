package com.example.board.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; //클래스
import org.springframework.security.core.Authentication; //인터페이스
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
@Component
public class JwtTokenProvider {
    // 비밀키 32바이트 가져오기
    @Value("${jwt.secret}") private String secret;
    // 유효기간 가져오기
    @Value("${jwt.access-exp}") private String accessExp;

    private Key key(){
        // 서명 키 종류 지정 및 비밀키 인코딩 통일
        return Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
    // 토큰 생성 메서드
    public String generateToken(Long userId, String email){
        Date now = new Date();
        Date exp = new Date(now.getTime() + accessExp);

        return Jwts.builder()
                .setSubject(String.valueOf(userId)) // 식별자
                .claim("email",email)   // 사용자 최소 정보 준거
                .setIssuedAt(now)
                .setExpiration(exp)
                .signWith(key(), SignatureAlgorithm.HS256)
                .compact();
    }
    // 토큰 검증결과 출력 메서드
    public boolean validate(String token){
        try{
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }
    // 토큰 파서(검증까지)
    public Jws<Claims> parseClaims(String token){
        return Jwts.parserBuilder()
            .setSigningKey(key())//서명 검증용 키
            .build()
            .parseClaimsJws(token);
    }

    // Authentication 객체를 생성해주는 메서드 - 인증된 사용자에 대해 호출
    public Authentication toAuthentication(String token){
        Claims claims = parseClaims(token).getBody();
        String email = claims.get("email", String.class);
        // 3인자 생성자를 통한 인증된 객체 생성
        return new UsernamePasswordAuthenticationToken(email, token, Collections.emptyList());
    }
}
