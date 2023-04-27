package com.main.Repository;

import com.main.model.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepo extends JpaRepository<EmployeeEntity , Integer> {

    List<EmployeeEntity> empId(Integer empId);

    Optional<EmployeeEntity> deleteAllById(int empId);


}
