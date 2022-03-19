package com.bumhee.blog.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TempControllerTest {
	
	@GetMapping("/temp/home")
	public String tempHome()
	{
		return "/home.html";
	}
	
	@GetMapping("/temp/img")
	public String tempImg()
	{
		return "/미소2.png";
	}
	
	@GetMapping("/temp/jsp")
	public String tempjsp()
	{
		return "test";
	}
}
