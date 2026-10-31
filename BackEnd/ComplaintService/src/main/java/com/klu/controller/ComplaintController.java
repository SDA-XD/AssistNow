
package com.klu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.model.Complaint;
import com.klu.service.ComplaintService;

@RestController
@RequestMapping("/complaints")
public class ComplaintController{

    @Autowired
    ComplaintService service;

    @PostMapping("/create")
    public Object createComplaint(@RequestBody Complaint complaint,@RequestHeader("Token") String token){
        return service.createComplaint(complaint,token);
    }

    @GetMapping("/all")
    public Object getAllComplaints(@RequestHeader("Token") String token){
        return service.getAllComplaints(token);
    }

    @GetMapping("/{id}")
    public Object getComplaintById(@PathVariable Integer id,@RequestHeader("Token") String token){
        return service.getComplaintById(id,token);
    }

    @PutMapping("/update/{id}")
    public Object updateComplaint(@PathVariable Integer id,@RequestBody Complaint complaint,@RequestHeader("Token") String token){
        return service.updateComplaint(id,complaint,token);
    }

    @DeleteMapping("/delete/{id}")
    public Object deleteComplaint(@PathVariable Integer id,@RequestHeader("Token") String token){
        return service.deleteComplaint(id,token);
    }
}
