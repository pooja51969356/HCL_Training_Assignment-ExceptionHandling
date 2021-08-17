package com.training.exception;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;




@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ErrorMessage {
	  private Date timestamp;
	  private String statusCode;
	  private String message;
	  private List<String> description;

	 
	
	}
