package com.se231.onlineedu.security.jwt;

import com.se231.onlineedu.security.services.UserPrinciple;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.DefaultClock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * JwtAuthTokenFilter class
 *
 * provide jwt
 *
 * @author Yuxuan Liu
 *
 * @date 2019/07/01
 */
@Component
@PropertySource(value={"classpath:jwt.properties"})
public class JwtProvider {

    private Clock clock = DefaultClock.INSTANCE;

    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpiration}")
    private int jwtExpiration;

    public String generateJwtToken(UserDetails userPrincipal) {
        Date createdDate = clock.now();
        Date expirationDate = new Date(createdDate.getTime() + jwtExpiration);
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(createdDate)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        }   catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }

        return false;
    }
}