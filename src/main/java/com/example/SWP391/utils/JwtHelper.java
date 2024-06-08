package com.example.SWP391.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtHelper {
    @Value("${jwt.private-key}")
    private String key;
    private long plusTimme = 8 * 60 * 60 * 1000;

    public String genertateToken(String data) {
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(key));
        Date currentDate = new Date();
        long futureTime = currentDate.getTime() + plusTimme;
        Date futureDate = new Date(futureTime);
        String token = Jwts.builder()
                .subject(data)
                .expiration(futureDate)
                .signWith(secretKey)
                .compact();

        return token;

    }
}
