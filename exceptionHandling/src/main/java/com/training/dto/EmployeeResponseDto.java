package com.training.dto;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class EmployeeResponseDto {

	private Long empId;
	private String name;
	private Integer age;
	private String email;
	private String department;
	private String experience;
	private String designation;
}

