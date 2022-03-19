package com.bumhee.blog.handler;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

	@ExceptionHandler(value=Exception.class) 
	public String handleArumentException(Exception e)
	{
		return "<h1>"+e.getMessage()+"</h1>";
	}
}
