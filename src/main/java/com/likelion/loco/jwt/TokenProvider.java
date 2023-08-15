package com.likelion.loco.jwt;


import com.likelion.loco.entities.User;
import com.likelion.loco.global.enums.RoleType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@Setter
public class TokenProvider {
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    public String create(String userId, RoleType roleType) {
        // 기한은 지금부터 1일로 설정
        Date expiryDate = Date.from(
                Instant.now()
                        .plus(1, ChronoUnit.DAYS));
        Map<RoleType, String> userMap = new HashMap<>();
        userMap.put(roleType,userId);


        // JWT Token 생성
        return Jwts.builder()
                // header에 들어갈 내용 및 서명을 하기 위한 SECRET_KEY
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                // payload에 들어갈 내용
                .setSubject(userMap.toString()) // sub
                .setIssuer("team 4")
                // iss
                .setIssuedAt(new Date())		// iat
                .setExpiration(expiryDate)		// exp
                .compact();
    }

    public String validateAndGetUserId(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject(); // subject 즉 사용자 아이디를 리턴한다.
    }
    public String extractRoleFromBearerToken(String token) {
        String userId = token.replace("{", "").replace("}", "");
            String[] parts = userId.split("="); // "="을 기준으로 분리
            if (parts.length > 0) {
                return parts[0]; // 첫 번째 부분이 역할 정보
            }
        return null; // Bearer Token이 없거나 역할 정보가 없는 경우
    }
    public String extractUserIdFromBearerToken(String token) {
            String userId = token.replace("{", "").replace("}", "");
            String[] parts = userId.split("="); // "="을 기준으로 분리
            if (parts.length > 0) {
                return parts[1]; // 첫 번째 부분이 역할 정보
            }


        return null; // Bearer Token이 없거나 역할 정보가 없는 경우
    }

}