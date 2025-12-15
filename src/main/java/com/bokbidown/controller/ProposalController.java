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
}