package com.shopby.controller;

import com.shopby.model.User;
import com.shopby.service.UserService;
import com.shopby.utils.encrypt.EncryptHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final EncryptHelper encryptHelper;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session, ModelMap model) throws IOException {
        String userId = request.getParameter("user_id");
        String userPassword = request.getParameter("user_password");
        String message = "";

        log.error(userId + " " + userPassword);
        Optional<User> optionalUser = userService.findByUserId(userId);

        if (optionalUser.isEmpty()) {
            message = "아이디를 확인해주세요";
        } else {
            User user = optionalUser.get();
            if (encryptHelper.isMatch(userPassword, user.getPassword())) {
                log.error("로그인 성공");
                session.setAttribute("userId", userId);
                return "redirect:/";
            }
            message = "비밀번호를 확인해주세요";
        }
        model.addAttribute("message", message);
        return "login";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("userId");
        return "redirect:/";
    }

    @PostMapping("/register")
    public String register(HttpServletRequest request, HttpServletResponse response, ModelMap model) throws IOException {
        String userId = request.getParameter("user_id");
        String userName = request.getParameter("user_name");
        String userPassword = request.getParameter("user_password");
        String userPassword2 = request.getParameter("user_password2");
        String message = "";

        if (!userPassword.equals(userPassword2)) {
            message = "비밀번호가 일치하지 않습니다.";
        } else if (userId.length() < 2 || userId.length() > 15) {
            message = "아이디는 3 ~ 14자 이여야 합니다.";
        } else if (userName.length() < 2 || userName.length() > 15) {
            message = "이름은 3 ~ 14자 이여야 합니다.";
        } else if (userPassword.length() < 5 || userPassword.length() > 15) {
            message = "비밀번호는 5 ~ 14자 이여야 합니다.";
        } else if (userService.checkUserId(userId)) {
            message = "이미 존재하는 아이디입니다.";
        } else {
            userService.save(new User(userId, userName, encryptHelper.encrypt(userPassword)));
            log.error("회원가입 성공");
            message = "정상적으로 회원가입 되었습니다. 로그인 후 이용해 주세요";
            model.addAttribute("message", message);
            return "login";
        }
        model.addAttribute("message", message);
        return "register";
    }
}
