package com.bokbidown.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ViewController {

    @GetMapping("/")
    public String home(Model model) {
        return "view/home";
    }
}