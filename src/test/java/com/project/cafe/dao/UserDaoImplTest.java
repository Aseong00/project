package com.project.cafe.dao;

import com.project.cafe.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class UserDaoImplTest {
    @Autowired
    UserDao userDao;
    @Test
    public void deleteUser() {
    }

    @Test
    public void selectUser() {
    }

    @Test
    public void insertUser() {
    }

    @Test
    public void updateUser() throws Exception{
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.set(2022, 1, 1);

        userDao.deleteAll();
        User user = new User("ccc", "1234", "abc", "asd@naver.com", new Date(cal.getTimeInMillis()), "010-0101-0101", new Date());
        int rowCnt = userDao.insertUser(user);
        assertTrue(rowCnt == 1);

        user.setPwd("4321");
        user.setEmail("bbb@naver.com");
        rowCnt = userDao.updateUser(user);
        assertTrue(rowCnt == 1);

        User user2 = userDao.selectUser(user.getId());
        System.out.println("user = " + user);
        System.out.println("user2 = " + user2);
        assertTrue(user.equals(user2));

    }
}