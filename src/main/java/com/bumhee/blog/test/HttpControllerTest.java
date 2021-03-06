package com.bumhee.blog.test;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HttpControllerTest {

	@GetMapping("/http/lombok")
	public String lombockTest()
	{
		Member m = Member.builder().username("ssar").password("1234").email("zz").build();
		System.out.println("getter:" + m.getUsername());
		
		m.setUsername("Zz");
		
		
		System.out.println("gettet:" + m.getUsername());
		
		return "롬복 톄스트 완료";
	}
	
	@GetMapping("/http/get")
	public String getTest(Member m)
	{
		return "get 요청:" + m.getId() + m.getUsername();
	}
	@PostMapping("/http/post")
	public String postTest(@RequestBody Member m)
	{
		return "post 요청:" + m.getId() + m.getUsername();
	}
	@PutMapping("/http/put")
	public String putTest()
	{
		return "put 요청";
	}
	@DeleteMapping("/http/delete")
	public String deleteTest()
	{
		return "delete 요청";
	}
}
