
package com.klu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.klu.model.Assignment;
import com.klu.service.AssignmentService;

@RestController
@RequestMapping("/assignments")
public class AssignmentController{

    @Autowired
    AssignmentService service;

    @PostMapping("/create")
    public Object createAssignment(@RequestBody Assignment assignment,@RequestHeader("Token") String token){
        return service.createAssignment(assignment,token);
    }

    @GetMapping("/all")
    public Object getAllAssignments(@RequestHeader("Token") String token){
        return service.getAllAssignments(token);
    }

    @GetMapping("/{id}")
    public Object getAssignmentById(@PathVariable Integer id,@RequestHeader("Token") String token){
        return service.getAssignmentById(id,token);
    }

    @PutMapping("/update/{id}")
    public Object updateAssignment(@PathVariable Integer id,@RequestBody Assignment assignment,@RequestHeader("Token") String token){
        return service.updateAssignment(id,assignment,token);
    }

    @DeleteMapping("/delete/{id}")
    public Object deleteAssignment(@PathVariable Integer id,@RequestHeader("Token") String token){
        return service.deleteAssignment(id,token);
    }
}
