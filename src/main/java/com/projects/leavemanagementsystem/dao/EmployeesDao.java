package com.projects.leavemanagementsystem.dao;

import com.projects.leavemanagementsystem.models.Employees;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesDao extends JpaRepository<Employees,Integer> {

}
