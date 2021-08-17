package com.training.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.training.dto.EmployeeResponseDto;
import com.training.model.Employee;
import com.training.service.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/add")
	public ResponseEntity<EmployeeResponseDto> addStudent(@Valid @RequestBody Employee student) throws MethodArgumentNotValidException{

	 return ResponseEntity.ok(employeeService.save(student));
	}
	
	/*
	 * @GetMapping("/allemployees") public List<Employee> findAllEmployee(){ return
	 * employeeService.findAllEmployee(); }
	 */	
	
	
	
	@GetMapping("/employeeName/{employeeName}")
	public List<EmployeeResponseDto> findByNameEquals(@PathVariable String name){
		return employeeService.findByNameEquals(name);
	}
	
	@GetMapping("/employeeId/{employeeId}")
	public List<EmployeeResponseDto> findByEmpId(@PathVariable  @Valid Long id){
		return employeeService.findByEmpId(id);
	}
		
	@GetMapping("/email/{email}/age/{age}")
	public List<EmployeeResponseDto> findByEmailAndAge(@PathVariable  @Valid String email,@PathVariable  @Valid Integer age){
		return employeeService.findByEmailAndAge(email,age);
		
	}
	
	@GetMapping("/age/{age}")
	public List<EmployeeResponseDto> findByAgeLessThan(@PathVariable  @Valid Integer age){
		return employeeService.findByAgeLessThan(age);
		
	}
	
	@GetMapping("/stage/{startAge}/endage/{endAge}")
	public List<EmployeeResponseDto> findByAgeBetween(@PathVariable  @Valid Integer startAge, @PathVariable  @Valid Integer endAge){
		return employeeService.findByAgeBetween(startAge, endAge);
	}
	
	@GetMapping("/orderByEmployeeName/{employeeName}")
	public List<EmployeeResponseDto> findByNameOrderByName(@PathVariable  @Valid String name){
		return employeeService.findByNameOrderByName(name);
	}
	@GetMapping("/department")
	public List<EmployeeResponseDto> findByExperienceIsNull(){
		return employeeService.findByExperienceIsNull();
	}
	@GetMapping("/orderbydepartment/{department}")
	public List<EmployeeResponseDto> findByDepartmentOrderByDepartmentDesc(@PathVariable  @Valid String branch){
		return employeeService.findByDepartmentOrderByDepartmentDesc(branch);
	}
}

