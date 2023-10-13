package com.test.secu1.board.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.test.secu1.board.service.BoardInfoService;
import com.test.secu1.board.vo.BoardInfoVO;
import com.test.secu1.common.vo.ResponsePageVO;
import com.test.secu1.user.vo.UserInfoVO;
@RestController
public class BoardInfoController {
	
		@Autowired
		private BoardInfoService boardInfoService;
		@PostMapping("/board-infos")
		public BoardInfoVO addBoard(BoardInfoVO board, @AuthenticationPrincipal UserInfoVO user) throws IllegalStateException, IOException {
			board.setUiNum(user.getUiNum());
			return boardInfoService.addBoard(board);
		}
		
		@GetMapping("/board-infos/{biNum}")
		public BoardInfoVO getBoardInfo(@PathVariable int biNum) {
			return boardInfoService.getBoardInfo(biNum);
		}
		
		@GetMapping("/board-infos")
		public ResponsePageVO<BoardInfoVO> getBoardInfos(BoardInfoVO board){
				return boardInfoService.selectBoardInfos(board);
		}
		
		
	
		@GetMapping("/board-infos/helper")
		public PageInfo<BoardInfoVO> getBoardInfoWithHelper(BoardInfoVO board){
				return boardInfoService.selectBoardInfosWithHelper(board);
		}
}
