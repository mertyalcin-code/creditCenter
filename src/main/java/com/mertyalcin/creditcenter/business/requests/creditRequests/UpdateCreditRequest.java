package com.mertyalcin.creditcenter.business.requests.creditRequests;

import com.mertyalcin.creditcenter.core.utils.messages.Messages;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCreditRequest {
    @Min(1)
    long id;
    @Min(1)
    long customerId;
    @Min(0)
    double creditAmount;
}
