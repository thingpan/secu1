package com.test.secu1.board.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.test.secu1.board.mapper.BoardInfoMapper;
import com.test.secu1.board.vo.BoardInfoVO;
import com.test.secu1.common.util.StringUtils;
import com.test.secu1.common.vo.ResponsePageVO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardInfoService {
 @Value("${upload.file-path}")
 private String filePath;
 @Autowired
 private BoardInfoMapper boardInfoMapper;
 
 public BoardInfoVO addBoard(BoardInfoVO board)throws IllegalStateException ,IOException{
	 
		String fileName = board.getFile().getOriginalFilename();
		if(board.getFile()!=null && !fileName.isEmpty()) {
			
			String uuid = UUID.randomUUID().toString();
			
			int idx = fileName.lastIndexOf(".");
			String extName = StringUtils.getExt(fileName);
			String saveFilePath = filePath+"/" + uuid + extName;
			File file = new File(saveFilePath);
			
			board.getFile().transferTo(file);
			log.info("fileName=>{}",fileName);
			board.setBiFileName(fileName);
			board.setBiFilePath(uuid + extName);
		}
		boardInfoMapper.insertBoardInfo(board);
		if(board.getBiNum()!=0) {
			return boardInfoMapper.selectBoardInfo(board.getBiNum());
		}
		return null;
	}
	
	public BoardInfoVO getBoardInfo(int biNum) {
		return boardInfoMapper.selectBoardInfo(biNum);
	}
	
	public ResponsePageVO<BoardInfoVO> selectBoardInfos(BoardInfoVO board){
		board.setEnd(board.getPageSize());
		int start = (board.getPage()-1) * board.getPageSize();
		board.setStart(start);
		ResponsePageVO<BoardInfoVO> resVO = new ResponsePageVO<>();
		resVO.setList(boardInfoMapper.selectBoardInfos(board));
		resVO.setTotalCnt(boardInfoMapper.selectBoardInfoCnt(board));
		return resVO;
	}
	public PageInfo<BoardInfoVO> selectBoardInfosWithHelper(BoardInfoVO board){
	PageHelper.startPage(board.getPage(),board.getPageSize());
	return new PageInfo<>(boardInfoMapper.selectBoardInfosWithHelper(board));
	}
}
