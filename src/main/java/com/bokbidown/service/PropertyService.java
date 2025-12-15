package com.bokbidown.service;

import com.bokbidown.domain.Property;
import com.bokbidown.domain.User;
import com.bokbidown.dto.PropertyRequest;
import com.bokbidown.repository.PropertyRepository;
import com.bokbidown.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    @Transactional
    public Long registerProperty(PropertyRequest request) {
        User seller = userRepository.findById(request.getSellerId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Property property = new Property();
        property.setSeller(seller);
        property.setAddress(request.getAddress());
        property.setTradeType(request.getTradeType());
        property.setDeposit(request.getDeposit());
        property.setMonthlyRent(request.getMonthlyRent());
        property.setDescription(request.getDescription());
        propertyRepository.save(property);

        return property.getId();
    }

    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }
}