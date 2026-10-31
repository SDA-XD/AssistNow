
package com.klu.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.model.Assignment;
import com.klu.repo.AssignmentRepository;

@Service
public class AssignmentService{

    @Autowired
    AssignmentRepository repo;

    @Autowired
    JWTService jwtservice;

    public Object createAssignment(Assignment assignment,String token){
        try{
            Map<String,String> user=jwtservice.validateJWT(token);

            if(!user.get("role").equals("3")){
                return Map.of("code",403,"message","Only Admin Can Assign Complaints");
            }

            assignment.setId(null);
            assignment.setAssignedby(Integer.parseInt(user.get("id")));
            assignment.setAssigneddate(LocalDate.now().toString());
            assignment.setStatus("Assigned");

            return repo.save(assignment);

        }catch(Exception e){
            return Map.of("code",401,"message","Token Invalid or Expired");
        }
    }

    public Object getAllAssignments(String token){
        try{
            Map<String,String> user=jwtservice.validateJWT(token);

            if(user.get("role").equals("3")){
                return repo.findAll();
            }

            if(user.get("role").equals("2")){
                return repo.findByAssignedto(Integer.parseInt(user.get("id")));
            }

            return Map.of("code",403,"message","Access Denied");

        }catch(Exception e){
            return Map.of("code",401,"message","Token Invalid or Expired");
        }
    }

    public Object getAssignmentById(Integer id,String token){
        try{
            Map<String,String> user=jwtservice.validateJWT(token);

            Assignment assignment=repo.findById(id).orElse(null);

            if(assignment==null){
                return Map.of("code",404,"message","Assignment Not Found");
            }

            if(user.get("role").equals("3")||
               (user.get("role").equals("2")&&
               assignment.getAssignedto().equals(Integer.parseInt(user.get("id"))))){
                return assignment;
            }

            return Map.of("code",403,"message","Access Denied");

        }catch(Exception e){
            return Map.of("code",401,"message","Token Invalid or Expired");
        }
    }

    public Object updateAssignment(Integer id,Assignment assignment,String token){
        try{
            Map<String,String> user=jwtservice.validateJWT(token);

            if(!user.get("role").equals("3")){
                return Map.of("code",403,"message","Only Admin Can Update Assignments");
            }

            Assignment existing=repo.findById(id).orElse(null);

            if(existing==null){
                return Map.of("code",404,"message","Assignment Not Found");
            }

            existing.setAssignedto(assignment.getAssignedto());
            existing.setStatus(assignment.getStatus());
            existing.setRemarks(assignment.getRemarks());

            return repo.save(existing);

        }catch(Exception e){
            return Map.of("code",401,"message","Token Invalid or Expired");
        }
    }

    public Object deleteAssignment(Integer id,String token){
        try{
            Map<String,String> user=jwtservice.validateJWT(token);

            if(!user.get("role").equals("3")){
                return Map.of("code",403,"message","Only Admin Can Delete Assignments");
            }

            if(!repo.existsById(id)){
                return Map.of("code",404,"message","Assignment Not Found");
            }

            repo.deleteById(id);

            return Map.of("code",200,"message","Assignment Deleted Successfully");

        }catch(Exception e){
            return Map.of("code",401,"message","Token Invalid or Expired");
        }
    }
}
