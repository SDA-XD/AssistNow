package com.klu.service;

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
