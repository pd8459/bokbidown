package com.bokbidown.service;

import com.bokbidown.domain.Agent;
import com.bokbidown.domain.Property;
import com.bokbidown.domain.Proposal;
import com.bokbidown.domain.RequestStatus;
import com.bokbidown.dto.ProposalRequest;
import com.bokbidown.repository.AgentRepository;
import com.bokbidown.repository.PropertyRepository;
import com.bokbidown.repository.ProposalRepository;
import com.bokbidown.repository.PurchaseRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProposalService {

    private final ProposalRepository proposalRepository;
    private final AgentRepository agentRepository;
    private final PropertyRepository propertyRepository;
    private final PurchaseRequestRepository purchaseRequestRepository;

    public Long submitProposal(ProposalRequest request) {
        Agent agent = agentRepository.findById(request.getAgentId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 중개사입니다."));

        Property property = propertyRepository.findById(request.getPropertyId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 매물입니다."));

        boolean isAuctionOpen = purchaseRequestRepository.findAll().stream()
                .anyMatch(r -> r.getProperty().getId().equals(request.getPropertyId())
                        && r.getStatus() == RequestStatus.AUCTION_OPEN);

        if (!isAuctionOpen) {
            throw new IllegalStateException("현재 경매가 진행 중인 매물이 아닙니다.");
        }

        Proposal proposal = new Proposal();
        proposal.setAgent(agent);
        proposal.setProperty(property);
        proposal.setProposedFee(request.getProposedFee());
        proposal.setMessage(request.getMessage());

        proposalRepository.save(proposal);

        return proposal.getId();
    }
}