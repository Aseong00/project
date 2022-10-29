package com.project.community.dao;

import com.project.community.domain.BoardDto;
import com.project.community.domain.SearchCondition;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BoardDaoImpl implements BoardDao {

    @Autowired
    private SqlSession session;
    private static String namespace = "com.project.community.dao.BoardDao.";

    @Override
    public BoardDto select(Integer bno) {
        return session.selectOne(namespace + "boardSelect", bno);
    }

    @Override
    public int delete(Integer bno, String writer) throws Exception {
        Map map = new HashMap();
        map.put("bno", bno);
        map.put("writer", writer);
        return session.delete(namespace + "boardDelete",map);
    }

    @Override
    public int insert(BoardDto boardDto) throws Exception {
        return session.insert(namespace + "boardInsert", boardDto);
    }

    @Override
    public int update(BoardDto boardDto) throws Exception {
        return session.update(namespace + "boardUpdate", boardDto);
    }

    @Override
    public int increaseViewCnt(Integer bno) throws Exception {
        return session.update(namespace+"increaseViewCnt", bno);
    }

    @Override
    public List<BoardDto> selectPage(Map map) throws Exception {
        return session.selectList(namespace+"selectPage", map);
    }

    @Override
    public List<BoardDto> selectAll() throws Exception {
        return session.selectList(namespace + "boardSelectAll");
    }

    @Override
    public int deleteAll() throws Exception {
        return session.delete(namespace + "boardDeleteAll");
    }

    @Override
    public int count() throws Exception {
        return session.selectOne(namespace+"count");
    }

    @Override
    public int searchResultCnt(SearchCondition sc) throws Exception {
        System.out.println("sc in searchResultCnt() = " + sc);
        System.out.println("session = " + session);
        return session.selectOne(namespace+"searchResultCnt", sc);
    }

    @Override
    public List<BoardDto> searchSelectPage(SearchCondition sc) throws Exception {
        return session.selectList(namespace+"searchSelectPage", sc);
    }

    @Override
    public int updateCommentCnt(Integer bno, int cnt) {
        Map map = new HashMap();
        map.put("bno", bno);
        map.put("cnt", cnt);
        return session.update(namespace + "updateCommentCnt", map);
    }
}
