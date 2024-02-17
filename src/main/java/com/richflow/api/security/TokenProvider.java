package com.richflow.api.security;

import com.richflow.api.request.UserLogin;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import static io.jsonwebtoken.Jwts.parser;

@Slf4j
@Service
public class TokenProvider {
    private final String SECRET_KEY = "testSecretKey";
    public String create(UserLogin userLogin) {
        Date expiryDate = Date.from(
                Instant.now()
                        .plus(1, ChronoUnit.DAYS));

        // JWT Token 생성
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .setSubject(String.valueOf(userLogin.getMemberIdx())) // sub
                .setIssuer("richflow app")            // iss
                .setIssuedAt(new Date())              // iat
                .setExpiration(expiryDate)            // exp
                .compact();
    }

    public String validate(String token) {
        log.info("tokenProvider > validate :: " + token);

        Claims claims = Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}
