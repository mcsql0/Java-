package mcsql.net.controller;

import mcsql.net.entities.LoginInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @PostMapping("login")
    public String login(LoginInfo loginInfo, HttpServletRequest request, HttpSession session,
            Map<String,String> map) {
        if ("admin".equals(loginInfo.getUserName()) && "1".equals(loginInfo.getPassword())) {
            session.setAttribute("loginInfo", loginInfo);
            return "redirect:/home";
        }else{
            //登陆失败
            map.put("msg","用户名密码错误");
            return  "index";
        }
    }

    @GetMapping("home")
    public String home() {
        return "dashboard";
    }
}
