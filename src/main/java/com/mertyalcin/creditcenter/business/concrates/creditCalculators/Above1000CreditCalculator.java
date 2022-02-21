package com.mertyalcin.creditcenter.business.concrates.creditCalculators;

import com.mertyalcin.creditcenter.business.abstracts.BaseCreditCalculator;
import com.mertyalcin.creditcenter.core.constants.Constants;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class Above1000CreditCalculator extends BaseCreditCalculator {

    private double coefficient = Constants.creditCoefficient;

    @Override
    public double calculateCreditAmount(int creditScore, double monthlySalary) {
       double creditAmount = monthlySalary*coefficient;
       log.info("By "+ this.getClass().getName()+
               " Credit Calculatated for creditScore: "+creditScore+" and for monthlySalary: "+monthlySalary
                +" credit amount: "+creditAmount
       );
        return creditAmount;
    }
}
