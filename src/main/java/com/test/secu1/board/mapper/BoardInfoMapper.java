package com.test.secu1.board.mapper;

import java.util.List;

import com.test.secu1.board.vo.BoardInfoVO;

public interface BoardInfoMapper {
List<BoardInfoVO> selectBoardInfos(BoardInfoVO board);
int insertBoardInfo(BoardInfoVO board);
int selectBoardInfoCnt(BoardInfoVO board);
BoardInfoVO selectBoardInfo(int biNum);
List<BoardInfoVO> selectBoardInfosWithHelper(BoardInfoVO board);
}
