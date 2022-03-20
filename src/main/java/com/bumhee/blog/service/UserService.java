package com.bumhee.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bumhee.blog.model.User;
import com.bumhee.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void 회원가입(User user)
	{
		userRepository.save(user);
	}
	
	@Transactional(readOnly = true)
	public User 로그인(User user)
	{
		return userRepository.findByUserNameAndPassWord(user.getUserName(), user.getPassWord());
	}
}
