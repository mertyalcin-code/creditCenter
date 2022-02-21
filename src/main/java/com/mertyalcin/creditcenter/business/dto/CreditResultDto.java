package com.mertyalcin.creditcenter.business.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditResultDto {
    private long id;
    private  String nationalityNo;
    private String firstName;
    private  String lastName;
    private String phoneNumber;
    private double creditAmount;
    private boolean isApproved;
    private String creditScore;
}
