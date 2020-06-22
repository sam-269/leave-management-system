package com.projects.leavemanagementsystem.controller;

import com.projects.leavemanagementsystem.dao.EmployeesDao;
import com.projects.leavemanagementsystem.dto.DateParams;
import com.projects.leavemanagementsystem.models.Employees;
import com.projects.leavemanagementsystem.models.Leaves;
import com.projects.leavemanagementsystem.service.LeaveService;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1")
public class LeaveController {
    @Autowired
    private LeaveService leaveService;

//    @HystrixCommand(fallbackMethod = "runLeaveFallback", commandProperties = {
//    @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value =
//                    "100"),
//    @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value =
//                    "20"),
//
//    @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value =
//                    "1000"),
//    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
//                    value = "10000") })

    @GetMapping("allinfo")
    public List<Leaves> getAll(){
        return (leaveService.getAll());
    }

//    @GetMapping("employees/{id}")
//    public String getEmployeesById(@PathVariable("id") Integer id){
//            return leaveService.getManagerEmailId(id);
//    }

    @GetMapping("leaves/{id}")
    public List<Date> getLeavesById(@PathVariable("id") Integer id){
        return leaveService.getLeavesById(id);
    }

    @PostMapping("leaves/{id}")
    public List<Date> checkLeaves(@PathVariable("id") Integer id,
                                  @NotNull @RequestBody DateParams date) {
        return leaveService.checkLeaves(id,date.getStartdate(),date.getEnddate());
    }

    @PostMapping("leave_application/{id}")
    public Integer applyLeaves( @PathVariable("id")Integer id,
                                @RequestBody DateParams date){
        return leaveService.applyLeaves(id,date.getFromDate(),date.getToDate());
    }

    public String runLeaveFallback(){
        return "Some Error occurred on the server, try again";
    }

    }