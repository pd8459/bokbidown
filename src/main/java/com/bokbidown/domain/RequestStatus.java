package com.bokbidown.domain;

public enum RequestStatus {
    VISIT_REQUESTED,   // 1. 방문 요청 (집 보여주세요)
    PURCHASE_DECIDED,  // 2. 구매 확정 (거래 할래요)
    AUCTION_OPEN,      // 3. 경매 시작 (중개사 입찰 중)
    COMPLETED          // 4. 완료
}