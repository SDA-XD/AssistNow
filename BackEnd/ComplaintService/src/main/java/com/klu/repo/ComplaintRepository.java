
package com.klu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.klu.model.Complaint;

import java.util.List;
@Repository
public interface ComplaintRepository extends JpaRepository<Complaint,Integer>{
	

List<Complaint> findByCreatedby(Integer createdby);

List<Complaint> findByAssignedto(Integer assignedto);


}
