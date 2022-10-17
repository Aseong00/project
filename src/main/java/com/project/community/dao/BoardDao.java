package com.project.community.dao;

import com.project.community.domain.BoardDto;

public interface BoardDao {
    BoardDto select(Integer bno);
}
