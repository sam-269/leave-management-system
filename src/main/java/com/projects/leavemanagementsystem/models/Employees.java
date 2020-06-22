package com.projects.leavemanagementsystem.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="employees")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employees implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="empid", nullable=false)
    private Integer empId;
    @Column(name="empname", nullable=false)
    private String empname;
    @Column(name="mid", nullable=false)
    private Integer mid;
    @Column(name="earned_leaves", nullable=false)
    private Integer earnedLeaves;
    @Column(name="maternity_leaves", nullable=true)
    private Integer maternityleaves;
    @Column(name = "emailid", nullable = false)
    private String emailid;

    @OneToMany(mappedBy = "employees", cascade = CascadeType.ALL, fetch = FetchType.EAGER,targetEntity = Leaves.class)
    private Set<Leaves> leaves;

    public Integer getMid() {
        return mid;
    }
    public String getEmailid(){
        return emailid;
    }
    //    public Set<Leaves> getLeaves() {
//        return leaves;
//    }
//
//    public void setLeaves(Set<Leaves> leaves) {
//        this.leaves = leaves;
//    }

}
