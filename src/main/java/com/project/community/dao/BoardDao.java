package com.project.community.dao;

import com.project.community.domain.BoardDto;
import com.project.community.domain.SearchCondition;

import java.util.List;
import java.util.Map;

public interface BoardDao {
    BoardDto select(Integer bno) throws Exception;
    int delete(Integer bno, String writer) throws Exception;
    int insert(BoardDto boardDto) throws Exception;
    int update(BoardDto boardDto) throws Exception;
    int increaseViewCnt(Integer bno) throws Exception;

    List<BoardDto> selectPage(Map map) throws Exception;
    List<BoardDto> selectAll() throws Exception;
    int deleteAll() throws Exception;
    int count() throws Exception;

    int searchResultCnt(SearchCondition sc) throws Exception;
    List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception;
}
