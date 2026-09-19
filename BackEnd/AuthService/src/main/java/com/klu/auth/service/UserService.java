package com.klu.auth.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.auth.model.User;
import com.klu.auth.repo.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository UR;

    @Autowired
    JWTService jwtservice;

    public Object signUpService(User u1){
        Map<String,Object> response=new HashMap<>();

        try{
            User user=UR.findByUsername(u1.getUsername());

            if(user!=null){
                response.put("code",501);
                response.put("message","User exists already");
            }else{
                u1.setRole(1);
                UR.save(u1);

                response.put("code",200);
                response.put("message","User Registered :)");
            }

            return response;

        }catch(Exception e){
            response.put("code",500);
            response.put("message",e.getMessage());
            return response;
        }
    }

    public Object signinService(Map<String,String> u1){
        Map<String,Object> response=new HashMap<>();

        try{
            User user=UR.findByUsername(u1.get("username"));

            if(user==null||!user.getPassword().equals(u1.get("password"))){
                response.put("code",500);
                response.put("message","Sign in Failed");
            }else{
                response.put("code",200);
                response.put("jwt",jwtservice.generateJWT(
                        u1,
                        user.getRole().toString(),
                        user.getId()
                ));
            }

            return response;

        }catch(Exception e){
            response.put("code",500);
            response.put("message",e.getMessage());
            return response;
        }
    }
    
    public Object validateToken(String token){
        try{
            return jwtservice.validateJWT(token);
        }catch(Exception e){
            Map<String,Object> response=new HashMap<>();
            response.put("code",401);
            response.put("message","Token Invalid or Expired");
            return response;
        }
    }
    
    public Object createUser(User u1,String token){
        Map<String,Object> response=new HashMap<>();

        try{
            Map<String,String> admin=jwtservice.validateJWT(token);

            if(!admin.get("role").equals("3")){
                response.put("code",403);
                response.put("message","Only admin can create users");
                return response;
            }

            if(u1.getRole()!=2&&u1.getRole()!=3){
                response.put("code",400);
                response.put("message","Invalid role");
                return response;
            }

            User user=UR.findByUsername(u1.getUsername());

            if(user!=null){
                response.put("code",501);
                response.put("message","User already exists");
            }else{
                UR.save(u1);
                response.put("code",200);
                response.put("message","User Created Successfully");
            }

            return response;

        }catch(Exception e){
            response.put("code",401);
            response.put("message","Token Invalid or Expired");
            return response;
        }
    }
}