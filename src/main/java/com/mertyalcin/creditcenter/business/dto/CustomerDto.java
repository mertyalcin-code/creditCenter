package com.mertyalcin.creditcenter.business.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
   private long id;
    private String nationalityNo;
    private String firstName;
    private String lastName;
    private double monthlySalary;
    private String phoneNumber;

}
