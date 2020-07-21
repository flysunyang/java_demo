package com.sun.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaoyang
 * @description
 * @create 2020-07-21 10:04
 */
@RestController
@RequestMapping("/session")
public class SessionController {

    @PostMapping("/set")
    public String set(HttpSession httpSession, @RequestParam("key") String key,
                    @RequestParam("value") String value) {
        httpSession.setAttribute(key, value);
        return "success";
    }

    @GetMapping("/get_all")
    public Map<String, Object> getAll(HttpSession httpSession) {
        Map<String, Object> result = new HashMap<>();
        Enumeration<String> attributeNames = httpSession.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String key = attributeNames.nextElement();
            Object value = httpSession.getAttribute(key);
            result.put(key, value);
        }
        return result;
    }

}
