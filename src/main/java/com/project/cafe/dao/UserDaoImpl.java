package com.project.cafe.dao;

import com.project.cafe.domain.User;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    public SqlSession session;
    public static String namespace = "com.project.cafe.dao.UserDao.";
    @Autowired
    DataSource ds;
    final int FAIL = 0;

    @Override
    public int deleteUser(String id) {
        return session.delete(namespace + "deleteUser", id);
    }
//    @Override
//    public int deleteUser(String id) {
//        int rowCnt = FAIL; //  insert, delete, update
//
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//
//        String sql = "delete from user where id= ? ";
//
//        try {
//            conn = ds.getConnection();
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, id);
//
//            return pstmt.executeUpdate(); //  insert, delete, update
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return FAIL;
//        } finally {
//            close(pstmt, conn); //     private void close(AutoCloseable... acs) {
//        }
//    }

    @Override
    public User selectUser(String id) {
        User user = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "select * from user where id= ? ";

        try {
            conn = ds.getConnection();
            pstmt = conn.prepareStatement(sql); // SQL Injection공격, 성능향상
            pstmt.setString(1, id);

            rs = pstmt.executeQuery(); //  select

            if (rs.next()) {
                user = new User();
                user.setId(rs.getString(1));
                user.setPwd(rs.getString(2));
                user.setName(rs.getString(3));
                user.setEmail(rs.getString(4));
                user.setBirth(new Date(rs.getDate(5).getTime()));
                user.setNum(rs.getString(6));
                user.setReg_date(new Date(rs.getTimestamp(7).getTime()));
            }
        } catch (SQLException e) {
            return null;
        } finally {
            close(rs, pstmt, conn);  //     private void close(AutoCloseable... acs) {
        }

        return user;
    }
//    @Override
//    public User selectUser(String id) {
//        User user = null;
//
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//        String sql = "select * from user where id= ? ";
//
//        try {
//            conn = ds.getConnection();
//            pstmt = conn.prepareStatement(sql); // SQL Injection공격, 성능향상
//            pstmt.setString(1, id);
//
//            rs = pstmt.executeQuery(); //  select
//
//            if (rs.next()) {
//                user = new User();
//                user.setId(rs.getString(1));
//                user.setPwd(rs.getString(2));
//                user.setName(rs.getString(3));
//                user.setEmail(rs.getString(4));
//                user.setBirth(new Date(rs.getDate(5).getTime()));
//                user.setNum(rs.getString(6));
//                user.setReg_date(new Date(rs.getTimestamp(7).getTime()));
//            }
//        } catch (SQLException e) {
//            return null;
//        } finally {
//            close(rs, pstmt, conn);  //     private void close(AutoCloseable... acs) {
//        }
//
//        return user;
//    }

    // 사용자 정보를 user_info테이블에 저장하는 메서드
    @Override
    public int insertUser(User user) {
        return session.insert(namespace + "insertUser", user);
    }
//    @Override
//    public int insertUser(User user) {
//        int rowCnt = FAIL;
//
//        Connection conn = null;
//        PreparedStatement pstmt = null;
//
//        String sql = "insert into user values (?, ?, ?, ?,?,?, now()) ";
//
//        try {
//            conn = ds.getConnection();
//            pstmt = conn.prepareStatement(sql); // SQL Injection공격, 성능향상
//            pstmt.setString(1, user.getId());
//            pstmt.setString(2, user.getPwd());
//            pstmt.setString(3, user.getName());
//            pstmt.setString(4, user.getEmail());
//            pstmt.setDate(5, new java.sql.Date(user.getBirth().getTime()));
//            pstmt.setString(6, user.getNum());
//
//            return pstmt.executeUpdate(); //  insert, delete, update;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return FAIL;
//        } finally {
//            close(pstmt, conn);  //     private void close(AutoCloseable... acs) {
//        }
//    }

    @Override
    public int updateUser(User user) {
        return session.update(namespace + "updateUser", user);
    }
    // 매개변수로 받은 사용자 정보로 user_info테이블을 update하는 메서드
//    @Override
//    public int updateUser(User user) {
//        int rowCnt = FAIL; //  insert, delete, update
//
//        String sql = "update user " +
//                "set pwd = ?, name=?, email=?, birth =?, num=?, reg_date=? " +
//                "where id = ? ";
//
//        try (
//                Connection conn = ds.getConnection();
//                PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection공격, 성능향상
//        ){
//            pstmt.setString(1, user.getPwd());
//            pstmt.setString(2, user.getName());
//            pstmt.setString(3, user.getEmail());
//            pstmt.setDate(4, new java.sql.Date(user.getBirth().getTime()));
//            pstmt.setString(5, user.getNum());
//            pstmt.setTimestamp(6, new java.sql.Timestamp(user.getReg_date().getTime()));
//            pstmt.setString(7, user.getId());
//
//            rowCnt = pstmt.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return FAIL;
//        }
//
//        return rowCnt;
//    }

    public void deleteAll() throws Exception {
        Connection conn = ds.getConnection();

        String sql = "delete from user ";

        PreparedStatement pstmt = conn.prepareStatement(sql); // SQL Injection공격, 성능향상
        pstmt.executeUpdate(); //  insert, delete, update
    }

    private void close(AutoCloseable... acs) {
        for(AutoCloseable ac :acs)
            try { if(ac!=null) ac.close(); } catch(Exception e) { e.printStackTrace(); }
    }
}