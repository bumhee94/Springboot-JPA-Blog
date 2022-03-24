package com.bumhee.blog.controller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bumhee.blog.config.auth.PrincipalDetail;
import com.bumhee.blog.dto.ResponseDto;
import com.bumhee.blog.model.Board;
import com.bumhee.blog.model.User;
import com.bumhee.blog.service.BoardService;
import com.bumhee.blog.service.UserService;

@RestController
public class BoardApiController {

	@Autowired
	private BoardService boardService;


	@PostMapping("/api/board")
	public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal)
	{
		boardService.글쓰기(board, principal.getUser());
		return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
	}
}
