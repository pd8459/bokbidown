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

    @Transactional(readOnly = true)
    public java.util.List<com.bokbidown.dto.ProposalResponseDto> getProposalsByPropertyId(Long propertyId) {
        return proposalRepository.findByPropertyId(propertyId)
                .stream()
                .map(com.bokbidown.dto.ProposalResponseDto::new)
                .collect(java.util.stream.Collectors.toList());
    }

    public void selectProposal(Long proposalId) {
        Proposal proposal = proposalRepository.findById(proposalId)
                .orElseThrow(() -> new IllegalArgumentException("제안서가 없습니다."));

        com.bokbidown.domain.PurchaseRequest request = purchaseRequestRepository.findAll().stream()
                .filter(r -> r.getProperty().getId().equals(proposal.getProperty().getId())
                        && r.getStatus() == RequestStatus.AUCTION_OPEN)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("진행 중인 경매를 찾을 수 없습니다."));

        request.setStatus(RequestStatus.COMPLETED);
        purchaseRequestRepository.save(request);

        System.out.println("낙찰된 중개사: " + proposal.getAgent().getName() + ", 금액: " + proposal.getProposedFee());
    }
}