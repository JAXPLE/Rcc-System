package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller



public class MainController {

    @GetMapping({ "/" })
    public String index(){
   //     return "index.html";
        return "index";
    }
    @GetMapping({ "/header" })
    public String header(){
        return "default/header";
    }
    @GetMapping({ "/footer" })
    public String footer(){
        return "default/footer";
    }
    @GetMapping({ "/main" })
    public String main(){
        return "main";
    }

    @GetMapping({ "/join" })
    public String join(){
        return "user/join";
    }
    @GetMapping({ "/login" })
    public String login(){
        return "login";
    }



    @GetMapping({ "/userList" })
    public String userList(){
        return "user/userList";
    }



}
