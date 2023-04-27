package com.main.service;

import com.main.Repository.EmployeeRepo;
import com.main.exception.EmpException;
import com.main.model.EmployeeEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService<de> {

    public EmployeeRepo employeeRepo;

    public EmployeeService(EmployeeRepo employeeRepo) {
        this.employeeRepo = employeeRepo;
    }
    public List<EmployeeEntity> getAll() {

        return employeeRepo.findAll();
    }
    public EmployeeEntity findById(Integer empId) throws EmpException {
        Optional<EmployeeEntity> employee =employeeRepo.findById(empId);
        if(employee.isPresent())
            return employee.get();
        throw new EmpException("Employee details not found");
    }

    public boolean add(EmployeeEntity employeeEntity) throws EmpException {
        employeeRepo.save(employeeEntity);
        return true;
        }

    public boolean update(int empId , EmployeeEntity employee) throws EmpException{
        employeeRepo.save(employee);
        return true;
    }

    public boolean delete(Integer empId) throws EmpException{
        employeeRepo.deleteAllById(Collections.singleton(empId));
        return true;
    }
}

