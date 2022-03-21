package com.bumhee.blog.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bumhee.blog.model.RoleType;
import com.bumhee.blog.model.User;
import com.bumhee.blog.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Transactional
	public void 회원가입(User user)
	{
		String rawPassword = user.getPassWord();
		String encPassword = encoder.encode(rawPassword);
		user.setPassWord(encPassword);
		user.setRole(RoleType.USER);
		userRepository.save(user);
	}
}
