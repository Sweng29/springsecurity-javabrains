package com.springsecurity.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @RequestMapping(value = "/")
    public String greet() {
        return "Hello World!";
    }

    @RequestMapping(value = "/user")
    public String greetUser() {
        return ("<h1>Hello User!</h2");
    }

    @RequestMapping(value = "/admin")
    public String greetAdmin() {
        return ("<h1>Hello Admin!</h2");
    }
}
