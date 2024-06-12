package com.isisma7.calendarease.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value = {"/"})
    public String index(){
        return "index";
    }

    @GetMapping(value = {"/loggin"})
    public String loggin(){
        return "loggin";
    }

    @GetMapping(value = {"/home"})
    public String home(){
        return "home";
    }
}
