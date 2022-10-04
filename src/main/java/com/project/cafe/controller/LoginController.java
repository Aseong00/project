package com.project.cafe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLEncoder;

@Controller
@RequestMapping("/login")
public class LoginController {

    @GetMapping("/login")
    public String loginForm() {
        return "loginForm";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(String id , String pwd , boolean rememberId, HttpServletResponse response, HttpServletRequest request) throws Exception{
        // 1. 아아디와 패스워드를 확인
        if (!loginCheck(id, pwd)) {
        //    일치하지 않으면 , loginForm으로 이동
            String msg = URLEncoder.encode("아이디나 패스워드가 일치하지 않습니다.", "utf-8");
            return "redirect:/login/login?msg=" + msg;
        }
        // 2. 아이디와 패스워드가 일치하면, 홈으로 이동
        //    세션 객체에 아이디 저장
        HttpSession session = request.getSession();
        session.setAttribute("id",id);

        if (rememberId) {
            Cookie cookie = new Cookie("id", id);
            response.addCookie(cookie);

        } else {
            Cookie cookie = new Cookie("id",id);
            cookie.setMaxAge(0);

            response.addCookie(cookie);
        }


        return "redirect:/";
    }

    private boolean loginCheck(String id, String pwd) {
        return "asdf".equals(id) && "1234".equals(pwd);
    }

}
