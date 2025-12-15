package com.bokbidown.controller;

import com.bokbidown.domain.Property;
import com.bokbidown.domain.PurchaseRequest;
import com.bokbidown.domain.RequestStatus;
import com.bokbidown.domain.User;
import com.bokbidown.repository.PropertyRepository;
import com.bokbidown.repository.PurchaseRequestRepository;
import com.bokbidown.repository.UserRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchase-requests")
@RequiredArgsConstructor
public class PurchaseRequestController {

    private final PurchaseRequestRepository purchaseRequestRepository;
    private final UserRepository userRepository;
    private final PropertyRepository propertyRepository;

    @PostMapping
    public String requestVisit(@RequestBody RequestDto dto) {
        User buyer = userRepository.findById(dto.getBuyerId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        Property property = propertyRepository.findById(dto.getPropertyId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 매물입니다."));

        PurchaseRequest request = new PurchaseRequest();
        request.setBuyer(buyer);
        request.setProperty(property);
        request.setStatus(RequestStatus.VISIT_REQUESTED);

        purchaseRequestRepository.save(request);

        return "방문 요청이 완료되었습니다. ID: " + request.getId();
    }

    @PatchMapping("/{id}/confirm-purchase")
    public String confirmPurchase(@PathVariable Long id) {
        PurchaseRequest request = purchaseRequestRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 요청이 없습니다."));

        request.setStatus(RequestStatus.AUCTION_OPEN);
        purchaseRequestRepository.save(request);

        return "구매 의사가 확정되었습니다. 역경매가 시작됩니다.";
    }

    @Data
    static class RequestDto {
        private Long buyerId;
        private Long propertyId;
    }
}