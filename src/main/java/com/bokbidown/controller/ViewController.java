package com.bokbidown.controller;

import com.bokbidown.domain.Property;
import com.bokbidown.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final PropertyService propertyService;

    @Value("${kakao.map.key}")
    private String kakaoMapKey;

    @GetMapping("/")
    public String home(Model model) {
        return "view/home";
    }

    @GetMapping("/properties")
    public String propertyList(Model model) {
        model.addAttribute("properties", propertyService.getAllProperties());
        model.addAttribute("kakaoMapKey", kakaoMapKey);

        return "view/property_list";
    }

    @GetMapping("/properties/{id}")
    public String propertyDetail(@PathVariable Long id, Model model) {
        Property property = propertyService.getProperty(id);
        model.addAttribute("property", property);

        return "view/property_detail";
    }
}