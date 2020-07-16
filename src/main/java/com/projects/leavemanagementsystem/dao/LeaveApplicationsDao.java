package com.projects.leavemanagementsystem.dao;

import com.projects.leavemanagementsystem.models.LeaveApplications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import java.util.List;

@Repository
public interface LeaveApplicationsDao extends JpaRepository<LeaveApplications,Integer> {
   List<LeaveApplications> findByApproverId(Integer id);
   List<LeaveApplications> findByEmpid(Integer id);
   Boolean existsByEmpid(Integer id);
}
