package com.isisma7.calendarease.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes({"DataUser"})
public class IndexController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping(value = {"/", "/index"})
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
