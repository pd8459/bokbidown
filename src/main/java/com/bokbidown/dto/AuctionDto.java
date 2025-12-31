package com.bokbidown.dto;

import com.bokbidown.domain.PurchaseRequest;
import com.bokbidown.domain.TradeType;
import lombok.Data;

@Data
public class AuctionDto {
    private Long purchaseRequestId; // 요청 ID
    private Long propertyId;        // 매물 ID (입찰할 때 필요)
    private String address;         // 주소
    private TradeType tradeType;    // 전세/매매
    private Long deposit;           // 금액
    private String description;     // 집 설명

    // 엔티티를 받아서 DTO로 변환하는 생성자
    public AuctionDto(PurchaseRequest request) {
        this.purchaseRequestId = request.getId();
        this.propertyId = request.getProperty().getId();
        this.address = request.getProperty().getAddress();
        this.tradeType = request.getProperty().getTradeType();
        this.deposit = request.getProperty().getDeposit();
        this.description = request.getProperty().getDescription();
    }
}