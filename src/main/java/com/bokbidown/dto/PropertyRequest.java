package com.bokbidown.dto;

import com.bokbidown.domain.TradeType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PropertyRequest {
    private Long sellerId;
    private TradeType tradeType;
    private String address;
    private Long deposit;
    private Long monthlyRent;
    private String description;
}