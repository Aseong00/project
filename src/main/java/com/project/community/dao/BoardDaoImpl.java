package com.project.community.dao;

import com.project.community.domain.BoardDto;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDaoImpl implements BoardDao {

    @Autowired
    private SqlSession session;
    private static String namespace = "com.project.community.dao.BoardDao.";
    @Override
    public BoardDto select(Integer bno) {
        return session.selectOne(namespace + "boardSelect", bno);
    }
}
