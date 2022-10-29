package com.project.community.dao;

import com.project.community.domain.CommentDto;

import java.util.List;

public interface CommentDao {
    int count(Integer bno) throws Exception;

    int deleteAll(Integer bno);

    int delete(Integer cno, String commenter) throws Exception;

    int insert(CommentDto commentDto) throws Exception;

    List<CommentDto> selectAll(Integer bno) throws Exception;

    CommentDto select(Integer cno) throws Exception;

    int update(CommentDto commentDto) throws Exception;
}
