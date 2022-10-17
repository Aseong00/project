package com.project.community.dao;

import com.project.community.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class mybatisTest {
    @Autowired
    UserDao userDao;
    @Test
    public void deleteUser() {
        userDao.deleteUser("test990");
    }

    @Test
    public void insertUser() {
    }

    @Test
    public void updateUser() {
        User user = new User();
        user.setPwd("0909");
        user.setNum("001-0000-0000");
        user.setId("aaaaaaa");
        userDao.updateUser(user);
    }
}