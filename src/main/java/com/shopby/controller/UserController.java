package com.shopby.controller;

import com.shopby.model.User;
import com.shopby.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/login")
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("user_id");
        String userPassword = request.getParameter("user_password");
        String message = "";

        log.error(userId + " " + userPassword);
        if (!userService.checkLoginData(userId, userPassword)) {
            message = "아이디, 비밀번호를 확인해주세요";
        } else {
            log.error("로그인 성공");
            response.sendRedirect("home");
        }

        response.sendRedirect("login?message=" + URLEncoder.encode(message, StandardCharsets.UTF_8));
    }

    @PostMapping("/register")
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String userId = request.getParameter("user_id");
        String userName = request.getParameter("user_name");
        String userPassword = request.getParameter("user_password");
        String userPassword2 = request.getParameter("user_password2");
        String message = "";

        if (!userPassword.equals(userPassword2)) {
            message = "비밀번호가 일치하지 않습니다.";
        } else if (userService.checkUserId(userId)) {
            message = "이미 존재하는 아이디입니다.";
        } else {
            message = "정상적으로 회원가입 되었습니다. 로그인 후 이용해 주세요";
            userService.save(new User(userId, userName, userPassword));
            response.sendRedirect("login?message=" + URLEncoder.encode(message, StandardCharsets.UTF_8));
        }
        response.sendRedirect("register?message=" + URLEncoder.encode(message, StandardCharsets.UTF_8));
    }
}
