package com.jurkiewicz.grzegorz.FinalBasket.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    public String showIndex (){
        return "index" ;
    }

    @RequestMapping ("/login")
    public String showLogin (){
        return "login" ;}

    @RequestMapping ("/register")
    public String showRegister () {
        return "register";
    }

    @RequestMapping ("/home")
    public  String showHome() {return "home";}

}
