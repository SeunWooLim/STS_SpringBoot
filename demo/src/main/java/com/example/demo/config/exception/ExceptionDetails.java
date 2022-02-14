package com.example.demo.config.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionDetails {
	
	private Date timeStamp;
	private String message;
	private String details;
}
