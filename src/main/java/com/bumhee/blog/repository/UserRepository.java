package com.bumhee.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bumhee.blog.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
