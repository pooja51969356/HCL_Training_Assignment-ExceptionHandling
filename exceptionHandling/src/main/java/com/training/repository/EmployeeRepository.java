package com.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.training.model.Employee;

public interface EmployeeRepository  extends JpaRepository<Employee, Integer> {
	
	public List<Employee> findByNameEquals(String name);
	public List<Employee> findByEmpId(Long id);	
	public List<Employee> findByEmailAndAge(String email,Integer age);
	public List<Employee> findByAgeLessThan(Integer age);
	public List<Employee> findByAgeBetween(Integer startAge, Integer endAge);
	public List<Employee> findByNameOrderByName(String name);
	public List<Employee> findByExperienceIsNull();
	public List<Employee> findByDepartmentOrderByDepartmentDesc(String branch);
//	public List<Employee> findAllEmployee();
}
