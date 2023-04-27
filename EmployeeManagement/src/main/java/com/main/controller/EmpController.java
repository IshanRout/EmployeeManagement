package com.main.controller;

import com.main.exception.EmpException;
import com.main.model.EmployeeEntity;
import com.main.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmpController {
    @Autowired
    private EmployeeService employeeService;

    //loggerfactory class called here for thr http status to show while the program is being executed
    Logger logger = LoggerFactory.getLogger(EmpController.class);


    @RequestMapping(value = "/list")
    public List<EmployeeEntity> getAllEmployees()
    {
        logger.info("HTTP request to display the list of employees ");
        return employeeService.getAll();
    }


    @RequestMapping(value = "/findById/{empId}")
    public Collection getEmployee(@PathVariable int empId) {
        logger.info("HTTP request to search the list of employee details present under the id requested");
        try {
            return (Collection) employeeService.findById(empId);
        } catch (EmpException e) {
            System.out.println("No employee details found");
            logger.error("HTTP status 404 , no employee details found.");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @RequestMapping(value = "/add", method= RequestMethod.POST)
    public void addStudent(@RequestBody EmployeeEntity employee , HttpSession session) {
        logger.info("HTTP request to add an employee detail");
        try {
            employeeService.add(employee);
            session.setAttribute("msg", "Emplyoee Added Sucessfully..");
        } catch (EmpException e) {
            System.out.println("employee Already Exists");
            logger.error("Employee details already exists");
        }
    }

    @RequestMapping(value = "/update/{empId}", method = RequestMethod.PUT)
    public void updateEmployee(@RequestBody EmployeeEntity employee,@PathVariable int empId , HttpSession session ) {
        logger.info("HTTP request to update the employee details under the id requested");
        try {
            employeeService.update(empId, employee);
            session.setAttribute("msg", "Emp Data Update Sucessfully..");;
        } catch (EmpException e) {
            System.out.println("No employee found with that Id to be updated ");
            logger.error("HTTP status 404 , no details found to update.");
        }
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable int empId,HttpSession session) {
        logger.info("HTTP request to delete the employee details present under the id requested");
        try {
            employeeService.delete(empId);
            session.setAttribute("msg","Emp Data Deleted Succesfully..");
        } catch (EmpException e) {
            System.out.println("Employee details already deleted ");
            logger.error("HTTP status 404 , employee already deleted.");
        }
    }



}


