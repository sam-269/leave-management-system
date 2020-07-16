package com.projects.leavemanagementsystem.service;

import com.projects.leavemanagementsystem.dao.EmployeesDao;
import com.projects.leavemanagementsystem.dao.LeaveApplicationsDao;
import com.projects.leavemanagementsystem.dao.LeavesDao;
import com.projects.leavemanagementsystem.dto.ApplicationParams;
import com.projects.leavemanagementsystem.exceptions.EndDateGreaterException;
import com.projects.leavemanagementsystem.exceptions.EmployeeIdNotFoundException;
import com.projects.leavemanagementsystem.models.Employees;
import com.projects.leavemanagementsystem.models.LeaveApplications;
import com.projects.leavemanagementsystem.models.Leaves;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LeaveService {

    @Autowired
    private LeaveApplicationsDao applicationsDao;
    @Autowired
    private LeavesDao leavesDao;
    @Autowired
    private EmployeesDao employeesDao;
    @Autowired
    public JavaMailSender emailSender;
    @Value(value = "${mail.subject}")
    private String subject;
    @Value(value = "${mail.body}")
    private String body;

    public Optional<Employees> getEmployeeDataById(Integer id)
    {
        return employeesDao.findById(id);
    }

    public List<Date> getAvailedLeavesById(Integer id){

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

    public List<Date> checkAvailedLeaves(Integer id, Date startDate, Date endDate) throws NullPointerException {
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
        List<Date> all_leaves=getAvailedLeavesById(id);
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
    public Optional<Employees> getManager(Integer id){

        Optional<Employees> emp = employeesDao.findById(id);
        Integer mid = emp.get().getMid();
        Optional<Employees> manager = employeesDao.findById(mid);
        return manager;
    }


    public void sendMailToManger(Integer id, ApplicationParams applicationParams) {
        Optional<Employees> emp = employeesDao.findById(id);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(this.getManager(id).get().getEmailid());
        message.setCc(emp.get().getEmailid());
        String name = emp.get().getEmpname();
        message.setSubject(subject+ " " + name);
        Date sd = applicationParams.getDuration().getStartDate();
        Date ed = applicationParams.getDuration().getEndDate();
        String body2 = " " + name + " has applied leave(s) for " + sd +" to "+ed+ " for the reason " + applicationParams.getReason();
        String body3 = " To take action please select the employee id i.e. " + emp.get().getEmpId() + " and your action (approve or reject)";
        message.setText(body + body2 + body3);
        emailSender.send(message);
    }

    public Integer applyLeaves(Integer id, ApplicationParams applicationParams) {
        //if earned leaves is greater or equals
        addToAppliedLeaves(id,applicationParams);
        sendMailToManger(id,applicationParams);

        //see how to handle proper body template
        return 1;
    }

    public void addToAppliedLeaves(Integer id, ApplicationParams applicationParams){
        LeaveApplications application = LeaveApplications.builder()
                .empid(id)
                .startDate(applicationParams.getDuration().getStartDate())
                .endDate(applicationParams.getDuration().getEndDate())
                .reason(applicationParams.getReason())
                .type(applicationParams.getType())
                .approverId(getEmployeeDataById(id).get().getMid())
                .empname(getEmployeeDataById(id).get().getEmpname())
                .approver(getEmployeeDataById(getEmployeeDataById(id).get().getMid()).get().getEmpname())
                .build();
        applicationsDao.save(application);
    }
    public List<LeaveApplications> checkAppliedLeaves(Integer mid) {
        return  applicationsDao.findByApproverId(mid);
    }

    public List<Leaves> getAll() {
        return (leavesDao.findAll());
    }

    public List<LeaveApplications> getAppliedLeavesById(Integer id){
        return applicationsDao.findByEmpid(id);
    }


    public String actionForAppliedLeaves(Integer leaveid ,String action) {
        Optional<LeaveApplications> application = applicationsDao.findById(leaveid);
        Integer empId = application.get().getEmpid();
        if  (applicationsDao.existsByEmpid(empId)){
            if (action.equals("approve")) {

                //add an entry in leavesdao
                //update status in appdao
                //update availed leaves in employee
                //send mail of approval


/*                List<Date> leaves = getDaysBetweenDates(application.get().getStartDate(), application.get().getEndDate());
                for (Date d: leaves) {
                    Leaves leave = Leaves.builder()
                            .empId(empId)
                            .leaves(d)
                            .build();
                    leavesDao.save(leave);

                }
               //update status
                applicationsDao.deleteById(leaveid);
*/
                return "Leave approved";
            }
            else if (action.equals("reject")){

                //send mail of reject
                return "Leave rejected";
            }
            else{
                return "Please enter \"approve\" or \"reject\" as action.";
            }
        }
        else{
            if(employeesDao.existsById(empId)){
                String name = getEmployeeDataById(empId).get().getEmpname();
                return name + " does not have any pending leave(s) for action";
            }
            else{
                return empId + " is not a valid ID";
            }

        }
    }
    public static List<Date> getDaysBetweenDates(Date startdate,Date enddate) {

        List<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);

        while (calendar.getTime().before(enddate)) {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }
}
