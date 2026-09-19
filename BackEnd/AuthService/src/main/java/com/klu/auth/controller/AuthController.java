package com.klu.auth.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.auth.model.User;
import com.klu.auth.service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    UserService service;

    @PostMapping("/signup")
    public Object signUP(@RequestBody User u1){
        return service.signUpService(u1);
    }

    @PostMapping("/signin")
    public Object signin(@RequestBody Map<String,String> u1){
        return service.signinService(u1);
    }
    
    @GetMapping("/validate")
    public Object validate(@RequestHeader("Authorization") String token){
        try{
            if(token.startsWith("Bearer ")){
                token=token.substring(7);
            }
            return service.validateToken(token);
        }catch(Exception e){
            return Map.of("code",401,"message","Token Invalid or Expired");
        }
    }
    
    @PostMapping("/create-user")
    public Object createUser(@RequestBody User u1,@RequestHeader("Authorization") String token){
        if(token.startsWith("Bearer ")){
            token=token.substring(7);
        }

        return service.createUser(u1,token);
    }
}