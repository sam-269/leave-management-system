package com.projects.leavemanagementsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@AllArgsConstructor
@Entity
@Table(name="leaves")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Leaves implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Integer leaveid;
    @JsonIgnore
    @Column(name="empid",insertable = false,updatable = false)
    private Integer empId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="empid",nullable = false)
    @JsonIgnore
    private Employees employees;

    public Leaves(){};
//    public Leaves (Date leaves){
//        this.leaves=leaves;
//    }
    private Date leaves;

    public Integer getLeaveid(){
        return leaveid;
    }
    public void setLeaveId(){
        this.leaveid = leaveid;

    }
    public Integer getEmpId() {
        return empId;
    }

    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    public Date getLeaves() {
        return leaves;
    }

    public void setLeaves(Date leaves) {
        this.leaves = leaves;
    }
}
