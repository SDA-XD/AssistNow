
package com.klu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.model.Complaint;
import com.klu.repo.ComplaintRepository;

@Service
public class ComplaintService{

    @Autowired
    ComplaintRepository repo;

    @Autowired
    JWTService jwtservice;

    public Object createComplaint(Complaint complaint,String token){
        try{
            Map<String,String> user=jwtservice.validateJWT(token);
            String role=user.get("role");

            if(!role.equals("1")&&!role.equals("3")){
                return Map.of("code",403,"message","Access Denied");
            }

            complaint.setId(null);
            complaint.setCreatedby(Integer.parseInt(user.get("id")));
            complaint.setAssignedto(null);
            complaint.setStatus(1);

            repo.save(complaint);

            return Map.of("code",200,"message","Complaint Created Successfully");

        }catch(Exception e){
            return Map.of("code",401,"message","Token Invalid or Expired");
        }
    }

    public Object getAllComplaints(String token){
        try{
            Map<String,String> user=jwtservice.validateJWT(token);
            String role=user.get("role");
            Integer userId=Integer.parseInt(user.get("id"));

            if(role.equals("3")){
                return repo.findAll();
            }

            if(role.equals("2")){
                return repo.findByAssignedto(userId);
            }

            if(role.equals("1")){
                return repo.findByCreatedby(userId);
            }

            return Map.of("code",403,"message","Access Denied");

        }catch(Exception e){
            return Map.of("code",401,"message","Token Invalid or Expired");
        }
    }

    public Object getComplaintById(Integer id,String token){
        try{
            Map<String,String> user=jwtservice.validateJWT(token);
            String role=user.get("role");
            Integer userId=Integer.parseInt(user.get("id"));

            Complaint complaint=repo.findById(id).orElse(null);

            if(complaint==null){
                return Map.of("code",404,"message","Complaint Not Found");
            }

            if(role.equals("3")||
               (role.equals("1")&&complaint.getCreatedby().equals(userId))||
               (role.equals("2")&&complaint.getAssignedto()!=null&&complaint.getAssignedto().equals(userId))){
                return complaint;
            }

            return Map.of("code",403,"message","Access Denied");

        }catch(Exception e){
            return Map.of("code",401,"message","Token Invalid or Expired");
        }
    }

    public Object updateComplaint(Integer id,Complaint complaint,String token){
        try{
            Map<String,String> user=jwtservice.validateJWT(token);
            String role=user.get("role");
            Integer userId=Integer.parseInt(user.get("id"));

            Complaint existing=repo.findById(id).orElse(null);

            if(existing==null){
                return Map.of("code",404,"message","Complaint Not Found");
            }

            if(role.equals("2")){
                if(existing.getAssignedto()==null||!existing.getAssignedto().equals(userId)){
                    return Map.of("code",403,"message","Access Denied");
                }
                existing.setStatus(complaint.getStatus());
            }else if(role.equals("3")){
                existing.setTitle(complaint.getTitle());
                existing.setDescription(complaint.getDescription());
                existing.setPriority(complaint.getPriority());
                existing.setStatus(complaint.getStatus());
                existing.setAssignedto(complaint.getAssignedto());
                existing.setDeadline(complaint.getDeadline());
            }else{
                return Map.of("code",403,"message","Access Denied");
            }

            return repo.save(existing);

        }catch(Exception e){
            return Map.of("code",401,"message","Token Invalid or Expired");
        }
    }

    public Object deleteComplaint(Integer id,String token){
        try{
            Map<String,String> user=jwtservice.validateJWT(token);

            if(!user.get("role").equals("3")){
                return Map.of("code",403,"message","Only Admin Can Delete Complaints");
            }

            if(!repo.existsById(id)){
                return Map.of("code",404,"message","Complaint Not Found");
            }

            repo.deleteById(id);

            return Map.of("code",200,"message","Complaint Deleted Successfully");

        }catch(Exception e){
            return Map.of("code",401,"message","Token Invalid or Expired");
        }
    }
}
