package com.mertyalcin.creditcenter.business.requests.customerRequests;

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
public class UpdateCustomerRequest {
    @Min(1)
    long id;
    @Pattern(regexp = "\\b\\d+\\b",message = Messages.NATIONALITY_NO_ONLY_CONTAINS_NUMBERS)
    @Size(min = 11, max = 11,message = Messages.NATIONALITY_NO_SHOULD_BE_11_DIGIT)
    String nationalityNo;
    @Size(min = 2, max = 100,message = Messages.INVALID_FIRST_NAME)
    String firstName;
    @Size(min = 2, max = 100,message = Messages.INVALID_LAST_NAME)
    String lastName;
    @Min(value = 0,message = Messages.MONTHLY_SALARY_CANNOT_BE_NAGATIVE)
    Double monthlySalary;
    @Pattern(regexp = "\\b[5][0-9]{9}\\b",message = Messages.INVALID_PHONE_NUMBER)
    @Size(min=10,max = 10)
    String phoneNumber;
}
