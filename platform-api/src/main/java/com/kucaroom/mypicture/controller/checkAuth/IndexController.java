package com.kucaroom.mypicture.controller.checkAuth;import org.springframework.web.bind.annotation.GetMapping;import org.springframework.web.bind.annotation.RequestMapping;import org.springframework.web.bind.annotation.RestController;@RestController@RequestMapping("/index")public class IndexController {    @GetMapping("/test")    public String test(){        return "hello api";    }}