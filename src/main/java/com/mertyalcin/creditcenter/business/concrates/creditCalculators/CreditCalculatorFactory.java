package com.mertyalcin.creditcenter.business.concrates.creditCalculators;

import com.mertyalcin.creditcenter.business.abstracts.BaseCreditCalculator;
import com.mertyalcin.creditcenter.business.abstracts.BaseCreditCalculatorFactory;
import com.mertyalcin.creditcenter.core.utils.messages.Messages;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class CreditCalculatorFactory extends BaseCreditCalculatorFactory {
    @Override
    public double calculateCreditAmount(int creditScore, double monthlySalary) {
        BaseCreditCalculator baseCreditCalculator;
        if (creditScore<500){
            baseCreditCalculator = new Below500CreditCalculator();
        }
        else if (creditScore>=500 && creditScore<=1000){
            baseCreditCalculator = new Between500To1000CreditCalculator();
        }
        else if (creditScore>1000){
            baseCreditCalculator = new Above1000CreditCalculator();
        }
        else{
            log.info(Messages.NO_CREDIT_CALCULATOR+"for "+"creditScore:"+creditScore+"montlySalary: "+monthlySalary);
            throw new IllegalArgumentException(Messages.NO_CREDIT_CALCULATOR);
        }

        return baseCreditCalculator.calculateCreditAmount(creditScore,monthlySalary);
    }
}
