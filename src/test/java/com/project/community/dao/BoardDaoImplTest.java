package com.project.community.dao;

import com.project.community.domain.BoardDto;
import com.project.community.domain.SearchCondition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class BoardDaoImplTest {
    @Autowired
    private BoardDao boardDao;
    @Test
    public void selectPageTest() throws Exception {

        SearchCondition sc = new SearchCondition(1, 10, "title2", "T");
        List<BoardDto> list = boardDao.searchSelectPage(sc);
        System.out.println("list = " + list);
    }
    @Test
    public void searchResultCnt() throws Exception {
        boardDao.deleteAll();

        for (int i = 1; i <= 10; i++) {
            BoardDto boardDto = new BoardDto("title"+i, "no content"+i, "asdf");
            boardDao.insert(boardDto);
        }
        SearchCondition sc = new SearchCondition(1, 10, "title2", "T");
        List<BoardDto> list = boardDao.searchSelectPage(sc);
        System.out.println("list = " + list);
        assertTrue(list.size() == 1);
    }
    @Test
    public void insertTestData() throws Exception {
        boardDao.deleteAll();
        for (int i = 1; i < 220; i++) {
            BoardDto boardDto = new BoardDto("title" + i, "no content", "asdf");
            boardDao.insert(boardDto);
        }
    }

    @Test
    public void select() throws Exception{
        assertTrue(boardDao!=null);
        System.out.println("boardDao = " + boardDao);
        BoardDto boardDto = boardDao.select(1);
        System.out.println("boardDto = " + boardDto);
        assertTrue(boardDto.getBno().equals(1));
    }
}