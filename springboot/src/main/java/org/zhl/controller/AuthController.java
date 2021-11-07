package org.zhl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhanghanlin
 * @date 2021/9/17
 **/
@RestController
@RequestMapping("/auth")
public class AuthController {


    @GetMapping("/{code}/{scope}")
    public String redUrl(@PathVariable(value = "code")String code,@PathVariable(value = "scope")String scope){

        System.out.println(code);
        System.out.println(scope);
        return "hello";

    }

}
