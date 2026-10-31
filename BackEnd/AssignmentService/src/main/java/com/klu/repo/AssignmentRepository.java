
package com.klu.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klu.model.Assignment;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment,Integer>{

    List<Assignment> findByAssignedto(Integer assignedto);

    List<Assignment> findByComplaintid(Integer complaintid);

}
