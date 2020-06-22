package com.projects.leavemanagementsystem.dao;

import com.projects.leavemanagementsystem.models.Leaves;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.hibernate.FetchMode.JOIN;
import static org.hibernate.hql.internal.antlr.HqlSqlTokenTypes.FROM;
import static org.hibernate.loader.Loader.SELECT;

@Repository
public interface LeavesDao extends JpaRepository<Leaves,Integer> {
    List<Leaves> findByEmpId(Integer id);
}