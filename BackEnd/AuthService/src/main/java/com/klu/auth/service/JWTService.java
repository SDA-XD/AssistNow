package com.klu.auth.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

    public String generateJWT(Map<String,String> user,String role,Integer id){
        String key="AssistNowSecretKeyForJWTGeneration123456789";
        SecretKey secretKey=Keys.hmacShaKeyFor(key.getBytes());

        Map<String,String> claims=new HashMap<>();
        claims.put("un",user.get("username"));
        claims.put("role",role);
        claims.put("id",id.toString());

        return Jwts.builder()
                .claims(claims)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+604800000))
                .signWith(secretKey)
                .compact();
    }

    public Map<String,String> validateJWT(String token)throws Exception{
        String key="AssistNowSecretKeyForJWTGeneration123456789";
        SecretKey secretKey=Keys.hmacShaKeyFor(key.getBytes());

        Claims claims=Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();

        if(claims==null||claims.getExpiration().before(new Date())){
            throw new Exception("Token Invalid!");
        }

        Map<String,String> parsedJWT=new HashMap<>();
        parsedJWT.put("username",claims.get("un").toString());
        parsedJWT.put("role",claims.get("role").toString());
        parsedJWT.put("id",claims.get("id").toString());

        return parsedJWT;
    }
}