package com.bumhee.blog.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bumhee.blog.model.Board;
import com.bumhee.blog.model.Reply;
import com.bumhee.blog.model.RoleType;
import com.bumhee.blog.model.User;
import com.bumhee.blog.repository.BoardRepository;
import com.bumhee.blog.repository.ReplyRepository;
import com.bumhee.blog.repository.UserRepository;

@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	@Autowired
	private ReplyRepository replyRepository;
	
	@Transactional
	public void 글쓰기(Board board, User user)
	{
		board.setCount(0);
		board.setUser(user);
		boardRepository.save(board);
	}
	
	@Transactional(readOnly = true)
	public Page<Board> 글목록(Pageable pageable)
	{
		return boardRepository.findAll(pageable);
	}
	
	@Transactional(readOnly = true)
	public Board 글상세보기(int id)
	{
		return boardRepository.findById(id)
				.orElseThrow(()->{
			return new IllegalArgumentException("글 상세보기 실패");
		});
	}
	
	@Transactional
	public void 삭제하기(int id)
	{
		 boardRepository.deleteById(id);
	}
	
	@Transactional
	public void 수정하기(int id, Board reqBoard)
	{
		Board board = boardRepository.findById(id)
				.orElseThrow(()->{
					return new IllegalArgumentException("글 찾기 실패");
				});
		 
		board.setTitle(reqBoard.getTitle());
		board.setContent(reqBoard.getContent());
	}
	
	@Transactional
	public void 댓글쓰기(User user, int boardId, Reply reqReply)
	{
		Board board = boardRepository.findById(boardId).orElseThrow(() -> {
			return new IllegalArgumentException("댓글쓰기 실패");
		});
		reqReply.setUser(user);
		reqReply.setBoard(board);
		
		replyRepository.save(reqReply);
	}
	@Transactional
	public void 댓글삭제(int replyId)
	{
		replyRepository.deleteById(replyId);
	}
	
}
