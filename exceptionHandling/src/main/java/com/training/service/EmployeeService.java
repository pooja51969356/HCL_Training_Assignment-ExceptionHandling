package com.training.service;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import com.training.dto.EmployeeResponseDto;
import com.training.exception.EmptyInputException;
import com.training.exception.NoElementFoundException;
import com.training.model.Employee;
import com.training.repository.EmployeeRepository;
@Service
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public EmployeeResponseDto save(Employee employee) {
		Employee employeeDetails =employeeRepository.save(employee);
	
		
		 if(employeeDetails!=null)	
			 return  prepareEmployeeDetailForResponse(employeeDetails);
			 else
				 throw new EmptyInputException("606","Please check employee entry ");

	}

	
	public List<EmployeeResponseDto> findByNameOrderByName(String employeeName) {
		// TODO Auto-generated method stub
		 List< Employee> emp= employeeRepository.findByNameOrderByName(employeeName);
		 if(emp!=null && !emp.isEmpty())	
		 return  prepareEmployeeDetailForResponseList(emp);
		 else
			throw new IllegalArgumentException("Employee Record Not  Found For match : "+ employeeName);

	}

	/*
	 * public java.util.List<Employee> findAllEmployee() { // TODO Auto-generated
	 * method stub return employeeRepository.findAllEmployee(); }
	 */

	public List<EmployeeResponseDto> findByNameEquals(String name) {
		// TODO Auto-generated method stub
		 List< Employee> emp= employeeRepository.findByNameEquals(name);
		 if(emp.isEmpty()) {
				throw new NoSuchElementException("No Record Found");
			}
		 return  prepareEmployeeDetailForResponseList(emp);
	}

	public List<EmployeeResponseDto> findByAgeBetween(Integer startAge, Integer endAge) {
		// TODO Auto-generated method stub
		 List< Employee> emp= employeeRepository.findByAgeBetween(startAge, endAge);
		 if(emp.isEmpty()) {
				throw new NoElementFoundException("602", "Empty list, no element present ");
			}
		 return  prepareEmployeeDetailForResponseList(emp);
	}

	public List<EmployeeResponseDto> findByAgeLessThan(Integer age) {
		// TODO Auto-generated method stub
		 List< Employee> emp= employeeRepository.findByAgeLessThan(age);
		 if(emp!=null && !emp.isEmpty())	
			 return  prepareEmployeeDetailForResponseList(emp);
			 else
				throw new IllegalArgumentException("Employee Record Not  Found For match : "+ age);
	
	}

	

	public List<EmployeeResponseDto> findByEmailAndAge(String email, Integer age) {
		// TODO Auto-generated method stub
		 List< Employee> emp= employeeRepository.findByEmailAndAge(email, age);		 
		 if(emp!=null && !emp.isEmpty())	
			 return  prepareEmployeeDetailForResponseList(emp);
			 else
				throw new IllegalArgumentException("Employee Record Not  Found For match : "+"email"+email+"and Age"+ age);
	}


	public List<EmployeeResponseDto> findByExperienceIsNull() {
		// TODO Auto-generated method stub
		  List< Employee> emp=  employeeRepository.findByExperienceIsNull();
			 if(emp!=null && !emp.isEmpty())	
				 return  prepareEmployeeDetailForResponseList(emp);
				 else
					throw new NoElementFoundException("Employee Record Not  Found For Experience!!!!!! "," ");

	}
	public List<EmployeeResponseDto> findByDepartmentOrderByDepartmentDesc(String department) {
		// TODO Auto-generated method stub
		  List< Employee> emp=  employeeRepository.findByDepartmentOrderByDepartmentDesc(department);
			 if(emp!=null && !emp.isEmpty())	
				 return  prepareEmployeeDetailForResponseList(emp);
				 else
					throw new NoSuchElementException("Employee Record Not  Found For match department:"+department);

	}


	
	  public List<EmployeeResponseDto> findByEmpId(Long id) { // TODO Auto-generated method
		  List< Employee> emp= employeeRepository.findByEmpId(id);
			 if(emp!=null && !emp.isEmpty())	
				 return  prepareEmployeeDetailForResponseList(emp);
				 else
					throw new NoSuchElementException("Employee Record Not  Found For match : "+"id"+id);

	   }
	  
	  
		private EmployeeResponseDto prepareEmployeeDetailForResponse(Employee employeeResponse) {
			
			EmployeeResponseDto employeeResponseDto=new EmployeeResponseDto
					(employeeResponse.getEmpId(), employeeResponse.getName(),
							employeeResponse.getAge(), employeeResponse.getEmail(), 
							employeeResponse.getDepartment(),employeeResponse.getExperience(),
							employeeResponse.getDesignation());
			
			
			return employeeResponseDto;
		}
		private List<EmployeeResponseDto> prepareEmployeeDetailForResponseList(List<Employee> employeeRes) {
			
			List<EmployeeResponseDto> responseList=new ArrayList<>();
			
			employeeRes.forEach( employeeResponse -> responseList.add( 
					new EmployeeResponseDto(employeeResponse.getEmpId(), employeeResponse.getName(),
							employeeResponse.getAge(), employeeResponse.getEmail(), 
							employeeResponse.getDepartment(),employeeResponse.getExperience(),
							employeeResponse.getDesignation()) ) );
			
			
			return responseList;
		}	

}
