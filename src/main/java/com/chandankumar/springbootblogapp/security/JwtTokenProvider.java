package com.chandankumar.springbootblogapp.security;

import com.chandankumar.springbootblogapp.exception.BlogApiException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();

        Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);

        String jwtToken  = Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(expireDate)
                .signWith(key())
                .compact();

        return jwtToken;
    }

    private Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret));
    }

    public String getUsername(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String token) {

        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);

            return true;
        }
        catch (MalformedJwtException e){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Invalid Jwt Token");
        }
        catch (ExpiredJwtException e){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Expired Jwt Token");
        }
        catch (UnsupportedJwtException e){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Unsupported Jwt Token");
        }
        catch (IllegalArgumentException e){
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Jwt claims string is empty");
        }


    }


}
