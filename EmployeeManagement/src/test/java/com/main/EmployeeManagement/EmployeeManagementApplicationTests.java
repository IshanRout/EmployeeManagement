package com.main.EmployeeManagement;

import com.main.Repository.EmployeeRepo;
import com.main.model.EmployeeEntity;
import com.main.model.EmployeeEntity;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
//Junit manual testing methods declared

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmpManagementApplicationTests {

	EmployeeRepo employeeRepo;

	@Test
	@Order(1)
	public void listTest(){
		List list = employeeRepo.findAll();
	}

	@Test
	@Order(2)
	public void addTest(){
		EmployeeEntity entity = new EmployeeEntity();
		entity.setEmpId(1);
		entity.setEmpName("Ishan Rout");
		entity.setJoiningDate("2023-04-02");
		entity.setEmail("ishan01@gmail.com");
		entity.setPhone("xxxxxxxxxx");
		entity.setSalary(15000);
		employeeRepo.save(entity);
	}
	@Test
	@Order(3)
	public void findByIdTest(){
		Optional<EmployeeEntity> employee = employeeRepo.findById(1);
	}

	@Test
	@Order(4)
	public void UpdateTest(){
		EmployeeEntity entity = new EmployeeEntity();
		entity = (EmployeeEntity) employeeRepo.empId(1);
		entity.setSalary(20000);
		employeeRepo.save(entity);
	}

	@Test
	@Order(5)
	public void deleteFromPerson() {
		Optional<EmployeeEntity> employee = employeeRepo.deleteAllById(1);

	}

}
