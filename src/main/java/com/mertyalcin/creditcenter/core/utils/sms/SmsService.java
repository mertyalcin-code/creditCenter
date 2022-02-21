package com.mertyalcin.creditcenter.core.utils.sms;

import com.mertyalcin.creditcenter.core.utils.results.SuccessResult;
import com.mertyalcin.creditcenter.entities.Credit;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class SmsService {
    public void sendApprovedCreditResultSMS(Credit credit){
        log.info(credit.getCustomer().getFirstName() +" "+ credit.getCreditAmount());

    }
    public void sendUnapprovedCreditResultSMS(Credit credit){
        log.info(credit.getCustomer().getFirstName() +"not approved");

    }
}
