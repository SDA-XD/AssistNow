
package com.klu.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="assignments")
public class Assignment{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private Integer complaintid;
    private Integer assignedto;
    private Integer assignedby;
    private String assigneddate;
    private String status;
    private String remarks;

    public Integer getId(){
        return id;
    }

    public void setId(Integer id){
        this.id=id;
    }

    public Integer getComplaintid(){
        return complaintid;
    }

    public void setComplaintid(Integer complaintid){
        this.complaintid=complaintid;
    }

    public Integer getAssignedto(){
        return assignedto;
    }

    public void setAssignedto(Integer assignedto){
        this.assignedto=assignedto;
    }

    public Integer getAssignedby(){
        return assignedby;
    }

    public void setAssignedby(Integer assignedby){
        this.assignedby=assignedby;
    }

    public String getAssigneddate(){
        return assigneddate;
    }

    public void setAssigneddate(String assigneddate){
        this.assigneddate=assigneddate;
    }

    public String getStatus(){
        return status;
    }

    public void setStatus(String status){
        this.status=status;
    }

    public String getRemarks(){
        return remarks;
    }

    public void setRemarks(String remarks){
        this.remarks=remarks;
    }
}
