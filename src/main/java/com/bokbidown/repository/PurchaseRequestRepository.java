package com.bokbidown.repository;

import com.bokbidown.domain.PurchaseRequest;
import com.bokbidown.domain.RequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PurchaseRequestRepository extends JpaRepository<PurchaseRequest, Long> {
    List<PurchaseRequest> findByStatus(RequestStatus status);
}