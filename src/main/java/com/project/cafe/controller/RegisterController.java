package com.project.cafe.controller;

import com.project.cafe.service.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLEncoder;

@Controller
@RequestMapping("/register")
public class RegisterController {
    @GetMapping("/add")
    public String register() {
        return "registerForm";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("user") User user, Model m) throws Exception{
        if(!isValid(user)) {
            String msg = URLEncoder.encode("id를 잘못입력하셨습니다.", "utf-8");

            m.addAttribute("msg", msg);
            return "redirect:/register/add"; // 신규회원 가입화면으로 이동(redirect)
        }

        return "registerInfo";
    }

    private boolean isValid(User user) {
        return true;
    }
}
