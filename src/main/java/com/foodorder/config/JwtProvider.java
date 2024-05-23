package com.foodorder.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class JwtProvider {
    SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRECT_KEY.getBytes());

    public String generateToken(Authentication auth){
        Collection <? extends GrantedAuthority>authorities=auth.getAuthorities();
        String roles = populateAuthorities(authorities);

        String jwt = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("email",auth.getName())
                .claim("authorities", roles)
                .signWith(key)
                .compact();
        return jwt;
    }

    private String populateAuthorities(Collection<?extends GrantedAuthority> authorities) {
        Set<String> auths = new HashSet<>();

        for(GrantedAuthority authority:authorities) {
            auths.add(authority.getAuthority());
        }
        return String.join(",", auths);
    }

    public String generateTokenLoginEMail(String email){
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 86400000))
                .claim("email",email)
                .signWith(key)
                .compact();
    }
    public  String getEmailFromToken(String jwt) {
        jwt =jwt.substring(7);
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        return String.valueOf(claims.get("email"));
    }
}