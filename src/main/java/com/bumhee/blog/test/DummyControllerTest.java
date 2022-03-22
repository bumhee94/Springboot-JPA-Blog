package com.bumhee.blog.test;

import java.util.List;
import java.util.function.Supplier;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bumhee.blog.model.RoleType;
import com.bumhee.blog.model.User;
import com.bumhee.blog.repository.UserRepository;

@RestController
public class DummyControllerTest {

	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("dummy/user/{id}")
	public User detail(@PathVariable int id)
	{
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {

			@Override
			public IllegalArgumentException get() {
				// TODO Auto-generated method stub
				return new IllegalArgumentException("해당유저는 없습니다"+id);
			}
			
		});
		return user;
	}
	
	@GetMapping("/dummy/user")
	public List<User> list()
	{
		return userRepository.findAll();
	}
	
	@GetMapping("/dummy/user/page")
	public List<User> pagingList(@PageableDefault(size=2, sort="id", direction=Sort.Direction.DESC) Pageable pageable)
	{
		List<User> users = userRepository.findAll(pageable).getContent();
		return users;
	}
	
	@Transactional
	@PutMapping("/dummy/user/{id}")
	public User updateUser(@PathVariable int id, @RequestBody User reqUser)
	{
		System.out.println("id : " + id);
		System.out.println("password : " + reqUser.getPassword());
		System.out.println("email  : " + reqUser.getEmail());
		
		User user = userRepository.findById(id).orElseThrow(()->{
			return new IllegalArgumentException("수정실패");
		});
		
		user.setPassword(reqUser.getPassword());
		user.setEmail(reqUser.getEmail());
	
		//userRepository.save(user);
		return null;
	}
	
	@DeleteMapping("/dummy/user/{id}")
	public String delete(@PathVariable int id)
	{
		try {
			userRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			return "삭제 실패 해당 id 없음";
		}
		
		return "삭제됨";
	}
	
	@PostMapping("/dummy/join")
	public String join(User user)
	{
		System.out.println("id" + user.getId());
		System.out.println("userName" + user.getUsername());
		System.out.println("passWord" + user.getPassword());
		System.out.println("email" + user.getEmail());
		System.out.println("role" + user.getRole());
		System.out.println("createdate" + user.getCreateDate());
		
		user.setRole(RoleType.USER);
		userRepository.save(user);
		return "회원가입 완료";
	}
}

