package com.bokbidown.dto;

import com.bokbidown.domain.Proposal;
import lombok.Data;

@Data
public class ProposalResponseDto {
    private Long proposalId;
    private String agentName;
    private String officeName;
    private Long proposedFee;
    private String message;

    public ProposalResponseDto(Proposal proposal) {
        this.proposalId = proposal.getId();
        this.agentName = proposal.getAgent().getName();
        this.officeName = proposal.getAgent().getOfficeName();
        this.proposedFee = proposal.getProposedFee();
        this.message = proposal.getMessage();
    }
}