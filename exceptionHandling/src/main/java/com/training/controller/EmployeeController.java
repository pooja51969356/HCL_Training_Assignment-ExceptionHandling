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
	
	@PostMapping("/employeeadd")
	public ResponseEntity<EmployeeResponseDto> addStudent(@Valid @RequestBody Employee employee ) throws MethodArgumentNotValidException{

	 return ResponseEntity.ok(employeeService.save(employee));
	}
	
	/*
	 * @GetMapping("/allemployees") public List<Employee> findAllEmployee(){ return
	 * employeeService.findAllEmployee(); }
	 */	
	
	
	
	@GetMapping("/employee-name/{employee-name}")
	public List<EmployeeResponseDto> findByNameEquals(@PathVariable String name){
		return employeeService.findByNameEquals(name);
	}
	
	@GetMapping("/employee-id/{employee-id}")
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
	
	@GetMapping("/stage/{startage}/endage/{endage}")
	public List<EmployeeResponseDto> findByAgeBetween(@PathVariable  @Valid Integer startage, @PathVariable  @Valid Integer endage){
		return employeeService.findByAgeBetween(startage, endage);
	}
	
	@GetMapping("/orderbyemployeename/{employeename}")
	public List<EmployeeResponseDto> findByNameOrderByName(@PathVariable  @Valid String name){
		return employeeService.findByNameOrderByName(name);
	}
	@GetMapping("/department")
	public List<EmployeeResponseDto> findByExperienceIsNull(){
		return employeeService.findByExperienceIsNull();
	}
	@GetMapping("/orderbydepartment/{department}")
	public List<EmployeeResponseDto> findByDepartmentOrderByDepartmentDesc(@PathVariable  @Valid String department){
		return employeeService.findByDepartmentOrderByDepartmentDesc(department);
	}
}

