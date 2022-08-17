package com.flybirds.oauth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @author : flybirds
 * @create :2022-07-31 16:52:00
 */
@RequestMapping("/user")
@RestController
@Slf4j
public class UserDemoController {

    @PostMapping("/add")
    @PreAuthorize("hasAuthority('sys:user:add')")
    public String addUser(HttpServletRequest request){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("用户信息: {}",principal.toString());
        return  "新增用户成功";
    }

    @PostMapping
    @PreAuthorize("hasAuthority('sys:user:add')")
    public Principal User(Principal principal){
        log.info("Principal 信息: {}",principal);
        return  principal;
    }

    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('sys:user:delete')")
    public String deleteUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        log.info("用户信息: {}",principal.toString());
        return  "删除用户成功";
    }
}
