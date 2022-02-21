package com.mertyalcin.creditcenter.business.concrates.creditCalculators;

import com.mertyalcin.creditcenter.business.abstracts.BaseCreditCalculator;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class Between500To1000CreditCalculator extends BaseCreditCalculator {
    @Override
    public double calculateCreditAmount(int creditScore, double monthlySalary) {
        double creditAmount;
        if(monthlySalary<5000){
            creditAmount= 10000;
        }
        else{
            creditAmount= 20000;
        }

        log.info("By "+ this.getClass().getName()+
                " Credit Calculatated for creditScore: "+creditScore+" and for monthlySalary: "+monthlySalary
                +" credit amount: "+creditAmount
        );
        return creditAmount;
    }
}
