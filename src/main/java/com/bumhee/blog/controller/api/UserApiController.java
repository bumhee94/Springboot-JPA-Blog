package com.bumhee.blog.controller.api;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bumhee.blog.dto.ResponseDto;
import com.bumhee.blog.model.RoleType;
import com.bumhee.blog.model.User;
import com.bumhee.blog.service.UserService;

@RestController
public class UserApiController {

	@Autowired
	private UserService userService;
	
//	@Autowired
//	private HttpSession session;
	
	@PostMapping("/api/user")
	public ResponseDto<Integer> save(@RequestBody User user)
	{
		user.setRole(RoleType.USER);
		userService.회원가입(user);
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
	
//	@PostMapping("/api/user/login")
//	public ResponseDto<Integer> login(@RequestBody User user)
//	{
//		System.out.println("로그인 호출됨");
//		User principal = userService.로그인(user);
//		
//		if(principal != null)
//		{
//			session.setAttribute("principal", principal);
//		}
//		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
//	}
}
