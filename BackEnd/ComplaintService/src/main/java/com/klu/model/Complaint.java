package com.klu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String title;
    private String description;
    private Integer priority;
    private Integer status;
    private Integer createdby;
    private Integer assignedto;
    private String deadline;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public String getTitle(){
        return title;
    }

    public void setTitle(String title){
        this.title=title;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description=description;
    }

    public Integer getPriority(){
        return priority;
    }

    public void setPriority(Integer priority){
        this.priority=priority;
    }

    public Integer getStatus(){
        return status;
    }

    public void setStatus(Integer status){
        this.status=status;
    }

    public Integer getCreatedby(){
        return createdby;
    }

    public void setCreatedby(Integer createdby){
        this.createdby=createdby;
    }

    public Integer getAssignedto(){
        return assignedto;
    }

    public void setAssignedto(Integer assignedto){
        this.assignedto=assignedto;
    }

    public String getDeadline(){
        return deadline;
    }

    public void setDeadline(String deadline){
        this.deadline=deadline;
    }
}
