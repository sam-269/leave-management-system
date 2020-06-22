package com.projects.leavemanagementsystem.service;

import com.projects.leavemanagementsystem.dao.EmployeesDao;
import com.projects.leavemanagementsystem.dao.LeavesDao;
import com.projects.leavemanagementsystem.exceptions.EndDateGreaterException;
import com.projects.leavemanagementsystem.exceptions.EmployeeIdNotFoundException;
import com.projects.leavemanagementsystem.models.Employees;
import com.projects.leavemanagementsystem.models.Leaves;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveService {

    @Autowired
    private LeavesDao leavesDao;
    @Autowired
    private EmployeesDao employeesDao;

    public List<Date> getLeavesById(Integer id){

        if (!leavesDao.existsById(id)){
            throw new EmployeeIdNotFoundException();
        }

        List<Leaves> leavesList = leavesDao.findByEmpId(id);
        List<Date> all_leaves=new ArrayList<Date>();
        for (int i = 0; i < leavesList.size(); i++){
            all_leaves.add(leavesList.get(i).getLeaves());
        }

        return all_leaves;
    }

    public List<Date> checkLeaves(Integer id, Date startDate, Date endDate) throws NullPointerException {
        //handle null pointer ex
       /* if(startDate== null || endDate== null){
            throw new ApiRequestException("Dates cannot be null");
        }*/
       try
       {
           if(startDate== null || endDate== null){
                throw new NullPointerException();
           }

           if (startDate.after(endDate)){
               throw new EndDateGreaterException();
           }
       }
       catch(NullPointerException e)
       {
            System.out.println("Date cannot be null.");
       }
        List<Date> all_leaves=getLeavesById(id);
        List<Date> list = new ArrayList<Date>();
        for(int i=0; i<all_leaves.size(); i++){
            if(startDate.before(all_leaves.get(i))){
                if(endDate.after(all_leaves.get(i))){
                    list.add(all_leaves.get(i));
                }
            }
        }
        return list;
    }
    public String getManagerEmailId(Integer id){

        Optional<Employees> emp = employeesDao.findById(id);
        Integer mid = emp.get().getMid();
        Optional<Employees> manager = employeesDao.findById(mid);
        return manager.get().getEmailid();
    }

    public Integer applyLeaves(Integer id,Date fromDate, Date toDate) {
        return 1;
    }
    public void check_applied_leaves(){

    }

    public List<Leaves> getAll() {
        return (leavesDao.findAll());
    }
}
