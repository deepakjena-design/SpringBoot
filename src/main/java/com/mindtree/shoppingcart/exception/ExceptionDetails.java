package com.mindtree.shoppingcart.exception;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExceptionDetails {
	
	private Date date;
	private String message;
	private String details;
	
	public ExceptionDetails(Date date, String message, String details) {
		super();
		this.date = date;
		this.message = message;
		this.details = details;
	}
	public ExceptionDetails(Date date, String details) {
		super();
		this.date = date;
		this.details = details;
	}
	public String getDate() {
		return new SimpleDateFormat("yyyy-MM-dd").format(date);
	}
	
	public String getMessage() {
		return message;
	}
	
	public String getDetails() {
		return details;
	}
	

}
