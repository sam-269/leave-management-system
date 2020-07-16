package com.projects.leavemanagementsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Entity
@Table(name="leaves")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Leaves implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer leaveid;
    @JsonIgnore
    @Column(name="empid",insertable = false,updatable = false)
    private Integer empId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="empid",nullable = false)
    @JsonIgnore
    private Employees employees;


    private Date leaves;

    public Integer getEmpId() {
        return empId;
    }

    public Date getLeaves() {
        return leaves;
    }
}
