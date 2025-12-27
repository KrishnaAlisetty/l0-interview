/**
 * @author Krishna Alisetty
 * @date 26-12-2025
 */

package com.portal.interview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/login")
    public String login() {
        return "login.html";
    }

    @GetMapping("/candidate")
    public String candidate() {
        return "candidate/index.html";
    }

    @GetMapping("/interviewer")
    public String interviewer() {
        return "interviewer/index.html";
    }

    @GetMapping("update")
    public String updateForm() {
        return "candidate/update/index.html";
    }
}
