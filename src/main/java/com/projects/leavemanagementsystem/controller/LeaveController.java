package com.projects.leavemanagementsystem.controller;

import com.projects.leavemanagementsystem.dao.EmployeesDao;
import com.projects.leavemanagementsystem.dao.LeavesDao;
import com.projects.leavemanagementsystem.dto.Action;
import com.projects.leavemanagementsystem.dto.ApplicationParams;
import com.projects.leavemanagementsystem.dto.DateParams;
import com.projects.leavemanagementsystem.models.Employees;
import com.projects.leavemanagementsystem.models.LeaveApplications;
import com.projects.leavemanagementsystem.models.Leaves;
import com.projects.leavemanagementsystem.service.LeaveService;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class LeaveController {

    @Autowired
    private EmployeesDao ed;
    @Autowired
    private LeavesDao ld;

    @Autowired
    private LeaveService leaveService;
    @Autowired
    public JavaMailSender emailSender;
    @Value("${name}")
    private String name;

    @GetMapping()
    public String hello(){
        return name;
    }

    @GetMapping("employees/{id}")
    @ApiOperation(value = "Gets employee data by id", notes = "Provide an id to look up employee details from the database")
    public Optional<Employees> getEmployeeDataById(@PathVariable("id") Integer id) {
        return leaveService.getEmployeeDataById(id);
    }

    @PostMapping("leaves/{id}")
    @ApiOperation(value = "Gets all leaves availed by employee within a particular duration",
            notes = "Provide the start and end date within which availed leaves need to be checked and the corresponding id")
    public List<Date> checkAvailedLeaves(@PathVariable("id") Integer id,
                                          @RequestBody DateParams date) {
        return leaveService.checkAvailedLeaves(id, date.getStartDate(), date.getEndDate());
    }

    @PostMapping("leave_application/{id}")
    @ApiOperation(value = "Apply leaves ",
            notes = "Provide id,start date, end date, reason for leave and type of leave. A mail will be sent to the employee's manager for leave application")
    public String applyLeaves(@PathVariable("id") Integer id,
                              @RequestBody ApplicationParams applicationParams) {
        return (leaveService.applyLeaves(id, applicationParams));

    }

    @GetMapping("my_leaves_applied/{eid}")
    @ApiOperation(value = "Check leaves applied by employee", notes = " Provide id to see all the leaves applied by the employee")
    public List<LeaveApplications> checkMyAppliedLeaves(@PathVariable Integer eid) {
        return leaveService.getAppliedLeavesById(eid);
    }

    @GetMapping("applied_leaves_to_me/{mid}")
    @ApiOperation(value = "Check leaves applied to the employee", notes = "Provide id to see all the leaves pending for your action")
    public List<LeaveApplications> checkLeavesAppliedToMe(@PathVariable Integer mid) {
        return leaveService.checkAppliedLeaves(mid);
    }

    @PostMapping("action/{lid}")
    @ApiOperation(value = "Take action for pending leaves",
            notes = "Provide the employee id whose leave request needs action and the action i.e. \"approve\" or \"reject\"")
    public String actionForAppliedLeave(@PathVariable Integer lid,@RequestBody Action action) {
        return leaveService.actionForAppliedLeaves(lid, action.getAction());
    }

    @PostMapping("hello")
    public void helloq(){
        Employees e = new Employees();
        e.setEarnedLeaves(2);
        e.setEmailid("");
        e.setEmpId(30);
        e.setEmpname("");
        e.setLeaves(null);
        e.setMaternityleaves(null);
        e.setMid(34);

    }


}