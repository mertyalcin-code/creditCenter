package com.mertyalcin.creditcenter.business.dto;

import com.mertyalcin.creditcenter.entities.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditDto {
    private long id;
    private double creditAmount;
    private  LocalDateTime creationTime;
    private   long customerId;
    private String customerFirstName;
    private String customerLastName;
}
