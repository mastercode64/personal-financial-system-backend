package com.mastercode.personalfinancialsystem.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Date;

public class TokenAuthenticationService {
    // EXPIRATION_TIME = 30 minutes
    static final long EXPIRATION_TIME = 30 * 60 * 1000;
    static final String SECRET = "MySecret";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    static void addAuthentication(HttpServletResponse response, String tokenSubject) {


        String JWT = Jwts.builder()
                .setSubject(tokenSubject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();

        response.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
    }

    static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token == null) return null;

        Claims jwtClaims = null;
        try {
            // faz parse do token
            jwtClaims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token.replace(TOKEN_PREFIX + " ", ""))
                    .getBody();

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }

        String tokenSubject = jwtClaims.getSubject();
        if (tokenSubject == null) return null;
        return new UsernamePasswordAuthenticationToken(tokenSubject, null, Collections.emptyList());
    }
}
