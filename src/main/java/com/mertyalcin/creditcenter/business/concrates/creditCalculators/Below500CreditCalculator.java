package com.mertyalcin.creditcenter.business.concrates.creditCalculators;

import com.mertyalcin.creditcenter.business.abstracts.BaseCreditCalculator;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class Below500CreditCalculator extends BaseCreditCalculator {
    @Override
    public double calculateCreditAmount(int creditScore, double monthlySalary) {
        double creditAmount=0;
        log.info("By "+ this.getClass().getName()+
                " Credit Calculatated for creditScore: "+creditScore+" and for monthlySalary: "+monthlySalary
                +" credit amount: "+creditAmount
        );
        return creditAmount;
    }
}
