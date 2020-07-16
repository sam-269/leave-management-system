package com.projects.leavemanagementsystem.models;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "leave_applications")
public class LeaveApplications implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "leave_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer leaveId;

    @Column(name = "employee_id")
    private Integer empid;

    @Column(name = "empname")
    private String empname;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "leave_type")
    private String type;

    @Column(name = "reason")
    private String reason;

    @Column(name = "approver_id")
    private Integer approverId;

    @Column(name = "approver")
    private String approver;
}
