package com.training.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name="employee_details")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empId;
	@Column
	@NotEmpty(message = "Employee name should not empty")
	private String name;
	
	@Min(value = 18, message = "Age should not be less than 18")
	private Integer age;
	private String email;
	
	
	
	@Column
	@NotEmpty(message = "Employee department should not empty")
	private String department;
	@Column
	@Size(min = 0, max = 2)
	private String experience;
	@Column
	@NotEmpty(message = "Employee experience should not empty")
	private String designation;
}

