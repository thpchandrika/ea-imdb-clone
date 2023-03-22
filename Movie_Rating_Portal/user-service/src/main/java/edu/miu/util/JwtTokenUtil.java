package edu.miu.util;

import edu.miu.dto.TokenDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;

@Component
public class JwtTokenUtil {

    @Value("${jwt.secret}")
    private String jwtSecret;

    public TokenDto generateAccessToken(Authentication authenticate) {
        return TokenDto.builder().token(Jwts.builder()
                .setIssuer("edu.miu")
                .setSubject(authenticate.getName())
                .claim("authorities",authenticate.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(Date.from(Instant.now().plusSeconds(36000)))
                .signWith(Keys.hmacShaKeyFor(jwtSecret.getBytes())).compact()).build();
    }
}
