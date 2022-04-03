package com.bumhee.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bumhee.blog.model.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{

}
