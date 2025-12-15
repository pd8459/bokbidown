package com.bokbidown.controller;

import com.bokbidown.domain.Property;
import com.bokbidown.dto.PropertyRequest;
import com.bokbidown.service.PropertyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping
    public String register(@RequestBody PropertyRequest request) {
        Long propertyId = propertyService.registerProperty(request);
        return "매물 등록 완료! ID: " + propertyId;
    }

    @GetMapping
    public List<Property> getAll() {
        return propertyService.getAllProperties();
    }
}