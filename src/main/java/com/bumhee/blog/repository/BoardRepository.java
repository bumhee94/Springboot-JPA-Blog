package com.bumhee.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bumhee.blog.model.Board;
import com.bumhee.blog.model.User;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}
