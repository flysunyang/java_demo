package com.sun.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.session.FindByIndexNameSessionRepository;
import org.springframework.session.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-21 14:54
 */
@RestController
@RequestMapping("/session")
public class SessionController {

    @Autowired
    private FindByIndexNameSessionRepository findByIndexNameSessionRepository;

    @GetMapping("/list")
    public Map<String, ? extends Session> list(@RequestParam("name") String name) {
        return findByIndexNameSessionRepository.findByPrincipalName(name);
    }
}
