package com.savvas.jobapp.util;

import com.savvas.jobapp.dto.Token;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @Author: TCMALTUNKAN - MEHMET ANIL ALTUNKAN
 * @Date: 30.12.2019:09:40, Pzt
 **/
@Service
public class TokenProviderImpl implements TokenProvider {
    private String tokenSecret = "myTokenSecret";
    private Long tokenExpirationMsec = 3600000L;

    @Override
    public Token generateAccessToken(String subject) {
        Date now = new Date();
        Long duration = now.getTime() + tokenExpirationMsec;
        Date expiryDate = new Date(duration);
        String token = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, tokenSecret)
                .compact();
        return new Token(Token.TokenType.ACCESS, token, duration, LocalDateTime.ofInstant(expiryDate.toInstant(), ZoneId.systemDefault()));
    }

    @Override
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

    @Override
    public LocalDateTime getExpiryDateFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(tokenSecret).parseClaimsJws(token).getBody();
        return LocalDateTime.ofInstant(claims.getExpiration().toInstant(), ZoneId.systemDefault());
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(tokenSecret).parse(token);
            return true;
        } catch (SignatureException ex) {
            ex.printStackTrace();
        } catch (MalformedJwtException ex) {
            ex.printStackTrace();
        } catch (ExpiredJwtException ex) {
            ex.printStackTrace();
        } catch (UnsupportedJwtException ex) {
            ex.printStackTrace();
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
