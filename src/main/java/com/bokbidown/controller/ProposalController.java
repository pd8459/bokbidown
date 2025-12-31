package com.bokbidown.controller;

import com.bokbidown.dto.ProposalRequest;
import com.bokbidown.service.ProposalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/proposals")
@RequiredArgsConstructor
public class ProposalController {

    private final ProposalService proposalService;

    @PostMapping
    public String submit(@RequestBody ProposalRequest request) {
        Long proposalId = proposalService.submitProposal(request);
        return "입찰 성공! 제안서 ID: " + proposalId;
    }

    @org.springframework.web.bind.annotation.GetMapping("/{propertyId}")
    public java.util.List<com.bokbidown.dto.ProposalResponseDto> getProposals(@org.springframework.web.bind.annotation.PathVariable Long propertyId) {
        return proposalService.getProposalsByPropertyId(propertyId);
    }

    @org.springframework.web.bind.annotation.PatchMapping("/{proposalId}/select")
    public String selectProposal(@org.springframework.web.bind.annotation.PathVariable Long proposalId) {
        proposalService.selectProposal(proposalId);
        return "중개사 선택 완료! 거래가 성사되었습니다.";
    }
}