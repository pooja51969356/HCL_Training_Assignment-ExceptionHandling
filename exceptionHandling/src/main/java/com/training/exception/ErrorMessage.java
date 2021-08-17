package com.training.exception;
import java.util.Date;
import java.util.List;
public class ErrorMessage {
	  private Date statusCode;
	  private String timestamp;
	  private String message;
	  private List<String> description;

	  public ErrorMessage(Date date, String string, String message, List<String> details) {
	    this.statusCode = date;
	    this.timestamp = string;
	    this.message = message;
	    this.description = details;
	  }

	  public Date getStatusCode() {
	    return statusCode;
	  }

	  public String getTimestamp() {
	    return timestamp;
	  }

	  public String getMessage() {
	    return message;
	  }

	  public List<String> getDescription() {
	    return description;
	  }
	}
