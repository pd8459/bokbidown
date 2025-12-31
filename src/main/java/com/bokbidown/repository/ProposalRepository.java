package com.bokbidown.repository;

import com.bokbidown.domain.Proposal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {
    List<Proposal> findByPropertyId(Long propertyId);
}