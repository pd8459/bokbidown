package com.bokbidown.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProposalRequest {
    private Long agentId;
    private Long propertyId;
    private Long proposedFee;
    private String message;
}